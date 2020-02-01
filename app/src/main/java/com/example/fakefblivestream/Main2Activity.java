package com.example.fakefblivestream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {

    private static final int STORAGE_REQUEST_CODE = 123;
    private static final int CAMERA_REQUEST_CODE = 231;
    private Button gotoLiveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gotoLiveBtn = findViewById(R.id.gotoLiveBtn);

        isStorageRequestAccepted();
        isCameraPermissionAccepted();

        gotoLiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean isStorageRequestAccepted() {
        String[] permissionList = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissionList, STORAGE_REQUEST_CODE);
            return false;
        }
        return true;
    }


    private boolean isCameraPermissionAccepted() {
        String[] permissionList = {Manifest.permission.CAMERA};
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissionList, CAMERA_REQUEST_CODE);
            return false;
        }
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case STORAGE_REQUEST_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Storage Permission", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }
            } case CAMERA_REQUEST_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera permission", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}
