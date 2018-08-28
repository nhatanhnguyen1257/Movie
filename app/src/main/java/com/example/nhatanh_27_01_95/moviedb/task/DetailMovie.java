package com.example.nhatanh_27_01_95.moviedb.task;

import com.example.nhatanh_27_01_95.moviedb.task.movie.Movie;

public class DetailMovie extends Movie {

  private String mBudget;

  private String mRevenue;

  private String mRuntime;

  public DetailMovie(Movie movie, String budget, String revenue, String runtime) {

    super(movie.getId(),
        movie.getVoteCount(),
        movie.getVoteAverage(),
        movie.getTitle(),
        movie.getUrlImage(),
        movie.getBackdropPath(),
        movie.getOverview(),
        movie.getDate(),
        movie.getPopularity(),
        movie.getArrayGenres());

    mBudget = budget;
    mRevenue = revenue;
    mRuntime = runtime;
  }

  public String getBudget() {
    return mBudget;
  }

  public String getRevenue() {
    return mRevenue;
  }

  public String getRuntime() {
    return mRuntime;
  }
}
