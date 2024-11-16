package com.example.midterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * UpdateGroceryActivity.java
 * Allows users to update or delete grocery items in the database.
 * Author: Abdullahi Ali
 * Date: 11/13/24
 */
public class UpdateGroceryActivity extends AppCompatActivity {

    // Variables for EditTexts, Buttons, Strings, and DBHandler
    private EditText groceryNameEdt, groceryQuantityEdt, groceryCategoryEdt, groceryPriceEdt;
    private Button updateGroceryBtn, deleteGroceryBtn;
    private DBHandler dbHandler;
    String originalName, groceryCategory;
    int groceryQuantity;
    double groceryPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_grocery);

        // Initializing variables
        groceryNameEdt = findViewById(R.id.idEdtGroceryName);
        groceryQuantityEdt = findViewById(R.id.idEdtGroceryQuantity);
        groceryCategoryEdt = findViewById(R.id.idEdtGroceryCategory);
        groceryPriceEdt = findViewById(R.id.idEdtGroceryPrice);
        updateGroceryBtn = findViewById(R.id.idBtnUpdateGrocery);
        deleteGroceryBtn = findViewById(R.id.idBtnDeleteGrocery);

        // Initializing DBHandler
        dbHandler = new DBHandler(UpdateGroceryActivity.this);

        // Retrieving data from Intent
        originalName = getIntent().getStringExtra("item_name");
        groceryCategory = getIntent().getStringExtra("category");
        groceryQuantity = getIntent().getIntExtra("quantity", 0);
        groceryPrice = getIntent().getDoubleExtra("price", 0.0);

        // Setting data to EditTexts
        groceryNameEdt.setText(originalName);
        groceryCategoryEdt.setText(groceryCategory);
        groceryQuantityEdt.setText(String.valueOf(groceryQuantity));
        groceryPriceEdt.setText(String.valueOf(groceryPrice));

        // Set onClickListener for Update Button
        updateGroceryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updating the grocery item
                String updatedName = groceryNameEdt.getText().toString();
                String updatedCategory = groceryCategoryEdt.getText().toString();
                int updatedQuantity;
                double updatedPrice;

                // Validate numeric input
                try {
                    updatedQuantity = Integer.parseInt(groceryQuantityEdt.getText().toString());
                    updatedPrice = Double.parseDouble(groceryPriceEdt.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(UpdateGroceryActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (updatedName.isEmpty() || updatedCategory.isEmpty()) {
                    Toast.makeText(UpdateGroceryActivity.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.updateGroceryItem(originalName, updatedName, updatedQuantity, updatedCategory, updatedPrice);
                Toast.makeText(UpdateGroceryActivity.this, "Grocery Updated!", Toast.LENGTH_SHORT).show();

                // Navigate back to MainActivity
                Intent i = new Intent(UpdateGroceryActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // Set onClickListener for Delete Button
        deleteGroceryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deleting the grocery item
                dbHandler.deleteGroceryItem(originalName);
                Toast.makeText(UpdateGroceryActivity.this, "Grocery Deleted!", Toast.LENGTH_SHORT).show();

                // Navigate back to MainActivity
                Intent i = new Intent(UpdateGroceryActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
