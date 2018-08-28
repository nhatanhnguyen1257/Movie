package com.example.nhatanh_27_01_95.moviedb.net.conncetion;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.nhatanh_27_01_95.moviedb.task.activityhome.PresenterHome;
import com.example.nhatanh_27_01_95.moviedb.util.UtilActivytyHome;


public class NetWorkConnection extends BroadcastReceiver  {

  private PresenterHome mPresenterHome;

  public NetWorkConnection() {
  }

  public void setPresenterHome(PresenterHome presenterHome) {
    mPresenterHome = presenterHome;
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    ConnectivityManager con = (ConnectivityManager)context.
        getSystemService(Context.CONNECTIVITY_SERVICE);

    if (!isConnectionMobi(con) && !isConnectionWifi(con)){
      UtilActivytyHome.isNetwork = false;
    Log.d("NET", "onReceive: not net" );
    }
    else{
      UtilActivytyHome.isNetwork = true;
      Log.d("NET", "onReceive: on net");
    }
  }


  private boolean isConnectionMobi(ConnectivityManager connectivityManager){
    return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
  }

  private boolean isConnectionWifi(ConnectivityManager connectivityManager){
    return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
  }


}
