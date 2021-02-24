package com.example.lapos;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class Category {
    private int id;
    private String label;

    public Category() {
        this.id = 0;
        this.label = "";
    }

    public Category(int i, String l) {
        this.id = i;
        this.label = l;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLabel() { return label; }

    public void setLabel(String label) { this.label = label; }

    @Override
    public String toString() {
        return this.id + " : " +this.label;
    }


    // DB table

    public static final String TABLE_CATEGORY = "caegory";
    public static final String cat_id = "id";
    private static final String cat_labl = "label";

    public static String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + "(" +
            cat_id + " INTEGER PRIMARY KEY," +
            cat_labl + " TEXT" + ")";

    public ContentValues prepare_insert(){
        ContentValues values = new ContentValues();
        values.put( cat_labl, this.getLabel() );
        return values;
    }

    public static ArrayList<Category> list( Cursor c ){
        ArrayList<Category> categories = new ArrayList<Category>();
        if (c != null) {
            while (c.moveToNext()) {
                Category cat = new Category();
                cat.setId( c.getInt(c.getColumnIndex( cat_id ) ) );
                cat.setLabel( c.getString(c.getColumnIndex( cat_labl ) ) );

                categories.add(cat);
            }
        }
        //Log.v("TAGOS", " CATS : "  + s );
        return  categories;
    }
}
