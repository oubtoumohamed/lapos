package com.example.lapos;

public class Sale {

    // DB table

    public static final String TABLE_NAME = "sales";
    /* Keys for Table Product */
    private static final String _id = "id";
    private static final String _caiser_name = "caiser_name";
    private static final String _date = "date_";
    private static final String _time = "time_";
    private static final String prd_description = "description";
    private static final String prd_image = "image";
    private static final String prd_color = "color";
    private static final String prd_solde = "solde";
    private static final String prd_category = "category";
    private static final String prd_supplier = "supplier";
    private static final String prd_manage_stock = "manage_stock";

    public static String _CREATE_TABLE_ = "CREATE TABLE " + TABLE_NAME + "(" +
            _id + " INTEGER PRIMARY KEY," +
            _name + " TEXT," +
            prd_price + " REAL,"  +
            _caiser_name + " TEXT,"  +
            prd_image + " TEXT,"  +
            prd_color + " TEXT,"  +
            prd_solde + " REAL," +
            prd_category + " INTEGER,"  +
            prd_supplier + " INTEGER,"  +
            prd_manage_stock + " BOOLEAN "  + ")";

}
