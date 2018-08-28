package com.example.nhatanh_27_01_95.moviedb.task.review;

import com.example.nhatanh_27_01_95.moviedb.PresenterBase;

import java.util.ArrayList;

public class ReviewContract {

  public interface Presenter extends PresenterBase{

    void loadReview(String idMovie);

  }

  public interface View {

    void showReview(ArrayList<Review> reviews);

  }
}
