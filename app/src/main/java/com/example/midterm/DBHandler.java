package com.example.midterm;

/**
 * DBHandler.java
 * Handles SQLite database operations for the Grocery List App.
 * Includes methods for creating the database, adding, reading, updating, and deleting grocery items.
 * Author: Abdullahi Ali
 * Date: 11/13/24
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // Database constants
    private static final String DB_NAME = "grocerydb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "grocery";
    private static final String ID_COL = "id";
    private static final String ITEM_NAME_COL = "item_name";
    private static final String QUANTITY_COL = "quantity";
    private static final String CATEGORY_COL = "category";
    private static final String PRICE_COL = "price";


    //Constructor for DBHandler

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEM_NAME_COL + " TEXT, "
                + QUANTITY_COL + " INTEGER, "
                + CATEGORY_COL + " TEXT, "
                + PRICE_COL + " REAL)";
        db.execSQL(query);
    }

    // Method to add new Grocery to SQLite database
    public void addNewGroceryItem(String itemName, int quantity, String category, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME_COL, itemName);
        values.put(QUANTITY_COL, quantity);
        values.put(CATEGORY_COL, category);
        values.put(PRICE_COL, price);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    // Method to read all contacts from the database
    public ArrayList<GroceryItemModal> readGroceryItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<GroceryItemModal> groceryList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                groceryList.add(new GroceryItemModal(
                        cursor.getString(1),  // item_name
                        cursor.getInt(2),    // quantity
                        cursor.getString(3), // category
                        cursor.getDouble(4)  // price
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return groceryList;
    }
    // Method to update a contact's details in the database
    public void updateGroceryItem(String originalName, String itemName, int quantity, String category, double price) {
        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ITEM_NAME_COL, itemName);
        values.put(QUANTITY_COL, quantity);
        values.put(CATEGORY_COL, category);
        values.put(PRICE_COL, price);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our grocery which is stored in original name variable.
        db.update(TABLE_NAME, values, "item_name=?", new String[]{originalName});
        db.close();
    }
    // Method to delete a contact from the database
    public void deleteGroceryItem(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "item_name=?", new String[]{itemName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

