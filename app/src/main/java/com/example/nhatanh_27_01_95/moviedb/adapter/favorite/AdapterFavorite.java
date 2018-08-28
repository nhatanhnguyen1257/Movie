package com.example.nhatanh_27_01_95.moviedb.adapter.favorite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.data.remote.LoadImage;
import com.example.nhatanh_27_01_95.moviedb.task.favorite.FavoritePresenter;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolde> {

  private ArrayList<Movie> mMovieArrayList;
  private FavoritePresenter mFavoritePresenter;

  public AdapterFavorite(ArrayList<Movie> movieArrayList, FavoritePresenter favoritePresenter) {
    mMovieArrayList = movieArrayList;
    mFavoritePresenter = favoritePresenter;
  }

  @NonNull
  @Override
  public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new ViewHolde(inflater.inflate(R.layout.item_favorite, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
    final Movie movie = mMovieArrayList.get(position);
    if (movie != null){
      holder.mTextNameMovie.setText(movie.getTitle());
      holder.mTextDate.setText(movie.getDate());
      new LoadImage(holder.itemView.getContext(),holder.mImageMovie).execute(UtilMovies.SearchMovie.urlImage(movie.getUrlImage()));

      holder.mImageMovie.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          mFavoritePresenter.getListGenreOfMovie(movie);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return mMovieArrayList == null ? 0 : mMovieArrayList.size();
  }

  public static class ViewHolde extends RecyclerView.ViewHolder{

    private ImageView mImageMovie;
    private TextView mTextNameMovie;
    private TextView mTextDate;

    public ViewHolde(View itemView) {
      super(itemView);

      mImageMovie = itemView.findViewById(R.id.image_avataFiml);
      mTextNameMovie = itemView.findViewById(R.id.text_name_fiml);
      mTextDate = itemView.findViewById(R.id.text_date);
    }
  }
}
