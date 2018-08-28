package com.example.nhatanh_27_01_95.moviedb.task.youtube;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.Video;
import com.example.nhatanh_27_01_95.moviedb.task.movie.MovieFragment;
import com.example.nhatanh_27_01_95.moviedb.task.review.ReviewFragment;

import java.util.ArrayList;

public class AdapterMovie extends FragmentStatePagerAdapter {

  private String mIdMovie;
  private String []adapter ={"Review", "Movie", "Trailer"};
  private ArrayList<Movie> mMovies;
  private ArrayList<Video> mVideos;
  private PlayMoviePresenter mPlayMoviePresenter;

  public AdapterMovie(FragmentManager fragmentManager,
                      String idMovie, ArrayList<Movie> movies,
                      ArrayList<Video> videos,
                      PlayMoviePresenter playMoviePresenter ) {
    super(fragmentManager);
    mIdMovie = idMovie;
    mMovies = movies;
    mVideos = videos;
    mPlayMoviePresenter = playMoviePresenter;
  }


  @Override
  public Fragment getItem(int position) {
    switch (position){
      case 0:
        return ReviewFragment.init(mIdMovie);
      case 1:
        return MovieFragment.initListFiml(mMovies);
      default:
        return VideoYoutubeFragment.init(mVideos, mPlayMoviePresenter);
    }
  }

  @Override
  public int getCount() {
    return adapter.length;
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return adapter[position];
  }
}
