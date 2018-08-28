package com.example.nhatanh_27_01_95.moviedb.adapter.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.data.remote.LoadImage;
import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;
import com.example.nhatanh_27_01_95.moviedb.task.movie.PresenterMovie;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;

public class AdapterRecyclerMovie extends RecyclerView.Adapter<AdapterRecyclerMovie.ViewHolde>  {

  private ArrayList<Movie> mMovieArrayList;

  private PresenterMovie mPresenterMovie;

  public AdapterRecyclerMovie(ArrayList<Movie> movieArrayList, PresenterMovie presenterMovie) {
    mMovieArrayList = movieArrayList;
    mPresenterMovie = presenterMovie;
  }

  @NonNull
  @Override
  public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fiml, parent, false);
    return new ViewHolde(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
    final Movie movie = mMovieArrayList.get(position);
    if (movie != null){
      holder.mTextDate.setText(movie.getDate());
      holder.mTextNameFiml.setText(movie.getTitle());
      holder.mTextRank.setText(movie.getVoteAverage());

      new LoadImage(holder.itemView.getContext(), holder.mImageAvate).execute(
          UtilMovies.SearchMovie.urlImage(
          movie.getBackdropPath().length() == 4 ? movie.getUrlImage() : movie.getBackdropPath()));

      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          mPresenterMovie.clickItemMovie(movie);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return mMovieArrayList == null ? 0 : mMovieArrayList.size();
  }

  @Override
  public boolean onFailedToRecycleView(@NonNull ViewHolde holder) {
    return true;
  }

  public class ViewHolde extends RecyclerView.ViewHolder{

    private TextView mTextNameFiml;
    private TextView mTextDate;
    private ImageView mImageAvate;
    private TextView mTextRank;
    private View mView;

    public ViewHolde(View itemView) {
      super(itemView);
      mImageAvate = (ImageView)itemView.findViewById(R.id.image_avataFiml);
      mTextDate = (TextView)itemView.findViewById(R.id.text_date);
      mTextNameFiml = (TextView)itemView.findViewById(R.id.text_name_fiml);
      mTextRank = itemView.findViewById(R.id.text_rank);
      mView = itemView;
    }
  }
}
