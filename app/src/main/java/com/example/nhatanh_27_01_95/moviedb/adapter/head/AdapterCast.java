package com.example.nhatanh_27_01_95.moviedb.adapter.head;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.data.remote.LoadImage;
import com.example.nhatanh_27_01_95.moviedb.task.Cast;
import com.example.nhatanh_27_01_95.moviedb.task.head.HeadPresenter;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;

public class AdapterCast extends RecyclerView.Adapter<AdapterCast.ViewHolder> {

  private ArrayList<Cast> mCasts;
  private HeadPresenter mHeadPresenter;

  public AdapterCast(ArrayList<Cast> arrayCast, HeadPresenter headPresenter) {
    mCasts = arrayCast;
    mHeadPresenter = headPresenter;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.item_image, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Cast cast;
    synchronized (cast = mCasts.get(position)){
      if (cast != null){

          String url = cast.getUrlImage();
          new LoadImage(holder.itemView.getContext(), holder.mImageView).
              execute(UtilMovies.SearchMovie.urlImage(url));
    }


      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          mHeadPresenter.loadMovieOfCast(cast.getId());
        }
      });
    }
  }


  @Override
  public int getItemCount() {
    return mCasts == null ? 0 : mCasts.size();
  }

//  @Override
//  public boolean onFailedToRecycleView(@NonNull ViewHolder holder) {
//    return true;
//  }
//
//  @Override
//  public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
//    mCasts = null;
//  }


  public static class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;
    private View mView;

    public ViewHolder(View itemView) {
      super(itemView);
      mImageView = (ImageView) itemView.findViewById(R.id.image_item);
      mView = itemView;
    }
  }
}
