package com.example.lapos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class CarteAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Product> products = new ArrayList<Product>();
    public float Total = 0;

    public CarteAdapter(Context context) {
        this.mContext = context;
        //this.products = products;
    }

    public void addProduct(Product prd) {
        boolean exist = false;
        float ttl = 0;
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
        this.Total = ttl;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
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

        prdName.setText( product.getQty() + "  X  " +product.getName() );
        prdPrice.setText( product.getPrice() + "" );

        return convertView;
    }
}
