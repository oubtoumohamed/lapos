package com.example.lapos;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class Order {

    private int id;
    private Product product;
    private int sale_id =  0;
    private double solde = 0;
    private float qty=1;

    public Order() {
    }

    public Order(Product product) {
        this.product = product;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double price() { return product.getPrice() - ( ( product.getPrice() * solde ) / 100 ); }

    public double getSolde() { return solde; }
    public String getStringSolde() {
        if(solde == (long) solde)
            return String.format("%d",(long)solde);
        else
            return String.format("%s",solde);
    }
    public double calculSolde() { return ( product.getPrice() * solde ) / 100 ; }
    public void setSolde(double solde) { this.solde = ( solde > 0 ) ? solde : 0 ; }

    public float getQty() { return qty; }
    public String getStringQty() {
        if(qty == (long) qty)
            return String.format("%d",(long)qty);
        else
            return String.format("%s",qty);
    }
    public void setQty(float q) { this.qty = q > 0 ? q : 1 ; }
    public void upQty() { this.qty ++; }
    public void downQty() { if( this.qty > 1 ) this.qty --; }

    public int getSale_id() {  return sale_id; }

    public void setSale_id(int sale_id) { this.sale_id = sale_id; }

    @Override
    public String toString() {
        if( this.solde == 0 )
            return this.product.toString();

        return this.product.toString() + "\n \n \t\t - Remise ( " + this.getStringSolde() + "% ) : -" + this.calculSolde() ;
    }


    // DB table

    public static final String TABLE_ORDER = "order";
    /* Keys for Table Product */
    private static final String _id = "id";
    private static final String prd_id = "product_id";
    private static final String quantity = "quantity";
    private static final String prd_solde = "solde";
    private static final String order_sale_id = "sale_id";

    public static String CREATE_TABLE_ORDER = "CREATE TABLE " + TABLE_ORDER + "(" +
            _id + " INTEGER PRIMARY KEY," +
            prd_id + " INTEGER," +
            quantity + " REAL," +
            prd_solde + " REAL," +
            order_sale_id + " INTEGER," + ")";


    public ContentValues prepare_insert(){
        ContentValues values = new ContentValues();

        values.put( prd_id, product.getId() );
        values.put( quantity, this.getQty() );
        values.put( prd_solde, this.getSolde() );
        values.put( order_sale_id, this.getSale_id() );

        return values;
    }

    public static ArrayList<Order> list(Cursor c ){
        ArrayList<Order> orders = new ArrayList<Order>();

        if (c != null) {
            while (c.moveToNext()) {
                Order ord = new Order();

                ord.setId( c.getInt(c.getColumnIndex( _id ) ) );

                ord.setQty( c.getFloat(c.getColumnIndex( quantity ) ) );
                ord.setSolde( c.getFloat(c.getColumnIndex( prd_solde ) ) );
                ord.setSale_id( c.getInt(c.getColumnIndex( order_sale_id ) ) );

                orders.add(ord);
            }
        }

        return  orders;
    }

}
