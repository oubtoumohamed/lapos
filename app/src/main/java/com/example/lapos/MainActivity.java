package com.example.lapos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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

        carte.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("TAGOS", "selected : "  + position );
                //
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.edit_menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //switch (item.getItemId())
                        switch (item.getItemId()) {
                            case R.id.MenuChangeQuantity:
                                changeQty(position);
                                break;
                            case R.id.MenuChangeSolde:
                                changeRemise(position);
                                break;
                            case R.id.MenuDeleteProduct:
                                carteAdapter.deleteOrder(position);
                                carteupdate();
                                break;
                        }
                        //Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });

    }

    public void loadCategores(){
        categories = new ArrayList<Category>();
        categories = databaseHelper.getAllCategories();

        //Log.v("TAGOS", "LOAD CATS : "  + categories.toString() );
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
                selectedCat = categories.get( tab.getPosition() ).getId();
                loadProducts();
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

                carteAdapter.addOrder( new Order(products.get( position )) );
                carteupdate();
            }
        });
    }

    public void carteupdate(){
        carteAdapter.notifyDataSetChanged();
        txtTotal.setText( carteAdapter.getTotal() + " DH" );
    }

    public void emptycarte(View view){
        if ( ! carteAdapter.isEmty() )
            new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.confirm)
                .setMessage(R.string.carte_confirm_empty)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        carteAdapter.empty();
                        carteupdate();
                    }
                }).setNegativeButton(R.string.no, null).show();
    }

    public void changeQty(int position){
        final EditText input = new EditText(this);
        input.setInputType( InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL );
        input.setPadding(50,50,50,50);
        input.setText( carteAdapter.getItem(position).getStringQty() );

        new AlertDialog.Builder(this)
                .setTitle(R.string.product_qty_full)
                .setView( input )
                .setPositiveButton(R.string.vlidate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float qt = Float.parseFloat( "0" + input.getText().toString() );
                        carteAdapter.setOrderQty( position, qt );
                        carteupdate();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public void changeRemise(int position){
        final EditText input = new EditText(this);
        input.setInputType( InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL );
        input.setPadding(50,50,50,50);
        input.setText( carteAdapter.getItem(position).getStringSolde() );

        new AlertDialog.Builder(this)
                .setTitle(R.string.product_solde)
                .setView( input )
                .setPositiveButton(R.string.vlidate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float solde = Float.parseFloat( "0" + input.getText().toString() );
                        carteAdapter.setOrderSolde( position, solde );
                        carteupdate();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}