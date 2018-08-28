package com.example.nhatanh_27_01_95.moviedb.task.genres;

import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilError;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GenrePrisenter implements GenresContract.Prisenter {

  private GenresContract.View mView;
  private Genre mGenre = new Genre("","");

  public GenrePrisenter(GenresContract.View view) {
    mView = view;
  }

  @Override
  public void loadArrayGenre() {
    try {
      mView.showAdapterGener(new LoadGenre().execute(UtilMovies.Grenre.URL_GRENRE).get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void loadData(Genre genre) {
    if (UtilActivytyHome.isSearch && !genre.getId().equals(mGenre.getId())){
      mGenre = genre;
      ArrayList<Movie> mMovieArrayList;
      try {
        mMovieArrayList = new JsonMovie(UtilMovies.TITLE_MOVIE_ITEM).execute(UtilMovies.Grenre.urlListGenres(genre.getId())).get();
        if (mMovieArrayList != null)
          if (mMovieArrayList.size() > 0) mView.addFragment(mMovieArrayList);
          else
            mView.showErr(UtilError.ERROR_NULL_MOVIE);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void start() {
    loadArrayGenre();
  }
}
