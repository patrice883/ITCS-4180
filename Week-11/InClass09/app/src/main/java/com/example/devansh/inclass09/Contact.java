package com.example.devansh.inclass09;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by devansh on 10/30/17.
 */

@IgnoreExtraProperties
public class Contact implements Serializable{

    public String name, email, phone, dept, uid;
    public int image;
    public static final int GIRL1 = 80;
    public static final int GIRL2 = 192;
    public static final int GIRL3 = 210;
    public static final int BOY1 = 890;
    public static final int BOY2 = 463;
    public static final int BOY3 = 214;

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
                ", image=" + image +'\'' +
                '}';
    }


}
