package com.example.nhatanh_27_01_95.moviedb.task;

public class Video {

  private String mKey;
  private String mName;

  public Video(String id, String name) {
    mKey = id;
    mName = name;
  }

  public String getId() {
    return mKey;
  }

  public String getName() {
    return mName;
  }
}
