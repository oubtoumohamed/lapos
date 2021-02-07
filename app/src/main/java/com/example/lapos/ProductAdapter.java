package com.example.lapos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.mContext = context;
        this.products = products;
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
            convertView = layoutInflater.inflate(R.layout.product, null);
        }

        final ConstraintLayout prdImage = (ConstraintLayout)convertView.findViewById(R.id.prdImage);
        final TextView prdName = (TextView)convertView.findViewById(R.id.prdName);
        final TextView prdPrice = (TextView)convertView.findViewById(R.id.prdPrice);

        try {
            if( ! product.getColor().equals("") )
                prdImage.setBackgroundColor(Color.parseColor( product.getColor() ));

            if( ! product.getImage().equals("") )
                prdImage.setBackgroundResource(mContext.getResources().getIdentifier(product.getImage(), "drawable", mContext.getPackageName()) );
        }catch (Exception e){ }

        prdName.setText( product.getName() );
        prdPrice.setText( product.getPrice() + " DH" );

        return convertView;
    }

}