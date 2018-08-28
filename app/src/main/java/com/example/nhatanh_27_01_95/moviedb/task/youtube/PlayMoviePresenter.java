package com.example.nhatanh_27_01_95.moviedb.task.youtube;

import android.content.Context;

import com.example.nhatanh_27_01_95.moviedb.data.local.DbMovie;
import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonMovie;
import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonReview;
import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonVideo;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.Video;
import com.example.nhatanh_27_01_95.moviedb.task.review.Review;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilError;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PlayMoviePresenter implements PlayMovieContract.Presenter {

  private Movie mMovie;
  private PlayMovieContract.View mView;
  private ArrayList<Review> mReviewArrayList;
  private ArrayList<Movie> mMovies;
  private ArrayList<Video> mVideos;

  public PlayMoviePresenter(PlayMovieContract.View view, Movie movie) {
    mView = view;
    mMovie = movie;
    mVideos = new ArrayList<>();
  }

  public ArrayList<Review> getReviewArrayList() {
    return mReviewArrayList;
  }

  public ArrayList<Movie> getMovies() {
    return mMovies;
  }

  public ArrayList<Video> getVideos() {
    return mVideos;
  }

  @Override
  public void searchKeyMovie() {
    try {
      mVideos = new JsonVideo().execute(UtilMovies.Youtube.urlKeyYoutube(mMovie.getId())).get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void searchListMovie() {
    try {
      mMovies = new JsonMovie(UtilMovies.TITLE_MOVIE_RESULTS).
          execute(UtilMovies.SearchMovie.urlMovieGenrer(mMovie.getId(), 1 )).get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void searchReview() {
    try {
      mReviewArrayList = new JsonReview().execute(UtilMovies.Review.urlReview(mMovie.getId())).get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void isFavorite(Context context) {
    DbMovie dbMovie = new DbMovie(context);
    if (dbMovie.isFavorite(mMovie.getId()))      mView.showFavorite();
    else mView.showUnFavorite();
    dbMovie.close();
  }

  @Override
  public void addOrRemoteFavorite(Context context) {
    DbMovie dbMovie = new DbMovie(context);
    if (dbMovie.isFavorite(mMovie.getId())){
      dbMovie.remoteFavorite(mMovie.getId());
      mView.showUnFavorite();
      mView.showMess(UtilError.REMOTE_FAVORITE);
    }else{
      dbMovie.addFavorite(mMovie);
      mView.showFavorite();
      mView.showMess(UtilError.ADD_FAVORITE);
    }
    dbMovie.close();
  }

  @Override
  public void youtubePlay(String key) {
    mView.playVideo(key);
  }

  @Override
  public void start() {
    searchReview();
    searchKeyMovie();
    searchListMovie();
    mView.showViewPageMovie();
  }
}
