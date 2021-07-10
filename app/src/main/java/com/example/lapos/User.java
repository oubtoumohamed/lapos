package com.example.lapos;

public class User {
    public int id;
    public String name;
    public String email;
    public String role;
    public String phone;
    public String cin;
    //public integer avatar;
    public String access_token;


    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
