package com.example.nhatanh_27_01_95.moviedb.util;

import android.widget.ImageView;

import com.example.nhatanh_27_01_95.moviedb.task.genres.Genre;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.Calendar;
import java.util.Date;

public class UtilDbMovie {

  public static final String NAME_BD_MOVIE =  "DB_MOVIE";
  public static final int VERSION = 1;

  public static final String NAME_TABLE_MOVIE = "movie";
  public final static String NAME_TABLE_FAVORITE = "favorite";

  public static class TableMovie{

    public static final String NAME_COL_ID = "id";
    public static final String NAME_COL_NAME = "name";
    public static final String NAME_COL_IMAGE = "iamge";
    public static final String NAME_COL_DATE= "date";
    public static final String NAME_COL_VOTE_COUNT= "voteCount";
    public static final String NAME_COL_VOTE_AVERAGE= "voteAverage";
    public static final String NAME_COL_OVERVIEW= "overview";
    public static final String NAME_COL_POPULARITY= "popularity";


    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+NAME_TABLE_MOVIE+" (" +
                                            " "+ TableMovie.NAME_COL_ID+" text NOT NULL , " +
                                            " "+ TableMovie.NAME_COL_VOTE_COUNT+" text NOT NULL , " +
                                            " "+ TableMovie.NAME_COL_VOTE_AVERAGE+" text NOT NULL , " +
                                            " "+TableMovie.NAME_COL_NAME+" text NOT NULL," +
                                            " "+TableMovie.NAME_COL_IMAGE+" text , " +
                                            " "+TableMovie.NAME_COL_OVERVIEW+" text , " +
                                            " "+TableMovie.NAME_COL_DATE+" text NOT NULL , " +
                                            " "+TableMovie.NAME_COL_POPULARITY+" text NOT NULL , " +
                                            " PRIMARY KEY ( "+TableMovie.NAME_COL_ID+" ) " +
                                            ");";

    public static String insertInto(Movie movie){
      return "INSERT INTO "+ NAME_TABLE_MOVIE + " VALUES ( " +
          "'" + movie.getId() + "' , " +
          "'"+ movie.getVoteCount() +"' ," +
          "'"+ movie.getVoteAverage() +"' ," +
          "'"+ movie.getTitle() +"' ," +
          "'"+ (movie.getUrlImage().length() <= 4 ? movie.getUrlImage() : movie.getBackdropPath())+"' ," +
          "'"+ movie.getOverview() +"' ," +
          "'"+ movie.getDate() +"' ," +
          " '"+ movie.getPopularity()+"' )";
    }
  }

  public static class TableFavorite{

    public final static String NAME_COL_ID = "id";
    public final static String NAME_COL_MOVIE = "idMovie";
    public final static String NAME_COL_GENRE = "idGenre";

    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+NAME_TABLE_FAVORITE+" ( " +
        " "+TableFavorite.NAME_COL_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
        " "+TableFavorite.NAME_COL_MOVIE+" text NOT NULL, " +
        " "+TableFavorite.NAME_COL_GENRE+" text NOT NULL, " +
        " FOREIGN KEY( "+TableFavorite.NAME_COL_MOVIE+" ) REFERENCES" + " "+NAME_TABLE_MOVIE+"" +
        " ( "+TableMovie.NAME_COL_ID+" ) " + ");";

    public static final String isFavorite(String idMovie){
      return "SELECT "+TableMovie.NAME_COL_ID+" FROM "+ NAME_TABLE_MOVIE +
              " WHERE "+TableMovie.NAME_COL_ID+" LIKE '"+ idMovie+"' " ;
//      return "SELECT "+TableFavorite.NAME_COL_GENRE+" FROM "+ NAME_TABLE_FAVORITE +" WHERE "+TableFavorite.NAME_COL_MOVIE+" LIKE '"+ idMovie+"' " ;
    }

    public static String insertInto(String idMovie, String idGenre){
      return "INSERT INTO "+ NAME_TABLE_FAVORITE +"( "+NAME_COL_MOVIE+" , "+NAME_COL_GENRE+" ) " +
          "VALUES " + "( '" + idMovie +"' , '"+ idGenre +"' ) ";
    }

    public static String getFavorite(String idGrenre){
      return "SELECT " +
          "m."+TableMovie.NAME_COL_ID+", " +
          "m."+TableMovie.NAME_COL_VOTE_COUNT+", " +
          "m."+TableMovie.NAME_COL_VOTE_AVERAGE+", " +
          "m."+TableMovie.NAME_COL_NAME+", " +
          "m."+TableMovie.NAME_COL_IMAGE+"," +
          "m."+TableMovie.NAME_COL_OVERVIEW+", " +
          "m."+TableMovie.NAME_COL_DATE+ ", "+
          "m."+TableMovie.NAME_COL_POPULARITY +
          " from "+NAME_TABLE_MOVIE+"  m " +
          " join "+NAME_TABLE_FAVORITE+"  f on f."+TableFavorite.NAME_COL_MOVIE+" = m.id " +
          " where f."+TableFavorite.NAME_COL_GENRE+" like '"+idGrenre+"' ";
    }

    public static String getAllFavorite(){
      return "SELECT * FROM "+ NAME_TABLE_MOVIE;
    }

    public static String getAllGenre(String idMovie){
      return "SELECT "+TableFavorite.NAME_COL_GENRE+" FROM "+ NAME_TABLE_FAVORITE
          + " WHERE "+ TableFavorite.NAME_COL_GENRE +" like '" + idMovie +"'";
    }
  }
}
