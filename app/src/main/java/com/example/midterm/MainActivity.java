package com.example.midterm;

/**
 * MainActivity.java
 * The main screen of the Grocery List App for adding and viewing grocery items.
 * Author: Abdullahi Ali
 * Date: 11/13/24
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText itemNameEdt, quantityEdt, categoryEdt, priceEdt;
    private Button addGroceryBtn, viewGroceryBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemNameEdt = findViewById(R.id.idEdtItemName);
        quantityEdt = findViewById(R.id.idEdtQuantity);
        categoryEdt = findViewById(R.id.idEdtCategory);
        priceEdt = findViewById(R.id.idEdtPrice);
        addGroceryBtn = findViewById(R.id.idBtnAddGrocery);
        viewGroceryBtn = findViewById(R.id.idBtnViewGrocery);

        dbHandler = new DBHandler(MainActivity.this);

        addGroceryBtn.setOnClickListener(v -> {
            String itemName = itemNameEdt.getText().toString();
            String category = categoryEdt.getText().toString();
            int quantity;
            double price;

            try {
                quantity = Integer.parseInt(quantityEdt.getText().toString());
                price = Double.parseDouble(priceEdt.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (itemName.isEmpty() || category.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHandler.addNewGroceryItem(itemName, quantity, category, price);
            Toast.makeText(MainActivity.this, "Grocery Item Added!", Toast.LENGTH_SHORT).show();
            itemNameEdt.setText("");
            quantityEdt.setText("");
            categoryEdt.setText("");
            priceEdt.setText("");
        });

        viewGroceryBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ViewGrocery.class);
            startActivity(i);
        });
    }
}
