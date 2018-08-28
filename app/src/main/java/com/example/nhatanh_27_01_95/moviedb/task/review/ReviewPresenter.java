package com.example.nhatanh_27_01_95.moviedb.task.review;

import com.example.nhatanh_27_01_95.moviedb.data.remote.JsonReview;
import com.example.nhatanh_27_01_95.moviedb.util.UtilMovies;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ReviewPresenter implements ReviewContract.Presenter {

  private ArrayList<Review> mReviewArrayList;
  private ReviewContract.View mView;
  private String mIdMovie;

  public static ReviewPresenter init(String idMovie, ReviewContract.View view){
    ReviewPresenter reviewPresenter = new ReviewPresenter();
    reviewPresenter.mView = view;
    reviewPresenter.mIdMovie = idMovie;
    return reviewPresenter;
  }

  @Override
  public void loadReview(String idMovie) {
    try {
      mReviewArrayList = new JsonReview().execute(UtilMovies.Review.urlReview(idMovie)).get();
      mView.showReview(mReviewArrayList);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void start() {
    loadReview(mIdMovie);
  }
}
