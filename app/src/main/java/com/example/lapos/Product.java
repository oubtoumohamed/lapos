package com.example.lapos;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description="";
    private String image="";
    private String color="#E6E6E6";
    private int category;
    private int supplier;
    private int manage_stock=0;

    public Product() {
        this.name = "";
        this.price = 0;
    }

    public Product( String na, double pr, String de, String im, String co, int ca, int su, int  ma) {
        this.name = na;
        this.price = pr;
        this.description = de;
        this.image = im;
        this.color = co;
        this.category = ca;
        this.supplier = su;
        this.manage_stock = ma;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String img) { this.image = img; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCategory() { return category; }
    public void setCategory(int category) { this.category = category; }

    public int getSupplier() { return supplier; }
    public void setSupplier(int supplier) { this.supplier = supplier; }

    public int isManage_stock() { return manage_stock; }
    public void setManage_stock(int manage_stock) { this.manage_stock = manage_stock; }

    @Override
    public String toString() { return this.name;  }


    // DB table

    public static final String _TABLE_ = "product";
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

    public static String _CREATE_TABLE_ = "CREATE TABLE " + _TABLE_ + "(" +
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


    public ContentValues prepare_insert(){
        ContentValues values = new ContentValues();
        values.put( prd_name, this.getName() );
        values.put( prd_price, this.getPrice() );
        values.put( prd_description, this.getDescription() );
        values.put( prd_image, this.getImage() );
        values.put( prd_color, this.getColor() );
        values.put( prd_category, this.getCategory() );
        values.put( prd_supplier, this.getSupplier() );
        values.put( prd_manage_stock, this.isManage_stock() );

        return values;
    }

    public static ArrayList<Product> list(Cursor c ){
        ArrayList<Product> products = new ArrayList<Product>();

        if (c != null) {
            while (c.moveToNext()) {
                Product prd = new Product();

                prd.setId( c.getInt(c.getColumnIndex( prd_id ) ) );
                prd.setName( c.getString(c.getColumnIndex( prd_name ) ) );
                prd.setPrice( c.getFloat(c.getColumnIndex( prd_price ) ) );
                prd.setDescription( c.getString(c.getColumnIndex( prd_description ) ) );
                prd.setImage( c.getString(c.getColumnIndex( prd_image ) ) );
                prd.setColor( c.getString(c.getColumnIndex( prd_color ) ) );
                prd.setCategory( c.getInt(c.getColumnIndex( prd_category ) ) );
                prd.setSupplier( c.getInt(c.getColumnIndex( prd_supplier ) ) );
                prd.setManage_stock( c.getInt(c.getColumnIndex( prd_manage_stock ) ) );

                products.add(prd);
            }
        }

        return  products;
    }


}
