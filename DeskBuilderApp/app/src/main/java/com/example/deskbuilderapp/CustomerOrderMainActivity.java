package com.example.deskbuilderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerOrderMainActivity extends AppCompatActivity {

    TextView customerNameDisplay;
    TextView deskDimensionsDisplay;
    TextView woodTypeDisplay;
    TextView drawerCountDisplay;
    TextView totalPriceDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_main);

        customerNameDisplay = findViewById(R.id.customerNameDisplay);
        deskDimensionsDisplay = findViewById(R.id.deskDimensionsDisplay);
        woodTypeDisplay = findViewById(R.id.woodTypeDisplay);
        drawerCountDisplay = findViewById(R.id.drawerCountDisplay);
        totalPriceDisplay = findViewById(R.id.totalPriceDisplay);

        Intent intent = getIntent();
        String customerName = intent.getStringExtra("customerName");
        String deskDimensions = intent.getStringExtra("deskDimensions");
        String woodType = intent.getStringExtra("woodType");
        int drawerCount = intent.getIntExtra("drawerCount", 0);
        double totalPrice = intent.getDoubleExtra("totalPrice", 0);

        customerNameDisplay.setText(customerName);
        deskDimensionsDisplay.setText(deskDimensions);
        woodTypeDisplay.setText(woodType);
        drawerCountDisplay.setText(String.valueOf(drawerCount));
        totalPriceDisplay.setText(String.format("$%.2f", totalPrice));
    }
}
