package com.example.uncc.midterm;

import java.io.Serializable;

/**
 * Created by diondrelewis on 10/16/17.
 */

class Url implements Serializable{
    String small = "", medium = "", large = "";

    public Url(){

    }

    @Override
    public String toString() {
        return "Url{" +
                "small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                '}';
    }
}
