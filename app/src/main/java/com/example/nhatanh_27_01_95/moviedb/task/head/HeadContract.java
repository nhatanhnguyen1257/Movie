package com.example.nhatanh_27_01_95.moviedb.task.head;

import android.content.Context;
import android.widget.ImageView;

import com.example.nhatanh_27_01_95.moviedb.PresenterBase;
import com.example.nhatanh_27_01_95.moviedb.ViewBase;
import com.example.nhatanh_27_01_95.moviedb.task.DetailMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

public interface HeadContract {

  public interface Presenter extends PresenterBase{

    void loadImage( Context context, ImageView imageView);

    void loadCast();

    void loadMovieOfCast(String idCast);

    void loadMovieGenrer(Movie movie);

    void callDialogDetail();

    void playMovie();

    void isFavorite(Context context, int auto);

    void addOrRemoteFavorite(Context context);

  }

  public interface Views extends ViewBase<Presenter>{

    void showRecylerCast();

    void drawLayout();

    void showImage();

    void showTextOverview(String string);

    void showNameMovie(String nameMovie);

    void showRank(String rank);

    void showCountMovie(String view);

    void showListMovie(ArrayList<Movie> movies);

    void showErr(String err);

    void showTabLayout();

    void showDetailsMovie(DetailMovie detailMovie);

    void showVideoMovie(Movie movie);

    void showFavorite();

    void showUnFavorite();

  }
}
