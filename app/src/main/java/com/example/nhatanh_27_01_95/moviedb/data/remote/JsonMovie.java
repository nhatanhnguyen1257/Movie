package com.example.nhatanh_27_01_95.moviedb.data.remote;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.genres.Genre;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class JsonMovie extends AsyncTask<String, Void, ArrayList<Movie>> {

  private String mKeyArray;
  private ProgressBar mProgressBar;

  public JsonMovie(String keyArray) {
    mKeyArray = keyArray;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    UtilActivytyHome.isSearch = false;
  }

  @Override
  protected ArrayList<Movie> doInBackground(String... strings) {
    HttpsURLConnection https = null;
    BufferedReader buf = null;
    try {
      URL url = new URL(strings[0]);
      https = (HttpsURLConnection) url.openConnection();
      https.setRequestMethod("GET");
      https.connect();
      buf = new BufferedReader(new InputStreamReader(https.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String json = "";
      while ((json = buf.readLine()) != null) {
        sb.append(json);
      }
      return getArrayGenre(sb.toString(), mKeyArray);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (buf != null) {
        try {
          if (buf != null) buf.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      https.disconnect();
    }
    return null;
  }

  private ArrayList<Movie> getArrayGenre(String json, String keyArray) {
    ArrayList<Movie> movies = null;
    try {
      JSONObject jsonObject = new JSONObject(json);
      JSONArray jsonArray = new JSONArray(jsonObject.getString(keyArray));
      movies = new ArrayList<>();
      for (int index = 0; index < jsonArray.length(); index++) {
        jsonObject = new JSONObject(jsonArray.getJSONObject(index).toString());
        Movie movie = new Movie(
            jsonObject.getString(UtilMovies.TITLE_MOVIE_ID),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_VOTE_COUNT),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_VOTE_AVERAGE),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_NAME),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_POSTER_PATH),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_BACK_DROP_PATH),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_OVERVIEW),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_RELEASE_DATE),
            jsonObject.getString(UtilMovies.TITLE_MOVIE_POPULARITY),
            null
        );
        String temp = jsonObject.getString(UtilMovies.TITLE_MOVIE_GENRE);
        if (temp.length() > 0) {
          JSONArray js = new JSONArray(temp);
          Genre genre[] = new Genre[js.length()];
          int i = 0;
          for (Genre g : genre) {
            g = new Genre(js.getString(i), "");
            genre[i] = g;
            i++;
          }
          movie.setArrayGenres(genre);
          movies.add(movie);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return movies;
  }

  @Override
  protected void onPostExecute(ArrayList<Movie> movies) {
    super.onPostExecute(movies);
    UtilActivytyHome.isSearch = true;
  }
}
