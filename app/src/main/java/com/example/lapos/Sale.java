package com.example.lapos;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class Sale {

    private int id;
    private String caiser_name;
    private String date;
    private String time;
    private double total = 0;

    public Sale() {
    }

    public Sale( Cursor c ) {
        if (c.moveToFirst())
        {
            this.setId( c.getInt(c.getColumnIndex( _id ) ) );
            this.setCaiser_name( c.getString(c.getColumnIndex( _caiser_name ) ) );
            this.setDate( c.getString(c.getColumnIndex( _date ) ) );
            this.setTime( c.getString(c.getColumnIndex( _time ) ) );
            this.setTotal( c.getFloat(c.getColumnIndex( _total ) ) );
        }
        c.close();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCaiser_name() {
        return caiser_name;
    }

    public void setCaiser_name(String caiser_name) {
        this.caiser_name = caiser_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // DB table

    public static final String _TABLE_ = "sales";
    /* Keys for Table Product */
    private static final String _id = "id";
    private static final String _caiser_name = "caiser_name";
    private static final String _date = "date_";
    private static final String _time = "time_";
    private static final String _total = "total";

    public static String _CREATE_TABLE_ = "CREATE TABLE " + _TABLE_ + "(" +
            _id + " INTEGER PRIMARY KEY," +
            _caiser_name + " TEXT," +
            _date + " TEXT,"  +
            _time + " TEXT,"  +
            _total + " REAL," + ")";

    public ContentValues prepare_insert(){
        ContentValues values = new ContentValues();

        values.put( _caiser_name, this.getCaiser_name() );
        values.put( _date, this.getDate() );
        values.put( _time, this.getTime() );
        values.put( _total, this.getTotal() );

        return values;
    }

    public static ArrayList<Sale> list(Cursor c ){
        ArrayList<Sale> sales = new ArrayList<Sale>();

        if (c != null) {
            while (c.moveToNext()) {
                Sale sl = new Sale();

                sl.setId( c.getInt(c.getColumnIndex( _id ) ) );
                sl.setCaiser_name( c.getString(c.getColumnIndex( _caiser_name ) ) );
                sl.setDate( c.getString(c.getColumnIndex( _date ) ) );
                sl.setTime( c.getString(c.getColumnIndex( _time ) ) );
                sl.setTotal( c.getFloat(c.getColumnIndex( _total ) ) );

                sales.add(sl);
            }
        }

        return  sales;
    }
}
