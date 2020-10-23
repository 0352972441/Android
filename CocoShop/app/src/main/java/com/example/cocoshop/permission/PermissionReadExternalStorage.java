package com.example.cocoshop.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class PermissionReadExternalStorage {
    public static final int PERMISSION_CODE = 1001;
    public static boolean checkVersionSDk(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkPermission(final Context context, final Activity activity){
        if(checkVersionSDk()){
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(activity,permission,PERMISSION_CODE);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
