package com.example.nhatanh_27_01_95.moviedb.adapter.video;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.data.remote.LoadImage;
import com.example.nhatanh_27_01_95.moviedb.task.Video;
import com.example.nhatanh_27_01_95.moviedb.task.youtube.PlayMoviePresenter;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.ViewHolde> {

  private ArrayList<Video> mVideos;
  private PlayMoviePresenter mPlayMoviePresenter;
  public AdapterVideo(ArrayList<Video> videos, PlayMoviePresenter playMoviePresenter) {
    mVideos = videos;
    mPlayMoviePresenter = playMoviePresenter;
  }

  @NonNull
  @Override
  public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new ViewHolde(inflater.inflate(R.layout.item_video, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
    final Video video = mVideos.get(position);
    if (video != null){
      new LoadImage(holder.itemView.getContext(), holder.mImageVideo).
          execute(UtilMovies.Youtube.urlImageYoutube(video.getId()));

      holder.mTextNameVideo.setText(video.getName());
      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          mPlayMoviePresenter.youtubePlay(video.getId());
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return mVideos == null ? 0 : mVideos.size();
  }

  public static class ViewHolde extends RecyclerView.ViewHolder{

    private ImageView mImageVideo;
    private TextView mTextNameVideo;
    private TextView mTextSize;
    private View mView;

    public ViewHolde(View itemView) {
      super(itemView);
      mView = itemView;
      mImageVideo = itemView.findViewById(R.id.image_video);
      mTextNameVideo = itemView.findViewById(R.id.text_titleVideo);
      mTextSize = itemView.findViewById(R.id.text_size);
    }
  }

}
