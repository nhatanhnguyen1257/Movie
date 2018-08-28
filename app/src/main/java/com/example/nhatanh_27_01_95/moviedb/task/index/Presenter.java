package com.example.nhatanh_27_01_95.moviedb.task.index;

import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Presenter implements IndexContract.Presenter {

  private int mPage;
  private Movie mMovie;
  private IndexContract.Views mView;
  private ArrayList<Movie> mMovieArrayList;

  public static Presenter init(int page, ArrayList<Movie> movieArrayList, Movie movie, IndexContract.Views view){
    Presenter presenter = new Presenter();
    presenter.mView = view;
    presenter.mPage = page;
    presenter.mMovie = movie;
    presenter.mMovieArrayList = movieArrayList;
    return presenter;
  }

  public ArrayList<Movie> getMovieArrayList() {
    return mMovieArrayList;
  }
  public Movie getMovie(){
    return mMovie;
  }

  @Override
  public void loadMoviePopular() {
    if (mMovieArrayList == null){
      try {
        mMovieArrayList = new JsonMovie(UtilMovies.TITLE_MOVIE_RESULTS).execute(UtilMovies.Popular.popularMovie(mPage == 0 ? 1 : mPage)).get();
        mView.showMoviePopular();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public void loadMovie() {
    try {
      mMovieArrayList = new JsonMovie(UtilMovies.TITLE_MOVIE_RESULTS).
          execute(UtilMovies.SearchMovie.
              urlMovieGenrer(mMovie.getId(), 1)).get();
      ArrayList<Movie> movies = new ArrayList<>();
      movies.add(mMovie);

      mView.showMovíe(mMovieArrayList, movies);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void showMovie() {
    if (mMovie == null)
      loadMoviePopular();
    else loadMovie();
  }

  @Override
  public void start() {
    mView.setHightFragmentHead();

  }
}
