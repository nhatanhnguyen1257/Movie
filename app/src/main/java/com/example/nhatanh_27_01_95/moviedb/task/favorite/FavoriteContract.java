package com.example.nhatanh_27_01_95.moviedb.task.favorite;

import android.content.Context;

import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

public interface FavoriteContract {

  public interface Presenter {

//    void isFovarite(Movie movie, Context context);

    void addOrRemoteFovarite(Movie movie, Context context);

    void showAllListFovarite(Context context, String idGenre);

    void getListGenreOfMovie( Movie movie);

  }

  public interface View {


    void showUnFovarite();

    public void showListForarite(ArrayList<Movie> movies);

    void showListMovieOfGenre(ArrayList<Movie> movies, Movie movie);
  }

}
