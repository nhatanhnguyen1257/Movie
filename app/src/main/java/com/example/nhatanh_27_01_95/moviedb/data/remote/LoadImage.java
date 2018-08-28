package com.example.nhatanh_27_01_95.moviedb.data.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nhatanh_27_01_95.moviedb.R;

public class LoadImage extends AsyncTask<String,  String, Void> {

  private Context mContext;
  private ImageView mImageView;

  public LoadImage(Context context, ImageView imageView) {
    mContext = context;
    mImageView = imageView;
  }

  @Override
  protected Void doInBackground(String... strings) {
    publishProgress(strings[0]);
    return null;
  }

  @Override
  protected void onProgressUpdate(String... values) {
    super.onProgressUpdate(values);
    Glide.with(mContext).
        load(values[0]).
        error(R.drawable.ic_splash_screen).
        diskCacheStrategy(DiskCacheStrategy.NONE).
        into(mImageView);
  }
}

