package com.example.lapos;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class Sale {

    private int id;
    private String caiser_name;
    private String datetime;
    private float total = 0;
    private float paid;
    private String payment_method;
    private String comment;
    private String state;

    public Sale() {
    }

    public Sale( Cursor c ) {
        if (c.moveToFirst())
        {
            this.setId( c.getInt(c.getColumnIndex( _id ) ) );
            this.setCaiser_name( c.getString(c.getColumnIndex( _caiser_name ) ) );
            this.setDatetime( c.getString(c.getColumnIndex( _date_time ) ) );
            this.setTotal( c.getFloat(c.getColumnIndex( _total ) ) );
            this.setPaid( c.getFloat(c.getColumnIndex( _paid ) ) );

            this.setPayment_method( c.getString(c.getColumnIndex( _payment_method ) ) );
            this.setComment( c.getString(c.getColumnIndex( _comment ) ) );
            this.setState( c.getString(c.getColumnIndex( _state ) ) );
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datet) {
        this.datetime = datet;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPaid() { return paid; }
    public void setPaid(float paid) { this.paid = paid; }

    public String getPayment_method() { return payment_method; }
    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String toString(){
        return  id + " " + caiser_name + " " + datetime + " " + total + " " + paid + " " + payment_method + " " + comment+ " " + state;
    }



    // DB table
    
    public static final String _TABLE_ = "sales";
    /* Keys for Table Product */
    private static final String _id = "id";
    private static final String _caiser_name = "caiser_name";
    private static final String _date_time = "date_time";
    private static final String _total = "total";
    private static final String _paid = "paid";
    private static final String _payment_method = "payment_method";
    private static final String _comment = "comment";
    private static final String _state = "state";

    public static String _CREATE_TABLE_ = "CREATE TABLE " + _TABLE_ + "(" +
            _id + " INTEGER PRIMARY KEY," +
            _caiser_name + " TEXT," +
            _date_time + " TEXT,"  +
            _total + " REAL," +
            _paid + " REAL," +
            _payment_method + " TEXT," +
            _comment + " TEXT," +
            _state + " TEXT" + ")";

    public ContentValues prepare_insert(){
        ContentValues values = new ContentValues();

        values.put( _caiser_name, this.getCaiser_name() );
        values.put( _date_time, this.getDatetime() );
        values.put( _total, this.getTotal() );
        values.put( _paid, this.getPaid() );
        values.put( _payment_method, this.getPayment_method() );
        values.put( _comment, this.getComment() );
        values.put( _state, this.getState() );

        return values;
    }

    public static ArrayList<Sale> list(Cursor c ){
        ArrayList<Sale> sales = new ArrayList<Sale>();

        if (c != null) {
            while (c.moveToNext()) {
                Sale sl = new Sale();

                sl.setId( c.getInt(c.getColumnIndex( _id ) ) );
                sl.setCaiser_name( c.getString(c.getColumnIndex( _caiser_name ) ) );
                sl.setDatetime( c.getString(c.getColumnIndex( _date_time ) ) );
                sl.setTotal( c.getFloat(c.getColumnIndex( _total ) ) );
                sl.setPaid( c.getFloat(c.getColumnIndex( _paid ) ) );

                sl.setPayment_method( c.getString(c.getColumnIndex( _payment_method ) ) );
                sl.setComment( c.getString(c.getColumnIndex( _comment ) ) );
                sl.setState( c.getString(c.getColumnIndex( _state ) ) );

                sales.add(sl);
            }
        }

        return  sales;
    }
}
