package com.example.nhatanh_27_01_95.moviedb.task.movie;

import com.example.nhatanh_27_01_95.moviedb.PresenterBase;

public interface MovieContract {

  public interface Presenter extends PresenterBase{

    public void loadMovie();

    public void clickItemMovie(Movie movie);

  }

  public interface View {

    public void showMovie();

    public void showMovieItem(Movie movie);
  }
}
