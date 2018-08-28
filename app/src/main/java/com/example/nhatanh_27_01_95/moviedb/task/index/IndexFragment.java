package com.example.nhatanh_27_01_95.moviedb.task.index;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.task.movie.MovieFragment;
import com.example.nhatanh_27_01_95.moviedb.task.head.ViewPageHead;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment implements IndexContract.Views{

  private NestedScrollView mLayoutHead;
  private ViewPager mViewPagerHead;


  private ArrayList<Movie> mMovieArrayList;
  private Movie mMovie;
  private Presenter mPresenter;

  public IndexFragment() {
  }

  public void setMovieArrayList(ArrayList<Movie> movieArrayList) {
    mMovieArrayList = movieArrayList;
  }

  public static IndexFragment init(ArrayList<Movie> movieArrayList, Movie movie){
    if ( movie != null) {
      IndexFragment indexFragment = new IndexFragment();
      indexFragment.mMovieArrayList = movieArrayList;
      indexFragment.mMovie = movie;
      return indexFragment;
    }
    return  new IndexFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_index, container, false);
    mLayoutHead =  view.findViewById(R.id.layout_head);

    mViewPagerHead = view.findViewById(R.id.viewpage_head);
    mPresenter = Presenter.init(0, mMovieArrayList, mMovie,this);
    mPresenter.start();
    return view;
  }


  @Override
  public void onResume() {
    super.onResume();
    mPresenter.showMovie();
  }

  @Override
  public void showMoviePopular() {
    MovieFragment movieFragment = new MovieFragment();
    movieFragment.setMovies(mPresenter.getMovieArrayList());
    android.support.v4.app.FragmentManager manager = getFragmentManager();
    android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.frame_movie, movieFragment);
    transaction.commit();
    mViewPagerHead.setAdapter(new ViewPageHead(getChildFragmentManager(),  mPresenter.getMovieArrayList()));
  }

  @Override
  public void showMovíe(ArrayList<Movie> arrayList, ArrayList<Movie> movie) {
    MovieFragment movieFragment = new MovieFragment();
    movieFragment.setMovies(arrayList);
    android.support.v4.app.FragmentManager manager = getFragmentManager();
    android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.frame_movie, movieFragment);
    transaction.commit();
    mViewPagerHead.setAdapter(new ViewPageHead(getChildFragmentManager(), movie));
  }

  @Override
  public void setHightFragmentHead() {
    mLayoutHead.post(new Runnable() {
      @Override
      public void run() {
        int height = mLayoutHead.getHeight();
        int width = mViewPagerHead.getWidth();
        mViewPagerHead.setLayoutParams(
            new LinearLayout.LayoutParams(width,
                height));
      }
    });
  }


}
