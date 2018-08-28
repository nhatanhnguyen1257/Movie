package com.example.nhatanh_27_01_95.moviedb.task.movie;

import com.example.nhatanh_27_01_95.moviedb.task.genres.Genre;

import java.util.Arrays;

public class Movie {

  private String mId;

  private String mVoteCount;

  private String mIdYoutube;

  private String mVoteAverage;

  private String mTitle;

  private String mUrlImage;

  private String mBackdropPath;

  private String mOverview;

  private String mDate;

  private String mPopularity;

  private Genre []mArrayGenres;

  public Movie(String id,
               String voteCount,
               String voteAverage,
               String title,
               String urlImage,
               String overview,
               String date,
               String popularity) {
    mId = id;
    mVoteCount = voteCount;
    mVoteAverage = voteAverage;
    mTitle = title;
    mUrlImage = urlImage;
    mOverview = overview;
    mDate = date;
    mPopularity = popularity;
  }

  public Movie(String id,
               String voteCount,
               String voteAverage,
               String title,
               String urlImage,
               String backdropPath,
               String overview,
               String date,
               String popularity,
               Genre[] arrayGenres) {
    mId = id;
    mVoteCount = voteCount;
    mVoteAverage = voteAverage;
    mTitle = title;
    mUrlImage = urlImage;
    mBackdropPath = backdropPath;
    mOverview = overview;
    mDate = date;
    mPopularity = popularity;
    mArrayGenres = arrayGenres;
  }

  public String getId() {
    return mId;
  }

  public void setId(String id) {
    mId = id;
  }

  public String getVoteCount() {
    return mVoteCount;
  }


  public String getIdYoutube() {
    return mIdYoutube;
  }

  public void setIdYoutube(String idYoutube) {
    mIdYoutube = idYoutube;
  }

  public String getVoteAverage() {
    return mVoteAverage;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public String getUrlImage() {
    return mUrlImage;
  }

  public void setUrlImage(String urlImage) {
    mUrlImage = urlImage;
  }

  public String getOverview() {
    return mOverview;
  }

  public String getDate() {
    return mDate;
  }

  public void setDate(String date) {
    mDate = date;
  }

  public Genre[] getArrayGenres() {
    return mArrayGenres;
  }

  public void setArrayGenres(Genre[] arrayGenres) {
    mArrayGenres = arrayGenres;
  }

  public String getBackdropPath() {
    return mBackdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    mBackdropPath = backdropPath;
  }

  public String getPopularity() {
    return mPopularity;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "mId='" + mId + '\'' +
        ", mVoteCount='" + mVoteCount + '\'' +
        ", mIdYoutube='" + mIdYoutube + '\'' +
        ", mVoteAverage='" + mVoteAverage + '\'' +
        ", mTitle='" + mTitle + '\'' +
        ", mUrlImage='" + mUrlImage + '\'' +
        ", mBackdropPath='" + mBackdropPath + '\'' +
        ", mOverview='" + mOverview + '\'' +
        ", mDate='" + mDate + '\'' +
        ", mPopularity='" + mPopularity + '\'' +
        ", mArrayGenres=" + Arrays.toString(mArrayGenres) +
        '}';
  }
}
