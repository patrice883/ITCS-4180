package edu.uncc.cci.mobileapps;
import java.util.*;

/**
 * Assignment #: InClass1 | Question #1
 * File Name: MainPart1.java
 * @author Aileen Benedict & Devansh Desai
 * @version August 28th, 2017
 */

public class MainPart1 {
    /*
    * Question 1:
    * - In this question you will use the Data.users array that includes
    * a list of users. Formatted as : firstname,lastname,age,email,gender,city,state
    * - Create a User class that should parse all the parameters for each user.
    * - Insert each of the users in a list.
    * - Print out the TOP 10 oldest users.
    * */

    public static void main(String[] args) {

        // Create ArrayList to hold Users
        ArrayList<User> users = new ArrayList<User>();

        // Access the Data.users array and fill users ArrayList
        for (String str : Data.users) {
            users.add(new User(str));
        }

        // Sorts in descending order (because of our compareTo method in
        // the User class)
        Collections.sort(users);

        // Print the top 10 users (top 10 oldest)
        for(int i = 0; i < 10; i++){
            System.out.println(users.get(i).toString());
        }


    }
}