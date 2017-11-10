package com.example.devansh.inclass08;

import java.io.Serializable;

/**
 * Created by devansh on 11/9/17.
 */

public class AMessage implements Serializable{

    String user_fname, user_lname, user_id, id, message, created_at;
    boolean user_created = false;

    @Override
    public String toString() {
        return "AMessage{" +
                "user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_id='" + user_id + '\'' +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
