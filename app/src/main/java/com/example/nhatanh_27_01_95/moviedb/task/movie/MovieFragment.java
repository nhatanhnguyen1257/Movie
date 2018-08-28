
package com.example.nhatanh_27_01_95.moviedb.task.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.adapter.movie.AdapterRecyclerMovie;
import com.example.nhatanh_27_01_95.moviedb.task.index.IndexFragment;

import java.util.ArrayList;


public class MovieFragment extends Fragment implements MovieContract.View {

  private int mPage = 1;
  private PresenterMovie mPresenterMovie;
  private ArrayList<Movie> mMovies;

  public MovieFragment() {
  }

  public void setMovies(ArrayList<Movie> movies) {
    mMovies = movies;
  }

  public static Fragment initListFiml(ArrayList<Movie> movieArrayList) {
    MovieFragment list = new MovieFragment();
    list.mMovies = movieArrayList;
    return list;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenterMovie = PresenterMovie.init(mPage, mMovies, this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_list_fiml, container, false);
  }


  @Override
  public void onResume() {
    super.onResume();
    if (mPresenterMovie != null) mPresenterMovie.start();
  }

  @Override
  public void showMovie() {
    RecyclerView recycler = getView().findViewById(R.id.recycler_list_fiml);
    recycler.setAdapter(new AdapterRecyclerMovie(mPresenterMovie.getMovieArrayList(), mPresenterMovie));
    recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    recycler.setHasFixedSize(true);
  }

  @Override
  public void showMovieItem(Movie movie) {
    FragmentManager manager = getActivity().getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.frame_Main, IndexFragment.init(null,movie));
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mMovies = null;
  }

}
