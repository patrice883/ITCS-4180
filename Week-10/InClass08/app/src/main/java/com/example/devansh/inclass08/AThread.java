package com.example.devansh.inclass08;

import java.io.Serializable;

/**
 * Created by devansh on 11/6/17.
 */

public class AThread implements Serializable{

    String user_fname, user_lname, user_id, id, title, created_at;
    boolean user_created = false;

    @Override
    public String toString() {
        return "AThread{" +
                "user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_id='" + user_id + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }



    /*
    {
            "user_fname": "Prajval",
            "user_lname": "Bavi",
            "user_id": "41",
            "id": "51",
            "title": "user448@test.org posting a new thread 2431",
            "created_at": "2017-11-07 02:12:01"
        },

    */


}
