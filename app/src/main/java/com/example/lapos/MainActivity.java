package com.example.lapos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DB databaseHelper;
    ArrayList<Category> categories;
    ArrayList<Product> products;
    GridView productsGridView;
    TabLayout categoriesTab;
    ProductAdapter productAdapter;
    ListView carte;
    CarteAdapter carteAdapter;
    int selectedCat = 0;
    TextView txtTotal;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DB(MainActivity.this);
        categoriesTab = findViewById(R.id.TabCats);
        productsGridView = findViewById(R.id.ProductGridView);
        txtTotal = findViewById(R.id.total);

        loadCategores();
        loadProducts();
        carteAdapter = new CarteAdapter(this);
        carte = (ListView)findViewById(R.id.carte);
        carte.setAdapter(carteAdapter);
    }

    public void loadCategores(){
        categories = new ArrayList<Category>();
        categories = databaseHelper.getAllCategories();

        Log.v("TAGOS", "LOAD CATS : "  + categories.toString() );
        // get the reference of TabLayout
        for ( Category cat : categories ) {
            if( selectedCat == 0)
                selectedCat = cat.getId();

            TabLayout.Tab firstTab = categoriesTab.newTab();
            firstTab.setText(cat.getLabel());
            //firstTab.setIcon(R.drawable.ic_launcher); // set an icon for the first tab
            categoriesTab.addTab(firstTab);
        }

        categoriesTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // called when tab selected
                selectedCat = categories.get( tab.getPosition() ).getId();
                loadProducts();
                //Log.v("TAGOS", "selected : "  + categories.get( pos ).toString() );
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }


    public void loadProducts(){
        products = databaseHelper.getAllProduct(selectedCat);
        productAdapter = new ProductAdapter(this, products);
        productsGridView.setAdapter(productAdapter);

        //Log.v("TAGOS", "LOAD CATS : "  + categories.toString() );
        productsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //selectedCat = categories.get( position ).getId();
                //loadProducts();
                Log.v("TAGOS", "selected : "  + products.get( position ).toString() );

                carteAdapter.addProduct( products.get( position ) );
                //carteAdapter = new CarteAdapter(getApplicationContext(), carte_products);
                carteAdapter.notifyDataSetChanged();

                txtTotal.setText( carteAdapter.Total + " DH" );
            }
        });
    }

}