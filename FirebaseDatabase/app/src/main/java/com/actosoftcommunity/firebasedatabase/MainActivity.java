package com.actosoftcommunity.firebasedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText ETMensaje;
    Button BtnEnviar;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnEnviar = (Button) findViewById(R.id.BtnEnviar);
        BtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ETMensaje = (EditText) findViewById(R.id.ETMensaje);
               mensaje = ETMensaje.getText().toString();
                if(mensaje.length() > 0)
                {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("mensajes").push();

                    myRef.child("mensaje").setValue(mensaje);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Escribe algo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ValueEventListener mensajesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }

    }

}
