package com.example.deskbuilderapp;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CustomerActivity extends AppCompatActivity {

    EditText customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        customerName = (EditText) findViewById(R.id.customerName);
    }

    public void launchApp(View view) {
        String inputCustomerName = customerName.getText().toString();

        if (inputCustomerName.trim().isEmpty()) {
            customerName.setError("Please enter a customer name");
            return;
        }

        Intent i = new Intent(this, DeskOrderActivity.class);
        i.putExtra("customerName", inputCustomerName);
        startActivity(i);
    }


}