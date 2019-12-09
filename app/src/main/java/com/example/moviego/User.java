package com.example.moviego;

public class User {
    public String email,username,password,job,tahun;
    public User () {

    }

    public  User (String email,String username,String password,String job,String tahun) {
        this.email = email;
        this.username=username;
        this.password = password;
        this.job = job;
        this.tahun = tahun;
    }
}