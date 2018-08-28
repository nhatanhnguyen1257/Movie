package com.example.nhatanh_27_01_95.moviedb.task.review;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.adapter.review.AdapterReview;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment implements ReviewContract.View {

  private String mIdMovie;
  private ReviewPresenter mReviewPresenter;

  public ReviewFragment() {
  }


  public static ReviewFragment init(String idMovie){
    ReviewFragment reviewFragment = new ReviewFragment();
    reviewFragment.mIdMovie = idMovie;
    return reviewFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_review, container, false);
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mReviewPresenter = ReviewPresenter.init(mIdMovie, this);
    mReviewPresenter.start();
  }

  @Override
  public void showReview(ArrayList<Review> reviews) {
    RecyclerView recycler = getView().findViewById(R.id.recycler_review);
    recycler.setAdapter(new AdapterReview(reviews));
    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
  }
}
