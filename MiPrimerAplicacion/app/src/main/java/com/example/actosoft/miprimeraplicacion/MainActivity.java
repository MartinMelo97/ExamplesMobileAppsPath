package com.example.actosoft.miprimeraplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botoncito;
    TextView TVmensaje;
    EditText ETmensaje;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botoncito = (Button) findViewById(R.id.botoncito);

        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Logica
                ETmensaje = (EditText) findViewById(R.id.ETmensaje);
                mensaje = ETmensaje.getText().toString();
                if(mensaje.length() > 0)
                {
                    TVmensaje = (TextView) findViewById(R.id.TVmensaje);
                    TVmensaje.setText(mensaje);
                    Toast.makeText(MainActivity.this, "Mensaje actualizado", Toast.LENGTH_SHORT).show();
                    ETmensaje.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No seas wey, escribe algo", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
