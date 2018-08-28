package com.example.nhatanh_27_01_95.moviedb.task.genres;

import android.os.AsyncTask;

import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoadGenre extends AsyncTask<String, Void, Genre[]> {

  @Override
  protected Genre[] doInBackground(String... strings) {
    HttpsURLConnection https = null;
    BufferedReader buf = null;
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
      return getArrayGenre(sb.toString());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (buf != null) {
        try {
          buf.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      https.disconnect();
    }
    return null;
  }

  private Genre[] getArrayGenre(String json){
    try {
      JSONObject jsonObject = new JSONObject(json);
      JSONArray jsonArray = new JSONArray(jsonObject.getString(UtilMovies.Grenre.TITTLE_GENRE));
      Genre []genres = new Genre[jsonArray.length()];
      for (int index = 0; index < jsonArray.length(); index++){
        jsonObject = jsonArray.getJSONObject(index);
        genres[index] = new Genre(
            jsonObject.getString(UtilMovies.Grenre.TITTLE_GENRE_ID),
            jsonObject.getString(UtilMovies.Grenre.TITTLE_GENRE_NAME)
        );
      }
      return genres;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

}
