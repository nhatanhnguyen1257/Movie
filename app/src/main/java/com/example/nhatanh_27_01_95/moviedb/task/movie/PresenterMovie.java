package com.example.nhatanh_27_01_95.moviedb.task.movie;

import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonMovie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PresenterMovie implements MovieContract.Presenter {

  private int mPage;
  private MovieContract.View mView;
  private ArrayList<Movie> mMovieArrayList;

  public static PresenterMovie init(int page, ArrayList<Movie> movieArrayList, MovieContract.View view){
    PresenterMovie presenterMovie = new PresenterMovie();
    presenterMovie.mView = view;
    presenterMovie.mPage = page;
    presenterMovie.mMovieArrayList = movieArrayList;
    return presenterMovie;
  }

  public ArrayList<Movie> getMovieArrayList() {
    return mMovieArrayList;
  }

  @Override
  public void loadMovie() {
    if (mMovieArrayList == null){
      try {
        mMovieArrayList = new JsonMovie(UtilMovies.TITLE_MOVIE_RESULTS).execute(UtilMovies.Popular.popularMovie(mPage == 0 ? 1 : mPage)).get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    mView.showMovie();
  }

  @Override
  public void clickItemMovie(Movie movie) {
    mView.showMovieItem(movie);
  }


  @Override
  public void start() {
    loadMovie();
  }
}
