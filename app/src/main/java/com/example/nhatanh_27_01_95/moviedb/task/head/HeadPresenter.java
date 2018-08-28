package com.example.nhatanh_27_01_95.moviedb.task.head;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.nhatanh_27_01_95.moviedb.data.local.DbMovie;
import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonCast;
import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonDetails;
import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonMovie;
import com.example.nhatanh_27_01_95.moviedb.data.remote.LoadImage;
import com.example.nhatanh_27_01_95.moviedb.task.DetailMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.Cast;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilError;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HeadPresenter implements HeadContract.Presenter {

  private ArrayList<Cast> mCast;
  private Movie mMovie;
  private HeadContract.Views mViews;

  public HeadPresenter( Movie movie, HeadContract.Views views) {
    mMovie = movie;
    mViews = views;
  }

  public ArrayList<Cast> getCast() {
    return mCast;
  }

  @Override
  public void start() {
    mViews.showTextOverview(mMovie.getOverview());
    mViews.showRank(mMovie.getVoteAverage());
    mViews.showNameMovie(mMovie.getTitle());
    mViews.showCountMovie(mMovie.getPopularity());
    mViews.drawLayout();
    mViews.showImage();
    mViews.showTabLayout();
    loadCast();
  }


  @Override
  public void loadImage( final Context context, ImageView imageView) {
    new LoadImage(context, imageView).execute(UtilMovies.SearchMovie.urlImage(mMovie.getUrlImage()));
  }

  @Override
  public void loadCast() {
    if (UtilActivytyHome.isNetwork)
    {
      try {
        mCast = new JsonCast().execute(UtilMovies.Cast.urlCastOffMovie(mMovie.getId())).get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      mViews.showRecylerCast();
    }
  }

  @Override
  public void loadMovieOfCast(String idCast) {
    try {
      ArrayList<Movie> arrayList = new JsonMovie(UtilMovies.Cast.CAST_TIEM).
          execute(UtilMovies.SearchMovie.movieOfCast (idCast)).get();
      if (arrayList != null){
        if (arrayList.size() > 0) {
          mViews.showListMovie(arrayList);
          return;
        }
      }
      mViews.showErr(UtilError.ERROR_NULL_MOVIE);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void loadMovieGenrer(Movie movie) {
    try {
      ArrayList<Movie> arrayList = new JsonMovie(UtilMovies.TITLE_MOVIE_RESULTS).
          execute(UtilMovies.SearchMovie.urlMovieGenrer(movie.getId(), 1)).get();
      if (arrayList != null){
        if (arrayList.size() > 0) {
          mViews.showListMovie(arrayList);
          return;
        }
      }
       mViews.showErr(UtilError.ERROR_NULL_MOVIE);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void callDialogDetail() {
    try {
      DetailMovie detailMovie = new JsonDetails(mMovie).execute(UtilMovies.Detail.urlDetailMovie(mMovie.getId())).get();
      mViews.showDetailsMovie(detailMovie);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void playMovie() {
     mViews.showVideoMovie(mMovie);
  }

  @Override
  public void isFavorite(Context context, int auto) {
    DbMovie dbMovie = new DbMovie(context);
    if (dbMovie.isFavorite(mMovie.getId())) {
      mViews.showFavorite();
      if (auto != 0) UtilActivytyHome.showMess(context, UtilError.ADD_FAVORITE);
    }
    else {
      mViews.showUnFavorite();
      if (auto != 0) UtilActivytyHome.showMess(context, UtilError.REMOTE_FAVORITE);
    }
    dbMovie.close();
  }

  @Override
  public void addOrRemoteFavorite(Context context) {
    DbMovie dbMovie = new DbMovie(context);
    if (dbMovie.isFavorite(mMovie.getId())){
      dbMovie.remoteFavorite(mMovie.getId());
      mViews.showUnFavorite();
      UtilActivytyHome.showMess(context,UtilError.REMOTE_FAVORITE);
    }else {
      dbMovie.addFavorite(mMovie);
      mViews.showFavorite();
      UtilActivytyHome.showMess(context,UtilError.ADD_FAVORITE);
    }
    dbMovie.close();
  }


}
