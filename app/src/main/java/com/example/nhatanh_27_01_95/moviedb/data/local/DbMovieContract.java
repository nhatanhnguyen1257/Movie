package com.example.nhatanh_27_01_95.moviedb.data.local;

import android.database.sqlite.SQLiteDatabase;

import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

public interface DbMovieContract {

  public interface Prisenter{

    public boolean isFavorite(String idMovie, SQLiteDatabase sqLiteDatabase);

    public void addFavorite(Movie movie, SQLiteDatabase sqLiteDatabase );

    public void remoteFavorite(String idMovie, SQLiteDatabase sqLiteDatabase);

    public ArrayList<Movie> getListFavorite(String idGenre, SQLiteDatabase sqLiteDatabase);

  }

  public interface View{

    public boolean isFavorite(String idMovie);

    public void addFavorite(Movie movie );

    public void remoteFavorite(String idMovie);

    public ArrayList<Movie> getListFavorite(String idGenre);

  }

}
