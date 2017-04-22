package com.actosoftcommunity.tiendita;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    TextView TVColor, TVSize, TVQuantity, TVUnitPrice, TVTotalPrice;
    int precio = 100;
    int quantity, total;
    String color, size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent receivingData = getIntent();
        quantity = receivingData.getIntExtra("quantity",0);
        color = receivingData.getStringExtra("color");
        size = receivingData.getStringExtra("size");

        total = quantity * precio;

        TVColor = (TextView) findViewById(R.id.TVPayColor);
        TVSize = (TextView) findViewById(R.id.TVPaySize);
        TVQuantity = (TextView) findViewById(R.id.TVPayQuantity);
        TVUnitPrice = (TextView) findViewById(R.id.TVPayUnitPrice);
        TVTotalPrice = (TextView) findViewById(R.id.TVPayTotalPrice);

        TVColor.setText("Color: " + color);
        TVSize.setText("Talla: "+size);
        TVQuantity.setText("Cantidad: "+ quantity);
        TVUnitPrice.setText("Precio unitario " + precio);
        TVTotalPrice.setText("TOTAL: " + total);
        /*Toast.makeText(this, "" + color + size + quantity + precio + total, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Color: " + color, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Talla: " + size, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Cantidad: " + quantity, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Precio: " + precio, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Total " + total, Toast.LENGTH_SHORT).show();
*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gracias perro por tu compra", Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

}
