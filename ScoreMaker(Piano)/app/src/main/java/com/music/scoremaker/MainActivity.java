package com.music.scoremaker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private void showRequestAgainDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("이 권한은 필요한 권한이므로, 활성화 부탁드립니다.");
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                }catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //취소하였음
            }
        });
        builder.create();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PermissionUtill.REQUEST_STORAGE) {
            if (PermissionUtill.verifyPermission(grantResults)) {
                // 요청한 권한을 얻었으므로 원하는 메소드를 사용
            } else {
                showRequestAgainDialog();
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(PermissionUtill.checkPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || (PermissionUtill.checkPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE))) {
            //권한이 있으므로 원하는 메소드를 사용
        }else{
            PermissionUtill.requestExternalPermissions(this);
        }



        Button btn_File = (Button) findViewById(R.id.btn_file);
        btn_File.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                File musicFile = new File("")
            }
        });

    }
}