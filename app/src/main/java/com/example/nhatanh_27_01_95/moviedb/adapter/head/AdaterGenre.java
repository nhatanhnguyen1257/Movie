package com.example.nhatanh_27_01_95.moviedb.adapter.head;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.task.genres.Genre;
import com.example.nhatanh_27_01_95.moviedb.task.genres.GenrePrisenter;
import com.example.nhatanh_27_01_95.moviedb.task.genres.GenresContract;
import com.example.nhatanh_27_01_95.moviedb.task.movie.PresenterMovie;

public class AdaterGenre extends RecyclerView.Adapter<AdaterGenre.ViewHoled> {

  private Genre []mGenre;
  private GenresContract.Prisenter  mPrisenter;

  public static AdaterGenre init(Genre []genres, GenresContract.Prisenter  prisenter){
    AdaterGenre adaterGenre = new AdaterGenre();
    adaterGenre.mGenre = genres;
    adaterGenre.mPrisenter = prisenter;
    return adaterGenre;
  }

  @NonNull
  @Override
  public ViewHoled onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new ViewHoled(inflater.inflate(R.layout.item_text_genres, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHoled holder, final int position) {
    holder.mText.setText(mGenre[position].getTitle());
    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mPrisenter.loadData(mGenre[position]);
      }
    });
  }

  @Override
  public int getItemCount() {
    return mGenre == null ? 0 : mGenre.length;
  }



  public class ViewHoled extends RecyclerView.ViewHolder {

    private TextView mText;
    private View mView;

    public ViewHoled(View itemView) {
      super(itemView);
      mView = itemView;
      mText = itemView.findViewById(R.id.text);
    }
  }
}
