package com.example.nhatanh_27_01_95.moviedb.adapter.review;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.task.review.Review;

import java.util.ArrayList;

public class AdapterReview extends RecyclerView.Adapter<AdapterReview.ViewwHolde> {

  private ArrayList<Review> mReviewArrayList;

  public AdapterReview(ArrayList<Review> reviewArrayList) {
    mReviewArrayList = reviewArrayList;
  }

  @NonNull
  @Override
  public ViewwHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new ViewwHolde(inflater.inflate(R.layout.item_review, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewwHolde holder, int position) {
    Review review = mReviewArrayList.get(position);
    if (review != null){
      holder.mTextAuthor.setText(review.getName());
      holder.mTextReview.setText(review.getContent());
    }
  }

  @Override
  public int getItemCount() {
    return mReviewArrayList == null ? 0 : mReviewArrayList.size();
  }

  public static class ViewwHolde extends RecyclerView.ViewHolder{

    private TextView mTextAuthor;

    private TextView mTextReview;

    public ViewwHolde(View itemView) {
      super(itemView);

      mTextAuthor = itemView.findViewById(R.id.text_author);
      mTextReview = itemView.findViewById(R.id.text_review);

    }
  }
}
