package com.hklimited.mimofun;

public class MainModel
{
    String name,email,picture,phone,id_number,uid;

    public MainModel() {
    }

    public MainModel(String name, String email, String picture, String phone, String id_number, String uid) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.phone = phone;
        this.id_number = id_number;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
