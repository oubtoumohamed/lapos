package com.example.lapos;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class CarteAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Order> orders;
    private float Total;

    public CarteAdapter(Context context){//, ArrayList<Order> p) {
        this.mContext = context;
        this.orders = new ArrayList<Order>();
        this.Total = 0;
    }

    public ArrayList<Order> getOrders() { return orders; }

    public boolean isEmty(){
        return this.orders.isEmpty();
    }

    public void empty(){
        this.orders.clear();
        this.Total = 0;
    }

    public float getTotal() {
        updateTotal();
        return this.Total;
    }

    public void addOrder(Order order) {
        /*boolean exist = false;
        this.Total = 0;

        for ( Order o : this.Orders ) {
            if( p.getId() == order.getId() ){
                p.upQty();
                exist = true;
            }
            this.Total += p.getPrice() * p.getQty();
        }
        if( exist == false) {
            this.Orders.add(order);
            this.Total += order.getPrice() * order.getQty();
        }
        if( this.orders.contains(order) ){
            // update Ordere
            int pos = orders.indexOf(order);
            orders.get( pos ).upQty();
        }else {
            // add Order
            orders.add(order);
        }
        */

        orders.add(order);
        Total += order.price();
    }

    public void setOrderQty(int position, float qty) {
        Order o = orders.get(position);
        if( o != null ) {
            o.setQty(qty);
        }
    }

    /*public void upOrderQty(int position) {
        Order o = orders.get(position);
        if( p != null ) {
            p.upQty();
        }
    }

    public void downOrderQty(int position) {
        Order o = orders.get(position);
        if( p != null ) {
            p.downQty();
        }
    }*/

    public void setOrderSolde(int position, float sld) {
        Order o = orders.get(position);
        if( o != null ) {
            o.setSolde(sld);
        }
    }

    public void deleteOrder(int position) {
        Order o = orders.get(position);
        if( o != null ) {
            this.orders.remove(o);
        }
        /*float ttl = 0;
        for ( Order o : this.Orders ) {
            if( p.getId() == order.getId() ){
                p.setQty( p.getQty() + 1 );
                exist = true;
            }
            ttl += p.getPrice() * p.getQty();
        }
        if( exist == false) {
            this.Orders.add(order);
            ttl += order.getPrice() * order.getQty();
        }
        this.Total = ttl;*/
    }

    public void updateTotal() {
        Total = 0;
        for ( Order order : orders ) {
            Total += order.price() * order.getQty();
        }
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Order getItem(int position) {
        return orders.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        Order Order = orders.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.carteitem, null);
        }

        final TextView orderName = (TextView)convertView.findViewById(R.id.txt_prdname);
        final TextView orderPrice = (TextView)convertView.findViewById(R.id.txt_prdprice);

        orderName.setText( Order.getStringQty() +  "  x  " + Order.toString() );
        orderPrice.setText( ( Order.price() * Order.getQty() ) + "" );

        return convertView;
    }

}
