package com.actosoftcommunity.grabadoradevideo;

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

    private static final int VIDEO_CAPTURE = 101;
    private static final int IIMAGE_CAPTURE = 115;
    private Uri fileUri;
    Button video;
    Button camera;
    public View cons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = (Button) findViewById(R.id.video);
        camera = (Button) findViewById(R.id.photo);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);


        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            makeRequest();
        }
        if(!hasCamera())
        {
            video.setEnabled(false);
        }

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakePhoto();
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordVideo();
            }
        });

    }

    private boolean hasCamera()
    {
        boolean has = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        return has;
    }

    protected void makeRequest()
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, IIMAGE_CAPTURE);
    }

    public void TakePhoto(){
        Intent intentphoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentphoto, IIMAGE_CAPTURE);
    }

    public void RecordVideo()
    {
        Intent intentvideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intentvideo, VIDEO_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Uri videoUri = data.getData();
        if(requestCode == VIDEO_CAPTURE)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "Tu video esta en: \n "+videoUri, Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "Grabacion cancelada por puto", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Algo fallo vlv", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case IIMAGE_CAPTURE: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                {
                    cons = (ConstraintLayout) findViewById(R.id.idcons);
                    Toast.makeText(this, "Denegado", Toast.LENGTH_SHORT).show();
                    video.setEnabled(false);
                    camera.setEnabled(false);
                    Snackbar.make(cons,"COMPRA UN CEL WEY", Snackbar.LENGTH_INDEFINITE).show();

                }
                else
                {
                    Toast.makeText(this, "Aprobado", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
