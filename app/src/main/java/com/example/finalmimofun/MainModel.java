package com.example.finalmimofun;

public class MainModel
{
    String name,age,email,picture;

    MainModel()
    {

    }

    public MainModel(String name, String age, String email, String picture) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
}
