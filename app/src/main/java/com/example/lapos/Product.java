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
    private int qty=1;

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

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public int getCategory() { return category; }
    public void setCategory(int category) { this.category = category; }

    public int getSupplier() { return supplier; }
    public void setSupplier(int supplier) { this.supplier = supplier; }

    public int isManage_stock() { return manage_stock; }
    public void setManage_stock(int manage_stock) { this.manage_stock = manage_stock; }

    public int getQty() { return qty; }
    public void setQty(int q) { this.qty = q; }

    @Override
    public String toString() {
        return this.name;
    }

}
