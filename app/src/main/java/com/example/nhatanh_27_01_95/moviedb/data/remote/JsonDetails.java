package com.example.nhatanh_27_01_95.moviedb.data.remote;

import android.os.AsyncTask;

import com.example.nhatanh_27_01_95.moviedb.task.DetailMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class JsonDetails extends AsyncTask<String, Void, DetailMovie> {

  private Movie mMovie;
  private HttpsURLConnection https = null;
  private BufferedReader buf = null;

  public JsonDetails(Movie movie) {
    mMovie = movie;
  }

  @Override
  protected DetailMovie doInBackground(String... strings) {
    try {
      URL url = new URL(strings[0]);
      https = (HttpsURLConnection)url.openConnection();
      https.setRequestMethod("GET");
      https.connect();

      buf = new BufferedReader(new InputStreamReader(https.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String json ="";
      while ((json = buf.readLine()) != null) {
        sb.append(json);
      }
      return getCastOfMovie(sb.toString());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try {
        buf.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      https.disconnect();
    }
    return null;
  }

  private DetailMovie getCastOfMovie(String json){
    DetailMovie detailMovies = null;
    try {
      JSONObject jsonObject = new JSONObject(json);
      detailMovies = new DetailMovie(
          mMovie,
          jsonObject.getString(UtilMovies.Detail.BUDGET),
          jsonObject.getString(UtilMovies.Detail.REVENUE),
          jsonObject.getString(UtilMovies.Detail.RUNTIME));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return detailMovies;
  }

}
