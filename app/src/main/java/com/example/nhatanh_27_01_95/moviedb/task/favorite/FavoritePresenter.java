package com.example.nhatanh_27_01_95.moviedb.task.favorite;

import android.content.Context;

import com.example.nhatanh_27_01_95.moviedb.data.local.DbMovie;
import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FavoritePresenter implements FavoriteContract.Presenter {

  private FavoriteContract.View mView;

  public FavoritePresenter(FavoriteContract.View view) {
    mView = view;
  }

//  @Override
//  public void isFovarite(Movie movie, Context context) {
//    DbMovie dbMovie = new DbMovie(context);
//    if (dbMovie.isFavorite(movie.getId()))mView.showFovarite();
//    else mView.showUnFovarite();
//    dbMovie.close();
//  }

  @Override
  public void addOrRemoteFovarite(Movie movie, Context context) {
    DbMovie dbMovie = new DbMovie(context);
    dbMovie.remoteFavorite(movie.getId());
    mView.showUnFovarite();
    dbMovie.close();
  }

  @Override
  public void showAllListFovarite(Context context, String IdGenre) {
    ArrayList<Movie> movies = null;
    DbMovie dbMovie = new DbMovie(context);
    movies = dbMovie.getListFavorite(IdGenre);
    dbMovie.close();
    mView.showListForarite(movies);
  }

  @Override
  public void getListGenreOfMovie(Movie movie) {
    try {
      ArrayList<Movie> arrayList = new JsonMovie(UtilMovies.TITLE_MOVIE_RESULTS).
          execute(UtilMovies.SearchMovie.urlMovieGenrer(movie.getId(), 1)).get();
      mView.showListMovieOfGenre(arrayList, movie);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}
