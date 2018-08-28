package com.example.nhatanh_27_01_95.moviedb.task.activityhome;

import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PresenterHome implements HomeContract.Presenter {

  private HomeContract.View mView;

  public PresenterHome(HomeContract.View view) {
    mView = view;

  }

  @Override
  public void start() {
    mView.showIndex();
  }

  @Override
  public void connectNet() {
    mView.connectNet();
  }

  @Override
  public void disconnectionNet() {
    mView.disconnectionNet();
  }

  @Override
  public void getMovieSearch(String nameMovie) {
    if (UtilActivytyHome.isNetwork){
      try {
        ArrayList<Movie> movies = new JsonMovie(UtilMovies.TITLE_MOVIE_RESULTS).
            execute(UtilMovies.SearchMovie.urlSearchMovie(nameMovie, 1)).get();

        if ( movies != null) mView.showMovieSearch(movies);
        else mView.showEmtyMovie();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }else mView.showDialogNotInternet();
  }
}
