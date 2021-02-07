package com.example.lapos;

public class Category {
    private int id;
    private String label;

    public Category() {
        this.id = 0;
        this.label = "";
    }

    public Category(int i, String l) {
        this.id = i;
        this.label = l;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLabel() { return label; }

    public void setLabel(String label) { this.label = label; }

    @Override
    public String toString() {
        return this.id + " : " +this.label;
    }
}
