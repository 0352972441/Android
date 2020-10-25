package com.example.cocoshop.permission;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import com.example.cocoshop.screen.authscreen.LoginScreen;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class InternetPermission implements Runnable{
    private boolean is3G = false;
    private boolean isWifi = false;
    private Context context;

    private boolean isOccurred =false;

    public InternetPermission(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        is3G = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        if(!is3G && !isWifi){
            isOccurred = true;
        }else{
            isOccurred = false;
        }
    }

    public boolean isOccurred() {
        return isOccurred;
    }

    public void setOccurred(boolean occurred) {
        isOccurred = occurred;
    }
}
