package edu.uncc.cci.mobileapps;

/**
 * Assignment #: InClass1
 * File Name: User.java
 * @author Aileen Benedict & Devansh Desai
 * @version August 28th, 2017
 */

public class User implements Comparable<User> {

    private String firstName, lastName, email, gender, city, state;
    private int age;

    /**
     * Constructor taking in all of the fields as parameters.
     * @param firstName
     * @param lastName
     * @param email
     * @param gender
     * @param city
     * @param state
     * @param age
     */
    public User(String firstName, String lastName, String email, String gender, String city, String state, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.state = state;
        this.age = age;
    }

    /**
     * Constructor that takes in a String of data in the order of:
     * firstName, lastName, age, email, gender, city, and state.
     * Parses this String and updates the given fields.
     * @param data - the String of data to be taken in and split
     */
    public User(String data){
        String[] resultingTokens = data.split(",");
        this.firstName = resultingTokens[0];
        this.lastName = resultingTokens[1];
        this.age = Integer.parseInt(resultingTokens[2]);
        this.email = resultingTokens[3];
        this.gender = resultingTokens[4];
        this.city = resultingTokens[5];
        this.state = resultingTokens[6];
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    /**
     * Compares based on age only. Multiply by -1 to get the reverse
     * order, since we want to find the oldest users.
     * @param o another User object
     */
    public int compareTo(User o) {
        return -1 * (this.getAge() - o.getAge());
    }

    // -----------------------------------------------------------------------------------
    // All the Getter and Setter methods

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
