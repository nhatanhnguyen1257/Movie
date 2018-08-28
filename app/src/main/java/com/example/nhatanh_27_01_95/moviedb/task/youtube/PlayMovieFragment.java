package com.example.nhatanh_27_01_95.moviedb.task.youtube;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nhatanh_27_01_95.moviedb.BuildConfig;
import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMovieFragment extends android.support.v4.app.Fragment
    implements  PlayMovieContract.View, View.OnClickListener, YouTubePlayer.OnInitializedListener {

  private Movie mMovie;
  private PlayMoviePresenter mPlayMoviePresenter;
  private YouTubePlayerSupportFragment mYouTubePlayerSupportFragment;
  private YouTubePlayer mYouTubePlayer;

  public PlayMovieFragment() {
  }

  public static PlayMovieFragment init(Movie movie){
    PlayMovieFragment playMovieFragment = new PlayMovieFragment();
    playMovieFragment.mMovie = movie;
    return playMovieFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view;
    view = inflater.inflate(R.layout.fragment_play_movie, container, false);
    ImageView imageView = view.findViewById(R.id.image_like);
    imageView.setOnClickListener(this);
    mYouTubePlayerSupportFragment = new YouTubePlayerSupportFragment();
    android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_youtube, mYouTubePlayerSupportFragment);
    fragmentTransaction.commit();
    mYouTubePlayerSupportFragment.initialize(BuildConfig.KEY_YOUTUBE, this);
    mPlayMoviePresenter = new PlayMoviePresenter(this, mMovie);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mPlayMoviePresenter.start();
    mPlayMoviePresenter.searchReview();
    mPlayMoviePresenter.isFavorite(getContext());
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
  }


  @Override
  public void showViewPageMovie() {
    ViewPager viewPager = getView().findViewById(R.id.viewpage_movie);
    TabLayout tabLayout = getView().findViewById(R.id.tabLayout_movie);
    android.support.v4.app.FragmentManager fragmentManager = getChildFragmentManager();

    viewPager.setAdapter(
        new AdapterMovie(
                fragmentManager,
                mMovie.getId(),
                mPlayMoviePresenter.getMovies(),
                mPlayMoviePresenter.getVideos(),
                mPlayMoviePresenter));

    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  public void showFavorite() {
    ImageView imageView = getView().findViewById(R.id.image_like);
    imageView.setBackgroundResource(R.drawable.ic_like);
  }

  @Override
  public void showUnFavorite() {
    ImageView imageView = getView().findViewById(R.id.image_like);
    imageView.setBackgroundResource(R.drawable.ic_unlike);
  }

  @Override
  public void showMess(String mess) {
    UtilActivytyHome.showMess(getContext(), mess);
  }

  @Override
  public void playVideo(String key) {
    mYouTubePlayer.loadVideo(key);
  }

  @Override
  public void onClick(View view) {
    int id = view.getId();
    switch (id){
      case R.id.image_like:
        mPlayMoviePresenter.addOrRemoteFavorite(getContext());
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    mYouTubePlayerSupportFragment.onPause();
  }

  @Override
  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
    mYouTubePlayer = youTubePlayer;
    if (!b) {
      if (mPlayMoviePresenter.getVideos().size() >0)
          mYouTubePlayer.loadVideo(mPlayMoviePresenter.getVideos().get(0).getId());
    }
  }

  @Override
  public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
    youTubeInitializationResult.getErrorDialog(getActivity(), 1);
  }

}
