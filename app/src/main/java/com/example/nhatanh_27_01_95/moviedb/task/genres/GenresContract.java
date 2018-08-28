package com.example.nhatanh_27_01_95.moviedb.task.genres;

import com.example.nhatanh_27_01_95.moviedb.PresenterBase;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

public interface GenresContract {

  public interface Prisenter extends PresenterBase{

    public void loadArrayGenre();

    public void loadData(Genre genre);

  }

  public interface View{

    public void showAdapterGener(Genre[]arrayGenre);

    public void addFragment(ArrayList<Movie> mMovieArrayList);

    public void showErr(String err);
  }
}
