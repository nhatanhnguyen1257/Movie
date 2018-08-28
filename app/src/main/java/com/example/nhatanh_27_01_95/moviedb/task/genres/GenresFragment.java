package com.example.nhatanh_27_01_95.moviedb.task.genres;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.adapter.head.AdaterGenre;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.MovieFragment;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenresFragment extends Fragment implements GenresContract.View {

  private GenrePrisenter mGenrePrisenter ;

  public GenresFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_genres, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mGenrePrisenter = new GenrePrisenter(this);
  }

  @Override
  public void onStart() {
    super.onStart();
    mGenrePrisenter = new GenrePrisenter(this);
    mGenrePrisenter.start();
  }

  @Override
  public void showAdapterGener(Genre[] arrayGenre) {
    RecyclerView recyclerView = getView().findViewById(R.id.recycler_genres);
    recyclerView.setAdapter(AdaterGenre.init(arrayGenre, mGenrePrisenter));
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
  }

  @Override
  public void addFragment(ArrayList<Movie> movieArrayList) {

    MovieFragment movieFragment = new MovieFragment();
    movieFragment.setMovies(movieArrayList);
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.frame_Main, movieFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void showErr(String err) {
    UtilActivytyHome.showErr(err, getContext());
  }

}
