package com.actosoftcommunity.conexionentreintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity {
    EditText ETNombre, ETApellidos, ETCorreo;
    Button BtnEnviar;
    String nombre, apellidos, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        ETNombre = (EditText) findViewById(R.id.ETNombre);
        ETApellidos = (EditText) findViewById(R.id.ETApellidos);
        ETCorreo = (EditText) findViewById(R.id.ETCorreo);
        BtnEnviar = (Button) findViewById(R.id.BtnEnviar);

        BtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = ETNombre.getText().toString();
                apellidos = ETApellidos.getText().toString();
                correo = ETCorreo.getText().toString();

                if(nombre.length() > 0 && apellidos.length() > 0 && correo.length() > 0)
                {
                    Intent intento = new Intent(getApplicationContext(), ActivityTwo.class);
                    intento.putExtra("nombre_2", nombre);
                    intento.putExtra("apellidos_2", apellidos);
                    intento.putExtra("correo_2", correo);
                    startActivity(intento);
                }
                else
                {
                    Toast.makeText(ActivityOne.this, "Llena todo los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
