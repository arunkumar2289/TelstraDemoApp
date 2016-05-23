package com.telestra.demoApp.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class is created for util like network connection
 */
public class Util {


    Context context;

    //Constructor  for Util class
    public Util(Context context){
      this.context = context;
    }

    /**
     * Checking network connection
     * If network available returns true else false
     *
     * */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
