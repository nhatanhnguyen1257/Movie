package com.example.nhatanh_27_01_95.moviedb.util;

import android.os.ParcelUuid;
import android.util.Log;

import com.example.nhatanh_27_01_95.moviedb.BuildConfig;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UtilMovies {

  private final static String WEB = "https://api.themoviedb.org/3/";
  private static final String LANGUAGE = "&language=en-US";
  public static final String TITLE_MOVIE_RESULTS = "results";
  public static final String TITLE_MOVIE_VOTE_COUNT ="vote_count";
  public static final String TITLE_MOVIE_ID = "id";
  public static final String TITLE_MOVIE_ITEM = "items";
  public static final String TITLE_MOVIE_VOTE_AVERAGE = "vote_average";
  public static final String TITLE_MOVIE_POPULARITY=  "popularity";
  public static final String TITLE_MOVIE_POSTER_PATH ="poster_path";
  public static final String TITLE_MOVIE_NAME = "original_title";
  public static final String TITLE_MOVIE_GENRE = "genre_ids";
  public static final String TITLE_MOVIE_OVERVIEW = "overview";
  public static final String TITLE_MOVIE_RELEASE_DATE = "release_date";
  public static final String TITLE_MOVIE_BACK_DROP_PATH = "backdrop_path";


  public static class SearchMovie{

    private static final String WEB_URL = WEB+"search/movie?api_key="+ BuildConfig.KEY_MOVIE;
    private static final String WEB_IMAGE ="https://image.tmdb.org/t/p/w500";

    /**
     *
     * @param nameMovie : tên bộ phim muốn tìm kiếm
     * @param page: trang tìm kiếm.
     * @return: đường dẫn tìm theo tên phim và vị trí trang
     */
    public static String urlSearchMovie( String nameMovie, int page){
      String name = "";
      try {
        name = URLEncoder.encode(nameMovie, "utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      return WEB_URL+LANGUAGE +"&query="+name+"&page="+page +"&include_adult=false";
    }

    /**
     * trả về danh sách bộ phim cùng loại mới bộ phim chco trước.
     * @param idMovie : idMovie
     * @param page:
     * @return
     */
    public static String urlMovieGenrer(String idMovie, int page){
      return WEB + "movie/"+ idMovie + "/similar?api_key="+BuildConfig.KEY_MOVIE+LANGUAGE+"&page="+page;
    }

    public static String urlImage(String urlImage){
      return WEB_IMAGE+urlImage;
    }


    public static String movieOfCast(String idCase){
      return WEB + "person/"+ idCase + "/movie_credits?api_key=" + BuildConfig.KEY_MOVIE + LANGUAGE;
    }

    //https://api.themoviedb.org/3/movie/<idMovie>?api_key=d1b9d9f9fe789de3b66646884651cbe7&language=en-US
    public static String movieDetails(String idMovie){
      return WEB + "movie/"+ "?api_key=" + BuildConfig.KEY_MOVIE + LANGUAGE;
    }

  }

  public static class Youtube{

    public static final String KEY = "key";
    public static final String NAME = "name";
    private static final String YOU_TU_BE_URL = "https://www.youtube.com/watch?v=";

    public static String urlKeyYoutube(String idMovie){
      return WEB + "movie/"+idMovie + "/videos?api_key=" +BuildConfig.KEY_MOVIE + LANGUAGE;
    }

    public static String urlPlayYoutube(String key){
      return YOU_TU_BE_URL + key;
    }

    public static String urlImageYoutube(String key){
      return "https://img.youtube.com/vi/"+key+"/0.jpg";
    }
  }

  public static class Review{

    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";

    //https://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=d1b9d9f9fe789de3b66646884651cbe7&language=en-US&page=1
    public static String urlReview(String idMovie){
      return WEB + "movie/" + idMovie + "/reviews?api_key=" +BuildConfig.KEY_MOVIE + LANGUAGE;
    }

  }


  public static class Grenre{
    //https://api.themoviedb.org/3/genre/movie/list?api_key=d1b9d9f9fe789de3b66646884651cbe7&language=en-US
    public static final String URL_GRENRE = WEB +
        "genre/movie/list?api_key="+BuildConfig.KEY_MOVIE+LANGUAGE;

    public static final String TITTLE_GENRE = "genres";
    public static final String TITTLE_GENRE_ID = "id";
    public static final String TITTLE_GENRE_NAME = "name";

    //https://api.themoviedb.org/3/list/28?api_key=d1b9d9f9fe789de3b66646884651cbe7&language=en-US

    public static String urlListGenres(String key){
      return WEB + "list/" + key +"?api_key="+BuildConfig.KEY_MOVIE +LANGUAGE;
    }


  }

  public static class Popular{

    public static String popularMovie(int page){
      return WEB+"movie/popular?api_key="+ BuildConfig.KEY_MOVIE+LANGUAGE+"&page="+page;
    }
  }

  public static class Cast{
    public static String CAST_TIEM = "cast";
    public static String CAST_CHARACTER = "character";
    public static String CAST_ID = "id";
    public static String CAST_NAME = "name";
    public static String CAST_PROFILE_PATH = "profile_path";

    /**
     * tiềm kiếm diễn viên theo tên của bộ phim
     * @param idMovie: id bộ phim muốn tìm kiếm
     * @return
     */
    public static String urlCastOffMovie(String idMovie){
      return WEB+"movie/"+idMovie+"/credits?api_key="+BuildConfig.KEY_MOVIE;
    }

  }

  public static class Detail{
    public static  final String BUDGET = "budget";
    public static  final String REVENUE = "revenue";
    public static  final String RUNTIME = "runtime";

    public static String urlDetailMovie(String idMovie){
      return WEB + "movie/" + idMovie + "?api_key=" + BuildConfig.KEY_MOVIE + LANGUAGE;
    }
  }

}
