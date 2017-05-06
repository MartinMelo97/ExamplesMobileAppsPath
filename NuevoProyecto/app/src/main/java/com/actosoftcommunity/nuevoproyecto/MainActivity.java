package com.actosoftcommunity.nuevoproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static EditText ETNombre, ETCorreo;
    public static Button BtnEntrar;
    public static String nombre, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETNombre = (EditText) findViewById(R.id.ETNombre);
        ETCorreo = (EditText) findViewById(R.id.ETCorreo);
        BtnEntrar = (Button) findViewById(R.id.BtnEntrar);

        BtnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = ETNombre.getText().toString();
                correo = ETCorreo.getText().toString();

                if(nombre.length() > 0 && correo.length() > 0)
                {
                    //Toast.makeText(getApplicationContext(), "Nombre: "+nombre, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Correo: "+correo, Toast.LENGTH_SHORT).show();
                    Intent intento = new Intent(getApplicationContext(), Activity2.class);
                    intento.putExtra("nombre", nombre);
                    intento.putExtra("correo", correo);
                    startActivity(intento);

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
