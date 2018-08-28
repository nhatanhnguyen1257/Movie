package com.example.nhatanh_27_01_95.moviedb.task;

public class Cast {

  private String mId;

  private String mName;

  private String mUrlImage;

  private String mCharacter;

  public Cast(){

  }

  public Cast(String id, String name, String urlImage, String character) {
    mId = id;
    mName = name;
    mUrlImage = urlImage;
    mCharacter = character;
  }

  public String getId() {
    return mId;
  }

  public void setId(String id) {
    mId = id;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public String getUrlImage() {
    return mUrlImage;
  }

  public void setUrlImage(String urlImage) {
    mUrlImage = urlImage;
  }

  public String getCharacter() {
    return mCharacter;
  }

  public void setCharacter(String character) {
    mCharacter = character;
  }
}
