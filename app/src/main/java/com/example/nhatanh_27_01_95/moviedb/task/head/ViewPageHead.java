package com.example.nhatanh_27_01_95.moviedb.task.head;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

public class ViewPageHead extends FragmentStatePagerAdapter {

  private ArrayList<Movie> mMovieArrayList;

  public ViewPageHead(FragmentManager fm, ArrayList<Movie> movieArrayList) {
    super(fm);
    mMovieArrayList = movieArrayList;
    notifyDataSetChanged();
  }

  @Override
  public Fragment getItem(int position) {
    return HeadFragment.init(mMovieArrayList.get(position));
  }

  @Override
  public int getCount() {
    return mMovieArrayList == null ? 0: mMovieArrayList.size() > 6 ? 6 : mMovieArrayList.size();
  }
}
