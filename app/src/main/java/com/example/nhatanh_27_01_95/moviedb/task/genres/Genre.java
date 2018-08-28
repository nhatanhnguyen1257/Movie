package com.example.nhatanh_27_01_95.moviedb.task.genres;

public class Genre {

  private String mId;

  private String mTitle;

  public Genre(String id, String title) {
    mId = id;
    mTitle = title;
  }

  public String getId() {
    return mId;
  }

  public String getTitle() {
    return mTitle;
  }


}
