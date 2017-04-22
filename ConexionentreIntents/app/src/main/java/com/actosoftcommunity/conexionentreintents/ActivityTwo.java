package com.actosoftcommunity.conexionentreintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    String nombre, apellidos, correo;
    TextView TVnombre, TVapellidos, TVcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Intent intentito = getIntent();
        nombre = intentito.getStringExtra("nombre_2");
        apellidos = intentito.getStringExtra("apellidos_2");
        correo = intentito.getStringExtra("correo_2");

        TVnombre = (TextView) findViewById(R.id.TVNombre);
        TVapellidos = (TextView) findViewById(R.id.TVApellidos);
        TVcorreo = (TextView) findViewById(R.id.TVCorreo);

        TVnombre.setText(nombre);
        TVapellidos.setText(apellidos);
        TVcorreo.setText(correo);
    }
}
