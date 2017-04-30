package com.actosoftcommunity.grabadoradeaudio;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    //Codigos de permisos
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;

    //Declaro variables
     Button playButton;
     Button recordButton;
     Button stopButton;
    private boolean isRecording;
    private static String audioFilePath;
    private static MediaPlayer mediaplayer;
    private static MediaRecorder mediarecorder;
    ConstraintLayout mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.btnPlay);
        recordButton = (Button) findViewById(R.id.btnRecord);
        stopButton = (Button) findViewById(R.id.btnStop);

        if(!hasMic())
        {
            playButton.setEnabled(false);
            recordButton.setEnabled(false);
            stopButton.setEnabled(false);
            mView = (ConstraintLayout) findViewById(R.id.cons);
            Snackbar.make(mView, "COMPRA UN CELULAR EN LA FAYUCA Y OCUPA MI APP", Snackbar.LENGTH_INDEFINITE).show();
        }
        else
        {
            playButton.setEnabled(false);
            stopButton.setEnabled(false);
        }

        audioFilePath =
                Environment.getExternalStorageDirectory().getAbsolutePath()+"/bliss.3gp";

        requestPermission(android.Manifest.permission.RECORD_AUDIO, RECORD_REQUEST_CODE);
        

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RecordMethod(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PlayMethod(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StopMethod(view);
            }
        });

    } //onCreate

    protected boolean hasMic(){
        PackageManager pmanager = this.getPackageManager();
        boolean mic = pmanager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
        return mic;
    } //hasMinc

    public void RecordMethod(View view) throws IOException
    {
        isRecording = true;
        stopButton.setEnabled(true);
        playButton.setEnabled(false);
        recordButton.setEnabled(false);

        try
        {
            mediarecorder = new MediaRecorder();
            mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediarecorder.setOutputFile(audioFilePath);
            mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediarecorder.prepare();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        mediarecorder.start();
    }

    public void StopMethod(View view)
    {
        stopButton.setEnabled(false);
        playButton.setEnabled(true);
        if(isRecording)
        {
            recordButton.setEnabled(false);
            mediarecorder.stop();
            mediarecorder.release();
            mediarecorder = null;
            isRecording = false;
        }
        else
        {
            mediaplayer.stop();
            mediaplayer.release();
            mediaplayer = null;
            recordButton.setEnabled(true);
        }
    }
    public void PlayMethod(View view) throws IOException
    {
        playButton.setEnabled(false);
        stopButton.setEnabled(true);
        recordButton.setEnabled(false);

        mediaplayer = new MediaPlayer();
        mediaplayer.setDataSource(audioFilePath);
        mediaplayer.prepare();
        mediaplayer.start();


    }
    
    protected void requestPermission(String permissionType, int requestCode)
    {
        int permissionint = ContextCompat.checkSelfPermission(this, permissionType);
        if(permissionint != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{permissionType}, requestCode);
        }
    }//requestCode
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode){
            
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    recordButton.setEnabled(false);
                    Toast.makeText(this, "Permiso de almacenamiento requerido", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case RECORD_REQUEST_CODE:{
                if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                {
                    recordButton.setEnabled(false);
                    Toast.makeText(this, "Permiso de grabacion requerido", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_REQUEST_CODE);
                }
                break;
            }
        }
    }


}
