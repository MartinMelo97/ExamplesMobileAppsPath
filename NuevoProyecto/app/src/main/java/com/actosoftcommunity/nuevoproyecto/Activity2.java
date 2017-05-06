package com.actosoftcommunity.nuevoproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    public static TextView TVNombre, TVCorreo;
    public static String nombre, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intento_2 = getIntent();
        nombre = intento_2.getStringExtra("nombre");
        correo = intento_2.getStringExtra("correo");

        TVNombre = (TextView) findViewById(R.id.TVNombre);
        TVCorreo = (TextView) findViewById(R.id.TVCorreo);

        TVNombre.setText("Nombre: "+nombre);
        TVCorreo.setText("Correo: " + correo);
    }
}
