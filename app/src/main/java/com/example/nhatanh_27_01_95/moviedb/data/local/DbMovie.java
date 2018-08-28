package com.example.nhatanh_27_01_95.moviedb.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilDbMovie;

import java.util.ArrayList;

public class DbMovie extends SQLiteOpenHelper implements DbMovieContract.View {

  private FavoriteMovie mFavoriteMovie;

  public DbMovie(Context context) {
    super(context, UtilDbMovie.NAME_BD_MOVIE, null, UtilDbMovie.VERSION);
    mFavoriteMovie = new FavoriteMovie();
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(UtilDbMovie.TableMovie.CREATE_TABLE);
//    sqLiteDatabase.execSQL(UtilDbMovie.TableFavorite.CREATE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }


  @Override
  public boolean isFavorite(String idMovie) {
    return mFavoriteMovie.isFavorite(idMovie, getReadableDatabase());
  }

  @Override
  public void addFavorite(Movie movie) {
    mFavoriteMovie.addFavorite(movie,  this.getWritableDatabase());
  }

  @Override
  public void remoteFavorite(String idMovie) {
    mFavoriteMovie.remoteFavorite(idMovie, getWritableDatabase());
  }

  @Override
  public ArrayList<Movie> getListFavorite(String idGenre) {
    return mFavoriteMovie.getListFavorite(idGenre, getReadableDatabase());
  }
}
