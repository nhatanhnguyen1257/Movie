package com.example.nhatanh_27_01_95.moviedb.data.remote;

import android.os.AsyncTask;

import com.example.nhatanh_27_01_95.moviedb.task.Video;
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

public class JsonVideo extends AsyncTask<String, Void, ArrayList<Video>> {

  private HttpsURLConnection https = null;
  private BufferedReader buf = null;

  @Override
  protected ArrayList<Video> doInBackground(String... strings) {
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

  private ArrayList<Video> getCastOfMovie(String json){
    ArrayList<Video> arrayList = null;
    try {
      JSONObject jsonObject = new JSONObject(json);
      JSONArray jsonArray = jsonObject.getJSONArray(UtilMovies.TITLE_MOVIE_RESULTS);
      arrayList = new ArrayList<>();
      for (int index = 0; index < jsonArray.length(); index++){

        jsonObject = jsonArray.getJSONObject(index);
        arrayList.add(
                      new Video(jsonObject.getString(UtilMovies.Youtube.KEY),
                        jsonObject.getString(UtilMovies.Youtube.NAME)));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return arrayList;
  }
}
