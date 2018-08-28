package com.example.nhatanh_27_01_95.moviedb.task.review;

public class Review {

  private String mName;

  private String mContent;

  public Review(String name, String content) {
    mName = name;
    mContent = content;
  }

  public String getName() {
    return mName;
  }

  public String getContent() {
    return mContent;
  }
}
