package itcs4180.parampassingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aileen on 9/18/2017.
 */

public class Person implements Parcelable{
    String name;
    double age;
    String city;

    public Person(String name, double age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    protected Person(Parcel in) {
        // This is a person constructor that GETS A PARCEL..
        // From that parcel, you reconstruct the person
        this.name = in.readString();
        this.age = in.readDouble();
        this.city = in.readString();
        // NOTICE that this is done in the SAME order as with the writeToParcel

    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // Called when you are trying to parcelize an object..
        // or move an object to a parcel

        //dest.writeString(this.name);
        // Oohh.. the Parcel was named "dest" in this, but "parcel" in this one
        parcel.writeString(this.name);
        parcel.writeDouble(this.age);
        parcel.writeString(this.city);
    }
}
