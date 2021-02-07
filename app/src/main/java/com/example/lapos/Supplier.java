package com.example.lapos;

public class Supplier {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String adresse;

    public Supplier() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.phone = "";
        this.adresse = "";
    }

    public Supplier(int i, String l, String s, String p, String a) {
        this.id = i;
        this.name = l;
        this.email = s;
        this.phone = p;
        this.adresse = a;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String label) { this.name = label; }


    public String getEmail(){ return this.email; }
    public void setEmail( String email){ this.email = email; }

    public String getPhone(){ return this.phone; }
    public void setPhone( String phone){ this.phone = phone; }

    public String getAdresse(){ return this.adresse; }
    public void setAdresse( String adresse){ this.adresse = adresse; }

    @Override
    public String toString() {
        return this.name;
    }
}