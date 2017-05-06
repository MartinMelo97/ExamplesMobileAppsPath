package com.actosoftcommunity.grabadoradevideox2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_CAPTURE = 115;
    private static final int VIDEO_RECORD = 101;

    Button video,camera;

    ConstraintLayout cons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = (Button) findViewById(R.id.BtnVideo);
        camera = (Button) findViewById(R.id.BtnPhoto);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            makeRequest();
        }

        if(!hasCamera())
        {
            video.setEnabled(false);
        }

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordVideo();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakePhoto();
            }
        });
    }

    protected void makeRequest()
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, IMAGE_CAPTURE);
    }

    private boolean hasCamera()
    {
        boolean has = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        return has;
    }

    public void TakePhoto()
    {
        Intent intentphoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentphoto, IMAGE_CAPTURE);
    }

    public void RecordVideo()
    {
        Intent intentvideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intentvideo, VIDEO_RECORD);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        Uri videoUri = data.getData();

        if(requestCode == VIDEO_RECORD)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "Tu video esta en \n" + videoUri, Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "Grabacion cancelada", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Algo fallo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case IMAGE_CAPTURE:{

                if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Denegado", Toast.LENGTH_SHORT).show();
                    video.setEnabled(false);
                    camera.setEnabled(false);
                    cons = (ConstraintLayout) findViewById(R.id.cons);
                    Snackbar.make(cons,"COMPRA UN CEL WEY MAS CHINGON", Snackbar.LENGTH_INDEFINITE).show();
                }
            }
        }
    }
}
