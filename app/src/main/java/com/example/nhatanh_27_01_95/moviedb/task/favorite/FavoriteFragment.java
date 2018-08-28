package com.example.nhatanh_27_01_95.moviedb.task.favorite;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.adapter.favorite.AdapterFavorite;
import com.example.nhatanh_27_01_95.moviedb.task.index.IndexFragment;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteContract.View{

  private FavoritePresenter mFavoritePresenter;

  public FavoriteFragment() {
  }


  @Override
  public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                        Bundle savedInstanceState) {
    mFavoritePresenter = new FavoritePresenter(this);
    return inflater.inflate(R.layout.fragment_favorite, container, false);
  }

  @Override
  public void onResume() {
    super.onResume();
    mFavoritePresenter.showAllListFovarite(getContext(), null);
  }

  @Override
  public void showUnFovarite() {

  }

  @Override
  public void showListForarite(ArrayList<Movie> movies) {
    RecyclerView recycler = getView().findViewById(R.id.recycler_fovarite);
    recycler.setAdapter(new AdapterFavorite(movies, mFavoritePresenter));
    recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    recycler.setHasFixedSize(true);
  }

  @Override
  public void showListMovieOfGenre(ArrayList<Movie> movies, Movie movie) {
    android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction transaction  = manager.beginTransaction();
    transaction.replace(R.id.frame_Main, IndexFragment.init(movies, movie));
    transaction.addToBackStack(null);
    transaction.commit();
  }
}
