package com.music.scoremaker;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class PermissionUtill {

    public static final int REQUEST_STORAGE = 1;

    public static boolean verifyPermission(int[] grantresults) {
        if(grantresults.length < 1) {
            return false;
        }
        for (int result : grantresults) {
            if(result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static  void requestExternalPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_STORAGE);
    }

    public static boolean checkPermissions(Activity activity, String permission){
        int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);
        if (permissionResult == PackageManager.PERMISSION_GRANTED) return true;
        else  return false;
    }
}
