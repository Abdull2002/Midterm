package com.example.midterm;

/**
 * GroceryRVAdapter.java
 * Adapter for displaying grocery items in a RecyclerView.
 * Author: Abdullahi Ali
 * Date: 11/13/24
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroceryRVAdapter extends RecyclerView.Adapter<GroceryRVAdapter.ViewHolder> {
    // Variables for the contact list and context
    private ArrayList<GroceryItemModal> groceryList;
    private Context context;

    // Constructor
    public GroceryRVAdapter(ArrayList<GroceryItemModal> groceryList, Context context) {
        this.groceryList = groceryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Setting data to views in each RecyclerView item
        GroceryItemModal modal = groceryList.get(position);
        holder.itemNameTV.setText(modal.getItemName());
        holder.quantityTV.setText("Quantity: " + modal.getQuantity());
        holder.categoryTV.setText("Category: " + modal.getCategory());
        holder.priceTV.setText("Price: $" + modal.getPrice());

        // Set an onClickListener for updating or deleting the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateGroceryActivity.class);

                // below we are passing all our values.
                i.putExtra("item_name", modal.getItemName());
                i.putExtra("quantity", modal.getQuantity());
                i.putExtra("category", modal.getCategory());
                i.putExtra("price", modal.getPrice());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Returning the size of the contact list
        return groceryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // TextView variables for displaying contact details
        private TextView itemNameTV, quantityTV, categoryTV, priceTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initializing TextViews with their respective IDs
            itemNameTV = itemView.findViewById(R.id.idTVItemName);
            quantityTV = itemView.findViewById(R.id.idTVQuantity);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
            priceTV = itemView.findViewById(R.id.idTVPrice);
        }
    }
}


