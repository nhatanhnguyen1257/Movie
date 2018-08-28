package com.example.nhatanh_27_01_95.moviedb.util;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.Toast;

import com.example.nhatanh_27_01_95.moviedb.R;
import com.example.nhatanh_27_01_95.moviedb.task.movie.MovieFragment;

public class UtilActivytyHome {

  public static boolean isNetwork = true;

  public static boolean isSearch = true;

  public static void showErr(String err, Context context){
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(err);
    builder.show();
  }

  public static void showMess(Context context, String mes){
    Toast.makeText(context, mes, Toast.LENGTH_SHORT).show();
  }

}
