package com.example.nhatanh_27_01_95.moviedb.data.remote;

import android.os.AsyncTask;

import com.example.nhatanh_27_01_95.moviedb.task.review.Review;
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

public class JsonReview extends AsyncTask<String, Void, ArrayList<Review>> {

  private HttpsURLConnection https = null;
  private BufferedReader buf = null;

  @Override
  protected ArrayList<Review> doInBackground(String... strings) {
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
        if (buf != null) buf.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (https != null) https.disconnect();
    }
    return null;
  }

  private ArrayList<Review> getCastOfMovie(String json){
    ArrayList<Review> arrayList = null;
    try {
      JSONObject jsonObject = new JSONObject(json);
      JSONArray jsonArray = jsonObject.getJSONArray(UtilMovies.TITLE_MOVIE_RESULTS);
      arrayList = new ArrayList<>();
      for (int index = 0; index < jsonArray.length(); index++){

        jsonObject = new JSONObject(jsonArray.getString(index).toString());
        arrayList.add(new Review(jsonObject.getString(UtilMovies.Review.AUTHOR),
                              jsonObject.getString(UtilMovies.Review.CONTENT)));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return arrayList;
  }
}
