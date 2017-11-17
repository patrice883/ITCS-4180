package com.example.devansh.inclass09;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by devansh on 10/30/17.
 */

@IgnoreExtraProperties
public class Contact implements Serializable{

    public String name, email, phone, dept;
    public int image;

    public Contact(){

    }

    public Contact(String name, String email, String phone, String dept, int image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }


}
