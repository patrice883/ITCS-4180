package com.example.devansh.inclass07;

import android.graphics.drawable.Drawable;

/**
 * Created by devansh on 10/30/17.
 */

public class Contact  {
    String name, email, phone, dept;
    int image;

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
