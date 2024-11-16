package com.example.midterm;

/**
 * ViewGrocery.java
 * Displays the list of grocery items in a RecyclerView.
 * Author: Abdullahi Ali
 * Date: 11/13/24
 */

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewGrocery extends AppCompatActivity {

    private RecyclerView groceryRecyclerView;
    private GroceryRVAdapter groceryRVAdapter;
    private ArrayList<GroceryItemModal> groceryItemList;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grocery);

        dbHandler = new DBHandler(ViewGrocery.this);
        groceryItemList = dbHandler.readGroceryItems();

        groceryRecyclerView = findViewById(R.id.idRVGrocery);
        groceryRVAdapter = new GroceryRVAdapter(groceryItemList, ViewGrocery.this);

        groceryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        groceryRecyclerView.setAdapter(groceryRVAdapter);
    }
}

