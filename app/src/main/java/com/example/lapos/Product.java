package com.example.lapos;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description="";
    private String image="";
    private String color="#E6E6E6";
    private double solde = 0;
    private int category;
    private int supplier;
    private int manage_stock=0;

    // used only for carte
    private float qty=1;

    public Product() {
        this.name = "";
        this.price = 0;
    }

    public Product( String na, double pr, String de, String im, String co, double so, int ca, int su, int  ma) {
        this.name = na;
        this.price = pr;
        this.description = de;
        this.image = im;
        this.color = co;
        this.solde = so;
        this.category = ca;
        this.supplier = su;
        this.manage_stock = ma;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String img) { this.image = img; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getPrice() { return price - ( ( price * solde ) / 100 ); }
    public void setPrice(double price) { this.price = price; }

    public double getSolde() { return solde; }
    public String getStringSolde() {
        if(solde == (long) solde)
            return String.format("%d",(long)solde);
        else
            return String.format("%s",solde);
    }
    public double calculSolde() { return ( price * solde ) / 100 ; }
    public void setSolde(double solde) { this.solde = solde > 0 ? solde : 0 ; }

    public int getCategory() { return category; }
    public void setCategory(int category) { this.category = category; }

    public int getSupplier() { return supplier; }
    public void setSupplier(int supplier) { this.supplier = supplier; }

    public int isManage_stock() { return manage_stock; }
    public void setManage_stock(int manage_stock) { this.manage_stock = manage_stock; }

    public float getQty() { return qty; }
    public String getStringQty() {
        if(qty == (long) qty)
            return String.format("%d",(long)qty);
        else
            return String.format("%s",qty);
    }
    public void setQty(float q) { this.qty = q >= 1 ? q : 1 ; }
    public void upQty() { this.qty ++; }
    public void downQty() { if( this.qty > 1 ) this.qty --; }

    @Override
    public String toString() {
        if( this.solde == 0 )
            return this.name;

        return this.name + "\n \n \t\t\t - Remise ( " + this.getStringSolde() + "% ) : -" + this.calculSolde() ;
    }

}
