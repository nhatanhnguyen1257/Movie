package com.example.nhatanh_27_01_95.moviedb.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bumptech.glide.request.target.SquaringDrawable;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.genres.Genre;
import com.example.nhatanh_27_01_95.moviedb.util.UtilDbMovie;

import java.util.ArrayList;

public class FavoriteMovie implements DbMovieContract.Prisenter {

  @Override
  public boolean isFavorite(String idMovie, SQLiteDatabase sqLiteDatabase) {
    Cursor cursor = sqLiteDatabase.rawQuery(UtilDbMovie.TableFavorite.isFavorite(idMovie) , null);
    if (cursor.getCount() > 0){
      cursor.close();
      return true;
    }
    cursor.close();
    return false;
  }

  @Override
  public void addFavorite(Movie movie, SQLiteDatabase sqLiteDatabase) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_ID, movie.getId());
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_VOTE_COUNT, movie.getVoteCount());
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_VOTE_AVERAGE, movie.getVoteAverage());
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_NAME, movie.getTitle());
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_IMAGE, movie.getUrlImage());
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_OVERVIEW, movie.getOverview());
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_DATE, movie.getDate());
    contentValues.put(UtilDbMovie.TableMovie.NAME_COL_POPULARITY, movie.getPopularity());
    sqLiteDatabase.insert(UtilDbMovie.NAME_TABLE_MOVIE, null, contentValues);
    contentValues.clear();

  }

  @Override
  public void remoteFavorite(String idMovie, SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.delete(UtilDbMovie.NAME_TABLE_MOVIE, UtilDbMovie.TableMovie.NAME_COL_ID + " like ?",
        new String[]{idMovie});

  }

  @Override
  public ArrayList<Movie> getListFavorite(String idGenre, SQLiteDatabase sqLiteDatabase) {
    Cursor cursorMovie = null;
    if (idGenre == null)
      cursorMovie = sqLiteDatabase.rawQuery( UtilDbMovie.TableFavorite.getAllFavorite(), null);
    else
      cursorMovie = sqLiteDatabase.rawQuery(UtilDbMovie.TableFavorite.getFavorite(idGenre), null);
    return readDatabase(cursorMovie, sqLiteDatabase);
  }

  private ArrayList<Movie> readDatabase(Cursor cursor, SQLiteDatabase sqLiteDatabase){
    ArrayList<Movie> movies = null;
    if (cursor.moveToFirst()){
      movies = new ArrayList<>();
      do{
        Movie movie = new Movie(
            cursor.getString(ColMovie.idMovie.ordinal()),
            cursor.getString(ColMovie.voteCount.ordinal()),
            cursor.getString(ColMovie.voteAverage.ordinal()),
            cursor.getString(ColMovie.name.ordinal()),
            cursor.getString(ColMovie.image.ordinal()),
            cursor.getString(ColMovie.overvew.ordinal()),
            cursor.getString(ColMovie.date.ordinal()),
            cursor.getString(ColMovie.popularity.ordinal())
        );
        movies.add(movie);
      }while (cursor.moveToNext());
    }
    return movies;
  }

  public enum ColMovie {
    idMovie,
    voteCount,
    voteAverage,
    name,
    image,
    overvew,
    date,
    popularity

  }
}
