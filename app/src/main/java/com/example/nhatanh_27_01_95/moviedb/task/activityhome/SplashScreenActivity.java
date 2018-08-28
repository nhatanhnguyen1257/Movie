package com.example.nhatanh_27_01_95.moviedb.task.activityhome;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {
  private final int SPLASH_DISPLAY_LENGTH = 500;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    new SplashScreen().execute();
  }

  public class SplashScreen extends AsyncTask<Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... voids) {
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      Intent mainIntent = new Intent(SplashScreenActivity.this,HomeActivity.class);
      SplashScreenActivity.this.startActivity(mainIntent);
      SplashScreenActivity.this.finish();
    }
  }
}
