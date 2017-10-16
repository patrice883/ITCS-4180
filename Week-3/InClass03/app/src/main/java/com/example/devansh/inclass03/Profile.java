package com.example.devansh.inclass03;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by devansh on 9/18/17.
 */

public class Profile implements Serializable{
    String name;
    String email;
    String department;
    String mood;


    int imgProfile;
    int imgMood;

    public Profile(String name, String email, String department, String mood, int imgProfile, int imgMood) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
        this.imgProfile = imgProfile;
        this.imgMood = imgMood;
    }



}
