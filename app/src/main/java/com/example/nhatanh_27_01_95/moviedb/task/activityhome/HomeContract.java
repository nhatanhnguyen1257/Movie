package com.example.nhatanh_27_01_95.moviedb.task.activityhome;

import com.example.nhatanh_27_01_95.moviedb.PresenterBase;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

public class HomeContract {

  public interface Presenter extends PresenterBase{
    void connectNet();
    void disconnectionNet();
    void getMovieSearch(String keyItem);
  }

  public interface View {

    void showIndex();
    void connectNet();
    void disconnectionNet();
    void showMovieSearch(ArrayList<Movie> arrayList);
    void showEmtyMovie();
    void showFovarite();
    void showDialogNotInternet();

  }

}
