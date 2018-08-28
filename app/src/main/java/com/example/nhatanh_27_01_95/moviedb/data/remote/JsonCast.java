package com.example.nhatanh_27_01_95.moviedb.data.remote;

import android.os.AsyncTask;

import com.example.nhatanh_27_01_95.moviedb.task.Cast;
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

public class JsonCast extends AsyncTask<String, Void, ArrayList<Cast>> {

  private HttpsURLConnection https = null;
  private BufferedReader buf = null;

  @Override
  protected ArrayList<Cast> doInBackground(String... strings) {
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

  private ArrayList<Cast> getCastOfMovie(String json){
    ArrayList<Cast> casts = null;
    try {
      JSONObject jsonObject = new JSONObject(json);
      JSONArray jsonArray = new JSONArray(jsonObject.getString(UtilMovies.Cast.CAST_TIEM));
      casts = new ArrayList<>();
      for (int index = 0; index < jsonArray.length(); index++){
        jsonObject = jsonArray.getJSONObject(index);
        casts.add(new Cast(
            jsonObject.getString(UtilMovies.Cast.CAST_ID),
            jsonObject.getString(UtilMovies.Cast.CAST_NAME),
            jsonObject.getString(UtilMovies.Cast.CAST_PROFILE_PATH),
            jsonObject.getString(UtilMovies.Cast.CAST_CHARACTER)
        ));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return casts;
  }

}
