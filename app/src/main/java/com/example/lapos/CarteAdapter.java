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
    private final ArrayList<Product> products;
    private float Total;

    public CarteAdapter(Context context){//, ArrayList<Product> p) {
        this.mContext = context;
        this.products = new ArrayList<Product>();
        this.Total = 0;
    }

    public boolean isEmty(){
        return this.products.isEmpty();
    }

    public void empty(){
        this.products.clear();
        this.Total = 0;
    }

    public float getTotal() {
        updateTotal();
        return this.Total;
    }

    public void addProduct(Product prd) {
        /*boolean exist = false;
        this.Total = 0;

        for ( Product p : this.products ) {
            if( p.getId() == prd.getId() ){
                p.upQty();
                exist = true;
            }
            this.Total += p.getPrice() * p.getQty();
        }
        if( exist == false) {
            this.products.add(prd);
            this.Total += prd.getPrice() * prd.getQty();
        }
        */

        if( this.products.contains(prd) ){
            // update producte
            int pos = this.products.indexOf(prd);
            this.products.get( pos ).upQty();
        }else {
            // add product
            this.products.add(prd);
        }

        this.Total += prd.getPrice();
    }

    public void setProductQty(int position, float qty) {
        Product p = products.get(position);
        if( p != null ) {
            p.setQty(qty);
        }
    }

    public void upProductQty(int position) {
        Product p = products.get(position);
        if( p != null ) {
            p.upQty();
        }
    }

    public void downProductQty(int position) {
        Product p = products.get(position);
        if( p != null ) {
            p.downQty();
        }
    }

    public void deleteProduct(int position) {
        Product p = products.get(position);
        if( p != null ) {
            this.products.remove(p);
        }
        /*float ttl = 0;
        for ( Product p : this.products ) {
            if( p.getId() == prd.getId() ){
                p.setQty( p.getQty() + 1 );
                exist = true;
            }
            ttl += p.getPrice() * p.getQty();
        }
        if( exist == false) {
            this.products.add(prd);
            ttl += prd.getPrice() * prd.getQty();
        }
        this.Total = ttl;*/
    }

    public void updateTotal() {
        this.Total = 0;
        for ( Product prd : this.products ) {

            Log.v("TAGOS", "// : "  + prd.getName() + " : " + prd.getPrice() + " : " + prd.getQty() );

            this.Total += prd.getPrice() * prd.getQty();
        }
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        Product product = products.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.carteitem, null);
        }

        final TextView prdName = (TextView)convertView.findViewById(R.id.txt_prdname);
        final TextView prdPrice = (TextView)convertView.findViewById(R.id.txt_prdprice);

        prdName.setText( product.getStringQty() +  "  x  " + product.toString() );
        prdPrice.setText( ( product.getPrice() * product.getQty() ) + "" );

        return convertView;
    }

}
