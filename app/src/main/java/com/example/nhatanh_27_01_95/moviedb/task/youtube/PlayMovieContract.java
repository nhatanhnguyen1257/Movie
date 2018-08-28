package com.example.nhatanh_27_01_95.moviedb.task.youtube;

import android.content.Context;

import com.example.nhatanh_27_01_95.moviedb.PresenterBase;

public class PlayMovieContract {

  public interface Presenter extends PresenterBase{

    void searchKeyMovie();

    void searchListMovie();

    void searchReview();

    void isFavorite(Context context);

    void addOrRemoteFavorite(Context context);

    void youtubePlay(String key);

  }

  public interface View {

    void showViewPageMovie();

    void showFavorite();

    void showUnFavorite();

    void showMess(String mess);

    void playVideo(String key);
  }

}
