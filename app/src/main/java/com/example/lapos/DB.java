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

    // TABLE NAMES
    private static final String TABLE_CATEGORY = "caegory";
    private static final String cat_id = "id";
    private static final String cat_labl = "label";

    String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + "(" +
            cat_id + " INTEGER PRIMARY KEY," +
            cat_labl + " TEXT" + ")";


    private static final String TABLE_PRODUCT = "product";
    /* Keys for Table Product */
    private static final String prd_id = "id";
    private static final String prd_name = "name";
    private static final String prd_price = "price";
    private static final String prd_description = "description";
    private static final String prd_image = "image";
    private static final String prd_color = "color";
    private static final String prd_solde = "solde";
    private static final String prd_category = "category";
    private static final String prd_supplier = "supplier";
    private static final String prd_manage_stock = "manage_stock";

    String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + "(" +
            prd_id + " INTEGER PRIMARY KEY," +
            prd_name + " TEXT," +
            prd_price + " REAL,"  +
            prd_description + " TEXT,"  +
            prd_image + " TEXT,"  +
            prd_color + " TEXT,"  +
            prd_solde + " REAL," +
            prd_category + " INTEGER,"  +
            prd_supplier + " INTEGER,"  +
            prd_manage_stock + " BOOLEAN "  + ")";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.v(TAG, "CREATE TABLE CAT: " + CREATE_TABLE_CATEGORY );
        db.execSQL(CREATE_TABLE_CATEGORY);

        Log.v(TAG, "CREATE TABLE CALL: " + CREATE_TABLE_PRODUCT );
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
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

        ContentValues values = new ContentValues();
        values.put( cat_labl, cat.getLabel() );

        long c = database.insert(TABLE_CATEGORY, null, values);
        database.close();
        return c;
    }

    /* Method for fetching record from Database */
    public ArrayList<Category> getAllCategories() {
        String query = "SELECT * FROM " + TABLE_CATEGORY;

        ArrayList<Category> categories = new ArrayList<Category>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery(query, null);

        if (c != null) {
            while (c.moveToNext()) {
                Category cat = new Category();
                cat.setId( c.getInt(c.getColumnIndex( cat_id ) ) );
                cat.setLabel( c.getString(c.getColumnIndex( cat_labl ) ) );

                categories.add(cat);
            }
        }
        //Log.v("TAGOS", " CATS : "  + s );
        return categories;
    }
    /******************************************
     * --------------PRODUCT-------------------
     * ******************************************/
    /* Method to create a Employee */
    public long createProduct(Product prd) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put( "name", prd.getName() );
        values.put( "price", prd.getPrice() );
        values.put( "description", prd.getDescription() );
        values.put( "image", prd.getImage() );
        values.put( "color", prd.getColor() );
        values.put( "solde", prd.getSolde() );
        values.put( "category", prd.getCategory() );
        values.put( "supplier", prd.getSupplier() );
        values.put( "manage_stock", prd.isManage_stock() );

        long c = database.insert(TABLE_PRODUCT, null, values);
        database.close();
        return c;
    }

    /* Method for fetching record from Database */
    public ArrayList<Product> getAllProduct(int catId) {
        String query = "SELECT * FROM " + TABLE_PRODUCT;
        if( catId > 0 )
            query += " WHERE category = " + catId;

        ArrayList<Product> products = new ArrayList<Product>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery(query, null);

        if (c != null) {
            while (c.moveToNext()) {
                Product prd = new Product();

                prd.setId( c.getInt(c.getColumnIndex( prd_id ) ) );
                prd.setName( c.getString(c.getColumnIndex( prd_name ) ) );
                prd.setPrice( c.getFloat(c.getColumnIndex( prd_price ) ) );
                prd.setDescription( c.getString(c.getColumnIndex( prd_description ) ) );
                prd.setImage( c.getString(c.getColumnIndex( prd_image ) ) );
                prd.setColor( c.getString(c.getColumnIndex( prd_color ) ) );
                prd.setSolde( c.getFloat(c.getColumnIndex( prd_solde ) ) );
                prd.setCategory( c.getInt(c.getColumnIndex( prd_category ) ) );
                prd.setSupplier( c.getInt(c.getColumnIndex( prd_supplier ) ) );
                prd.setManage_stock( c.getInt(c.getColumnIndex( prd_manage_stock ) ) );

                products.add(prd);
            }
        }
        Log.v("TAGOS", " query : "  + products.size() );
        return products;
    }

    /* This method is used to get a single record from Database.
    I have given an example, you have to do something like this.

    public Employee getEmployeeByCode(int code) {
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