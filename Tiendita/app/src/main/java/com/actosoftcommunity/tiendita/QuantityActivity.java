package com.actosoftcommunity.tiendita;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuantityActivity extends AppCompatActivity {
    TextView TVSize, TVColor;
    String size, color;
    EditText ETQuantity;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent receiving = getIntent();
        size = receiving.getStringExtra("size");
        color = receiving.getStringExtra("color");

        TVSize = (TextView) findViewById(R.id.TVSize);
        TVColor = (TextView) findViewById(R.id.TVColor);

        TVColor.setText("Color: "+ color);
        TVSize.setText("Talla: "+size);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                    ETQuantity = (EditText) findViewById(R.id.ETQuantity);
                    String text = ETQuantity.getText().toString();
                    quantity = Integer.parseInt(text);
                    if(quantity > 0) {
                        Toast.makeText(QuantityActivity.this, "Cantidad: " + quantity, Toast.LENGTH_SHORT).show();
               /* try {*/
                        Intent sendPayment = new Intent(QuantityActivity.this, PaymentActivity.class);
                        sendPayment.putExtra("size", size);
                        sendPayment.putExtra("color", color);
                        sendPayment.putExtra("quantity", quantity);
                        startActivity(sendPayment);
                    }
                    else
                    {
                        Toast.makeText(QuantityActivity.this, "Frepo", Toast.LENGTH_SHORT).show();
                    }


            }
        });
    }

}
