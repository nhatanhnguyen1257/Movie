package com.example.nhatanh_27_01_95.moviedb.task.head;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.adapter.head.AdapterCast;
import com.example.nhatanh_27_01_95.moviedb.task.DetailMovie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.MovieFragment;
import com.example.nhatanh_27_01_95.moviedb.task.youtube.PlayMovieFragment;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilError;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadFragment extends Fragment implements HeadContract.Views, View.OnClickListener {

  private ImageView mImageLove;
  private ImageView mImagePlay;

  private TextView mTextView;

  private HeadPresenter mHeadPresenter;
  private Movie mMovie;

  public static HeadFragment init(Movie movie) {
    HeadFragment headFragment = new HeadFragment();
    headFragment.mMovie = movie;
    return headFragment;
  }

  public HeadFragment() {
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_head, container, false);
    mImageLove = view.findViewById(R.id.image_love);
    mImagePlay = view.findViewById(R.id.image_play);
    mTextView = view.findViewById(R.id.text_view);
    mImagePlay.setOnClickListener(this);
    mImageLove.setOnClickListener(this);
    mHeadPresenter = new HeadPresenter(mMovie, this);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mHeadPresenter.start();
    mHeadPresenter.isFavorite(getContext(), 0);
  }

  @Override
  public void showRecylerCast() {
    RecyclerView recycler = getView().findViewById(R.id.recycler_imageActor);
    AdapterCast adapterCast = new AdapterCast(mHeadPresenter.getCast(), mHeadPresenter);
    recycler.setAdapter(adapterCast);
    recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    recycler.setHasFixedSize(true);
  }

  public void drawLayout() {
    final ConstraintLayout constrain = getView().findViewById(R.id.constraint_love);
    constrain.post(new Runnable() {
      @Override
      public void run() {

        float mMaxX = constrain.getWidth();
        float mMaxY = constrain.getHeight();
        float temp;
        temp = mImageLove.getWidth();

        mImageLove.setX(mMaxX / 4);
        mImageLove.setY(mMaxY * 0.65f);

        mTextView.setY(mMaxY * 0.65f);

        temp = mImagePlay.getWidth();
        mImagePlay.setX(mMaxX * 3 / 4 - temp / 4);
        mImagePlay.setY(mMaxY / 4 - temp / 4);
      }
    });
  }

  @Override
  public void showImage() {
    mHeadPresenter.loadImage(getContext(), (ImageView) getView().findViewById(R.id.image_postMovie));
  }

  @Override
  public void showTextOverview(String string) {
    TextView text = getView().findViewById(R.id.textext_info);
    text.setText(string);
  }

  @Override
  public void showNameMovie(String nameMovie) {
    TextView textName = getView().findViewById(R.id.text_nameMovie);
    textName.setText(nameMovie);
  }

  @Override
  public void showRank(String rank) {
    TextView textRank = getView().findViewById(R.id.text_voteAverage);
    textRank.setText(rank);
  }

  @Override
  public void showCountMovie(String view) {
    mTextView.setText(getResources().getString(R.string.title_view) + view);
  }

  @Override
  public void showListMovie(ArrayList<Movie> movies) {
    MovieFragment movieFragment = new MovieFragment();
    movieFragment.setMovies(movies);
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.frame_Main, movieFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void showErr(String err) {
    UtilActivytyHome.showErr(err, getContext());
  }

  @Override
  public void showTabLayout() {
    TabLayout tabLayout = getView().findViewById(R.id.tab_movie);
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
          case 1:
            if (UtilActivytyHome.isSearch) mHeadPresenter.loadMovieGenrer(mMovie);
            else UtilActivytyHome.showMess(getContext(), UtilError.PLEASE_WAIT);
            break;
        }
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
          case 0:
            mHeadPresenter.callDialogDetail();
            break;
        }
      }
    });
  }

  @Override
  public void showDetailsMovie(DetailMovie detailMovie) {
    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setPositiveButton(R.string.action_done, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
      }
    });
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.diag_detail_movie, null);
    builder.setView(view);

    TextView textNameMovie = view.findViewById(R.id.text_nameMovie);
    TextView textOverview = view.findViewById(R.id.text_overview);
    TextView textRuntime = view.findViewById(R.id.text_runtime);
    TextView textPopularity = view.findViewById(R.id.text_popularity);
    TextView textBudget = view.findViewById(R.id.text_budget);

    textNameMovie.setText(detailMovie.getTitle());
    textOverview.setText(detailMovie.getOverview());
    textRuntime.setText(detailMovie.getRuntime());
    textPopularity.setText(detailMovie.getPopularity());
    textBudget.setText(detailMovie.getBudget());

    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }

  @Override
  public void showVideoMovie(Movie movie) {
    android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.frame_Main, PlayMovieFragment.init(movie));
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void showFavorite() {
    mImageLove.setBackgroundResource(R.drawable.ic_like);
  }

  @Override
  public void showUnFavorite() {
    mImageLove.setBackgroundResource(R.drawable.ic_unlike);
  }


  @Override
  public void onDetach() {
    super.onDetach();
    mMovie = null;
  }

  @Override
  public void setPresenter(HeadContract.Presenter presenter) {

  }

  @Override
  public void onClick(View view) {
    int id = view.getId();
    switch (id){
      case R.id.image_play:
        mHeadPresenter.playMovie();
        break;
      case R.id.image_love:
        mHeadPresenter.addOrRemoteFavorite(getContext());
        break;
    }
  }
}
