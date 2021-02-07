package com.example.lapos;

public class Client {
    private int id;
    private String name;
    private String sexe;
    private String phone;
    private String adresse;

    public Client() {
        this.id = 0;
        this.name = "";
        this.sexe = "";
        this.phone = "";
        this.adresse = "";
    }

    public Client(int i, String l, String s, String p, String a) {
        this.id = i;
        this.name = l;
        this.sexe = s;
        this.phone = p;
        this.adresse = a;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String label) { this.name = label; }


    public String getSexe(){ return this.sexe; }
    public void setSexe( String sexe){ this.sexe = sexe; }

    public String getPhone(){ return this.phone; }
    public void setPhone( String phone){ this.phone = phone; }

    public String getAdresse(){ return this.adresse; }
    public void setAdresse( String adresse){ this.adresse = adresse; }

    @Override
    public String toString() {
        return this.name;
    }
}
