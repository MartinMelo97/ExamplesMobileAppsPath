package com.example.actosoft.suma2numeros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText Num1, Num2;
    TextView Resultado;
    Button boton;
    int num1, num2, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.Calcular);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Num1 = (EditText) findViewById(R.id.ETnum1);
                Num2 = (EditText) findViewById(R.id.ETNum2);
                num1 = Integer.parseInt(Num1.getText().toString());
                num2 = Integer.parseInt(Num2.getText().toString());
                resultado = num1 + num2;
                Resultado = (TextView) findViewById(R.id.TVResultado);
                Resultado.setText("La suma es: " + resultado);
            }
        });
    }
}
