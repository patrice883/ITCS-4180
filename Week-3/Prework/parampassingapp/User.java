package itcs4180.parampassingapp;

import java.io.Serializable;

/**
 * Created by Aileen on 9/18/2017.
 */

// MUST IMPLEMENT SERIALIZABLE TO PASS INFO THRU INTENT
public class User implements Serializable{
    String name; // these variables are serializable...(?)
    double age;

    public User(String name, double age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
