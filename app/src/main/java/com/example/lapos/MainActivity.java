package com.example.lapos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.widget.Toolbar;

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
    AlertDialog payment_Dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        databaseHelper = new DB(MainActivity.this);
        categoriesTab = findViewById(R.id.TabCats);
        productsGridView = findViewById(R.id.ProductGridView);
        txtTotal = findViewById(R.id.total);

        loadCategores();
        loadProducts();

        loadSales();

        carteAdapter = new CarteAdapter(this);
        carte = (ListView)findViewById(R.id.carte);
        carte.setAdapter(carteAdapter);

        carte.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("TAGOS", "selected : "  + position );
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

    public void loadSales(){

        ArrayList<Order> orders = new ArrayList<Order>();
        orders = databaseHelper.getAllOrders();

        for ( Order ordr : orders ) {
            //ordr.setSale_id(sale_id);
            //long ordr_id = databaseHelper.createOrder(ordr);
            Log.v("ORDER", "ORDER  : " + ordr._toString() );
        }


        ArrayList<Sale> sales = new ArrayList<Sale>();
        sales = databaseHelper.getAllSales();
        //Log.v("TAGOS", "LOAD CATS : "  + categories.toString() );
        // get the reference of TabLayout
        for ( Sale sl : sales ) {
            Log.v("SALES", "SALES : "  + sl.toString() );
        }

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
                .setCancelable(false)
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
                .setCancelable(false)
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
                .setCancelable(false)
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

    public void payment(View view){
        if ( ! carteAdapter.isEmty() ) {
            payment_Dialog = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle(R.string.carte_payment)
                    .setView(getLayoutInflater().inflate(R.layout.activity_payement, null))
                    .show();

            TextView txt_payment_total = (TextView) payment_Dialog.findViewById(R.id.txt_payment_total);
            txt_payment_total.setText(carteAdapter.getTotal() + "");
        }
    }

    public void btn_calculate_(View view){
        Button btn = (Button) view;
        String btn_txt = btn.getText().toString();

        TextView txt_calculator = (TextView) payment_Dialog.findViewById(R.id.txt_calculator);
        String calc_txt = txt_calculator.getText().toString();

        switch (btn_txt){
            case ".":
                if ( ! calc_txt.contains(btn_txt) )
                    calc_txt += btn_txt;
                break;
            case "d":
                if( calc_txt.length() > 0)
                    calc_txt = calc_txt.substring( 0, calc_txt.length() - 1 );
                break;
            case "c":
                calc_txt = "";
                break;
            case "Rendu":

                TextView txt_payment_donne = (TextView) payment_Dialog.findViewById(R.id.txt_payment_donne);
                TextView txt_payment_rendu = (TextView) payment_Dialog.findViewById(R.id.txt_payment_rendu);

                float donne = Float.parseFloat( "0"+calc_txt );
                txt_payment_donne.setText(donne +"");
                txt_payment_rendu.setText( ( donne - carteAdapter.getTotal() ) + "" );
                break;
            default:
                calc_txt += btn_txt;
                break;
        }

        txt_calculator.setText(calc_txt);
    }

    public void payment_cancel(View v){
        payment_Dialog.dismiss();

    }

    public void payment_pendding(View v){
        payment_save("Pending");
    }

    public void payment_validate(View v){
        payment_save("Completed");
        carteAdapter.empty();
        carteupdate();
    }

    public void payment_save(String state){

        MaterialButtonToggleGroup payment_metod = payment_Dialog.findViewById(R.id.payment_metod);
        int buttonId = payment_metod.getCheckedButtonId();

        MaterialButton button = payment_metod.findViewById(buttonId);
        //Toast.makeText(MainActivity.this, "jmlk", Toast.LENGTH_LONG).show();

        Sale sale = new Sale();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sale.setDatetime( dateFormat.format( Calendar.getInstance().getTime() ) );

        sale.setCaiser_name("Mohamed");
        sale.setTotal( carteAdapter.getTotal() );

        TextView txt_calculator = (TextView) payment_Dialog.findViewById(R.id.txt_calculator);
        String calc_txt = "0"+txt_calculator.getText().toString();
        sale.setPaid(  Float.parseFloat( calc_txt ) );

        EditText txt_comments = (EditText) payment_Dialog.findViewById(R.id.comment);
        sale.setComment( txt_comments.getText().toString() );

        sale.setState(state);

        switch ( payment_metod.getCheckedButtonId() ){
            case R.id.visa :
                sale.setPayment_method("Visa");
                break;
            case R.id.cash :
                sale.setPayment_method("Cash");
                break;
            case R.id.division :
                sale.setPayment_method("Division");
                break;
        }

        int sale_id = (int) databaseHelper.createSale(sale);
        Toast.makeText(MainActivity.this, "Id : "+sale_id, Toast.LENGTH_LONG).show();

        if( sale_id > 0 ){
            for ( Order ordr : carteAdapter.getOrders() ) {
                ordr.setSale_id(sale_id);
                long ordr_id = databaseHelper.createOrder(ordr);
                Log.v("ORDER", "ORDER  : " + ordr_id +" || "+ ordr._toString() );
            }
        }

        payment_Dialog.dismiss();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}