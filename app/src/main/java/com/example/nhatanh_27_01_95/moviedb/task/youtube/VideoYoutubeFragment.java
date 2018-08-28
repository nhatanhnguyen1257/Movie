package com.example.nhatanh_27_01_95.moviedb.task.youtube;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.adapter.video.AdapterVideo;
import com.example.nhatanh_27_01_95.moviedb.task.Video;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoYoutubeFragment extends Fragment {

  private ArrayList<Video> mVideos;
  private PlayMoviePresenter mPlayMoviePresenter;
  public VideoYoutubeFragment() {
    // Required empty public constructor
  }

  public static VideoYoutubeFragment init(ArrayList<Video> videos, PlayMoviePresenter playMoviePresenter){
    VideoYoutubeFragment videoYoutubeFragment = new VideoYoutubeFragment();
    videoYoutubeFragment.mVideos = videos;
    videoYoutubeFragment.mPlayMoviePresenter = playMoviePresenter;
    return videoYoutubeFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_video_youtube, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    RecyclerView recyclerView = getView().findViewById(R.id.recycler_video);
    recyclerView.setAdapter(new AdapterVideo(mVideos, mPlayMoviePresenter));
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setHasFixedSize(true);
  }
}
