package com.example.deskbuilderapp;

import android.content.Intent;
import android.widget.Toast;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.chip.ChipGroup;

public class DeskOrderActivity extends AppCompatActivity {

    EditText deskLength;
    EditText deskWidth;
    EditText drawerCount;
    ChipGroup woodType;
    private static final int BASE_COST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        deskLength = findViewById(R.id.deskLength);
        deskWidth = findViewById(R.id.deskWidth);
        drawerCount = findViewById(R.id.drawerCount);
        woodType = findViewById(R.id.woodType);
    }

    public void launchDeskOrder(View view) {
        if (validateInput()) {
            double price = calculatePrice();
            String customerName = getIntent().getStringExtra("customerName");
            String deskDimensions = deskLength.getText().toString() + " x " + deskWidth.getText().toString();
            int drawerCount = Integer.parseInt(this.drawerCount.getText().toString());
            String woodType = getSelectedWoodType();

            Intent j = new Intent(this, CustomerOrderMainActivity.class);
            j.putExtra("customerName", customerName);
            j.putExtra("deskDimensions", deskDimensions);
            j.putExtra("woodType", woodType);
            j.putExtra("drawerCount", drawerCount);
            j.putExtra("totalPrice", price);

            startActivity(j);
        }
    }

    private boolean validateInput() {
        int length = Integer.parseInt(deskLength.getText().toString());
        int width = Integer.parseInt(deskWidth.getText().toString());
        int drawers = Integer.parseInt(drawerCount.getText().toString());

        if (length < 24 || length > 144) {
            Toast.makeText(this, "Desk length must be between 24 and 144 inches.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (width < 24 || width > 48) {
            Toast.makeText(this, "Desk width must be between 24 and 48 inches.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (drawers < 0 || drawers > 10) {
            Toast.makeText(this, "Drawer count must be between 0 and 10.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getSelectedWoodType() {
        int selectedWoodType = woodType.getCheckedChipId();
        switch (selectedWoodType) {
            case R.id.mahogany:
                return "Mahogany";
            case R.id.oak:
                return "Oak";
            case R.id.pine:
                return "Pine";
            default:
                return "Unknown";
        }
    }


    public double calculatePrice() {
        double price = BASE_COST;

        int length = Integer.parseInt(deskLength.getText().toString());
        int width = Integer.parseInt(deskWidth.getText().toString());
        int drawers = Integer.parseInt(drawerCount.getText().toString());

        if (length * width > 750) {
            price += 50;
        }

        int selectedWoodType = woodType.getCheckedChipId();
        switch (selectedWoodType) {
            case R.id.mahogany:
                price += 150;
                break;
            case R.id.oak:
                price += 125;
                break;
            case R.id.pine:
                price += 0;
            default:
                break;
        }

        price += 30 * drawers;

        return price;
    }
}
