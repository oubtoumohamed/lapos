package com.example.lapos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    private String TAG = this.getClass().getSimpleName();
    private static final String DATABASE_NAME = "laposdb";
    private static final int DATABASE_VERSION = 1;

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(Category._CREATE_TABLE_);
        db.execSQL(Product._CREATE_TABLE_);
        db.execSQL(Order._CREATE_TABLE_);
        db.execSQL(Sale._CREATE_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + Category._TABLE_);
        db.execSQL("DROP TABLE IF EXISTS " + Product._TABLE_);
        db.execSQL("DROP TABLE IF EXISTS " + Order._TABLE_);
        db.execSQL("DROP TABLE IF EXISTS " + Sale._TABLE_);
        onCreate(db);
    }
    /******************************************
    * --------------CATEGORY-------------------
    * ******************************************/
    public long createCategory(Category cat) {
        /*String query = "DELETE FROM " + TABLE_CATEGORY;
        SQLiteDatabase databas= getWritableDatabase();
        databas.execSQL(query);*/
        SQLiteDatabase database = getWritableDatabase();
        long c = database.insert(Category._TABLE_, null, cat.prepare_insert());
        database.close();
        return c;
    }
    /* Method for fetching record from Database */
    public ArrayList<Category> getAllCategories() {
        String query = "SELECT * FROM " + Category._TABLE_;
        SQLiteDatabase database = getReadableDatabase();
        return Category.list( database.rawQuery(query, null) );
    }

    /******************************************
     * --------------PRODUCT-------------------
     * ******************************************/
    public long createProduct(Product prd) {
        SQLiteDatabase database = getWritableDatabase();
        long c = database.insert(Product._TABLE_, null, prd.prepare_insert());
        database.close();
        return c;
    }
    /* Method for fetching record from Database */
    public ArrayList<Product> getAllProduct(int catId) {
        String query = "SELECT * FROM " + Product._TABLE_;
        if( catId > 0 )
            query += " WHERE category = " + catId;

        SQLiteDatabase database = getReadableDatabase();
        return Product.list( database.rawQuery(query, null) );
    }

    public Product getProduct(int Id) {
        String query = "SELECT * FROM " + Product._TABLE_ + " where id = " + Id;

        SQLiteDatabase database = getReadableDatabase();
        return new Product( database.rawQuery(query, null) );
    }

    /******************************************
     * --------------ORDER-------------------
     * ******************************************/
    public long createOrder(Order ordr) {
        SQLiteDatabase database = getWritableDatabase();
        long c = database.insert(Order._TABLE_, null, ordr.prepare_insert());
        database.close();
        return c;
    }
    /* Method for fetching record from Database */
    public ArrayList<Order> getAllOrders() {
        String query = "SELECT * FROM " + Order._TABLE_;

        SQLiteDatabase database = getReadableDatabase();
        return Order.list( database.rawQuery(query, null) );
    }

    public Order getOrder(int Id) {
        String query = "SELECT * FROM " + Order._TABLE_ + " where id = " + Id;

        SQLiteDatabase database = getReadableDatabase();
        return new Order( database.rawQuery(query, null) );
    }

    /******************************************
     * --------------SALE-------------------
     * ******************************************/
    public long createSale(Sale sl) {
        SQLiteDatabase database = getWritableDatabase();
        long c = database.insert(Sale._TABLE_, null, sl.prepare_insert());
        database.close();
        return c;
    }
    /* Method for fetching record from Database */
    public ArrayList<Sale> getAllSales() {
        String query = "SELECT * FROM " + Sale._TABLE_;

        SQLiteDatabase database = getReadableDatabase();
        return Sale.list( database.rawQuery(query, null) );
    }

    public Sale getSale(int Id) {
        String query = "SELECT * FROM " + Sale._TABLE_ + " where id = " + Id;

        SQLiteDatabase database = getReadableDatabase();
        return new Sale( database.rawQuery(query, null) );
    }



    /* This method is used to get a single record from Database.
    I have given an example, you have to do something like this.

    public Employee getEmployeeByCode(int code) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + POSITION + " = " + position;

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int userId;

        if (cursor.moveToFirst())
        {
            userId = cursor.getInt(0);
        }

        cursor.close();
        return userId;


        String query = "SELECT * FROM " + TABLE_EMP + " WHERE " + KEY_CODE + " = " + code;
        Employee emp = new Employee();
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery(query, null);

        if (c.getCount() > 0) {

            c.moveToFirst();
            int code = c.getInt(c.getColumnIndex(KEY_CODE));
            String name = c.getString(c.getColumnIndex(KEY_NAME));
            String email = c.getString(c.getColumnIndex(KEY_EMAIL));
            String address = c.getString(c.getColumnIndex(KEY_ADDRESS));

            emp.setCode(code);
            emp.setName(name);
            emp.setEmail(email);
            emp.setAddress(address);

            Log.v("DBHelper: ", "Name: " + name);
            Log.v("DBHelper: ", "Code: " + code);
            Log.v("DBHelper: ", "Email: " + email);
            Log.v("DBHelper: ", "Address: " + address);


        }
        return emp;
    }*/
}