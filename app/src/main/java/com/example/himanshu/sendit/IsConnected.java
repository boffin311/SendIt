package com.example.himanshu.sendit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class IsConnected {
    Context context;
    public IsConnected(Context context){
        this.context=context;
        }
    public boolean returnNetworkState() {
   ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
   NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
    if (networkInfo.isConnected())
    {
        return true;
    }
    else return false;
    }
}
