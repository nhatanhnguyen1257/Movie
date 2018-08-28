package com.example.nhatanh_27_01_95.moviedb.task.activityhome;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.NavigationView;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.net.conncetion.NetWorkConnection;
import com.example.nhatanh_27_01_95.moviedb.task.favorite.FavoriteFragment;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.index.IndexFragment;
import com.example.nhatanh_27_01_95.moviedb.task.movie.MovieFragment;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilError;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    HomeContract.View, View.OnKeyListener {

  private AutoCompleteTextView mAutoViewSearch;
  private NetWorkConnection mNetWorkConnection;
  private PresenterHome mPresenterHome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    mAutoViewSearch = findViewById(R.id.auto_search);
    mAutoViewSearch.setOnKeyListener(this);
    mPresenterHome = new PresenterHome(this);
    mPresenterHome.connectNet();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mPresenterHome.start();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mPresenterHome.disconnectionNet();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  //TODO
  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_fravate) {
      showFovarite();
    }else  if (id == R.id.nav_home) {
      remoteAllFragmentBackStack();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override
  public boolean onKey(View view, int i, KeyEvent keyEvent) {
    int key = keyEvent.getKeyCode();
    switch (key) {
      case KeyEvent.KEYCODE_ENTER:
        if (UtilActivytyHome.isSearch)
          mPresenterHome.getMovieSearch(mAutoViewSearch.getText().toString());
        else UtilActivytyHome.showMess(this, UtilError.PLEASE_WAIT);

    }
    return false;
  }

  @Override
  public void showIndex() {
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.frame_Main, IndexFragment.init(null,null));
    transaction.commit();
  }

  @Override
  public void connectNet() {
    mNetWorkConnection = new NetWorkConnection();
    mNetWorkConnection.setPresenterHome(this.mPresenterHome);
    IntentFilter intentFilter = new IntentFilter(
        ConnectivityManager.EXTRA_NETWORK_TYPE
    );
    intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    this.registerReceiver(mNetWorkConnection, intentFilter);
  }

  @Override
  public void disconnectionNet() {
    unregisterReceiver(mNetWorkConnection);
  }

  @Override
  public void showMovieSearch(ArrayList<Movie> arrayList) {
    MovieFragment movieFragment = new MovieFragment();
    movieFragment.setMovies(arrayList);
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.frame_Main, movieFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void showEmtyMovie() {
    UtilActivytyHome.showErr(UtilError.ERROR_NULL_MOVIE, this);
  }

  @Override
  public void showFovarite() {
    FavoriteFragment favoriteFragment = new FavoriteFragment();
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.frame_Main, favoriteFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void showDialogNotInternet() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(UtilError.NO_NETWORK);
    builder.show();
  }

  @Override
  public void onPointerCaptureChanged(boolean hasCapture) {

  }

  private void remoteAllFragmentBackStack(){
    Handler handler  = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
      }
    }, 300);

  }


}
