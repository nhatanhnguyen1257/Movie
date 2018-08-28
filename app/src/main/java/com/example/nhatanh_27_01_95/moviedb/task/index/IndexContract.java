package com.example.nhatanh_27_01_95.moviedb.task.index;

import com.example.nhatanh_27_01_95.moviedb.PresenterBase;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

public interface IndexContract {

  public interface Presenter extends PresenterBase{

    public void loadMoviePopular();

    public void loadMovie();

    public void showMovie();

  }

  public interface Views {

    public void showMoviePopular();

    public void showMovíe(ArrayList<Movie> arrayList, ArrayList<Movie> movie) ;

    public void setHightFragmentHead();


  }
}
