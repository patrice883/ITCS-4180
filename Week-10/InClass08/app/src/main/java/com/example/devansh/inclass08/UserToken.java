package com.example.devansh.inclass08;

import java.io.Serializable;

/**
 * Created by devansh on 11/6/17.
 */

public class UserToken implements Serializable{

    String token;
    int user_id;
    String user_email, user_fname, user_lname, user_role;

    @Override
    public String toString() {
        return "UserToken{" +
                "token='" + token + '\'' +
                ", user_id=" + user_id +
                ", user_email='" + user_email + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_role='" + user_role + '\'' +
                '}';
    }
}
