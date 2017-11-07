package com.example.devansh.inclass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsFragment.OnFragmentInteractionListener, SelectAvatarFragment.OnFragmentInteractionListener, CreateNewContactFragment.OnFragmentInteractionListener{

    // Contact List
    private ArrayList<Contact> contacts = new ArrayList<>();

    private int iconID = R.drawable.select_avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts");

        contacts.add(new Contact("Dr. Shehab", "mshehab@uncc.edu","Dr. Shehab Phone #","CS",R.drawable.avatar_m_1));
        contacts.add(new Contact("Aileen Benedict", "abenedi3@uncc.edu","XXX-XXX-XXXX","CS",R.drawable.avatar_f_3));
        contacts.add(new Contact("Devansh Desai", "ddesai14@uncc.edu","XXX-XXX-XXXX","CS",R.drawable.avatar_m_2));
        contacts.add(new Contact("Sunand Pujari", "spujari@uncc.edu","TA Phone 1","CS",R.drawable.avatar_m_3));
        contacts.add(new Contact("Sai Mounika Pabbathireddy", "spabbath@uncc.edu","TA PHONE 2","CS",R.drawable.avatar_f_1));
        contacts.add(new Contact("Dummy Contact", "dummy@dummy.com","dummyphone1","SIS",R.drawable.avatar_f_2));

        Log.d("test", "onCreate: person = " + contacts.get(0).toString() + "\n" + contacts.get(0).image);



        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentView, new ContactsFragment(), "contacts_fragment")
                .commit();



    }

    @Override
    public ArrayList<Contact> getList() {
        return contacts;
    }

    @Override
    public void setIconId(int iconId){
        this.iconID = iconId;
    }

    @Override
    public int getIconId(){
        return iconID;
    }
    @Override
    public void resetID(){
        this.iconID = R.drawable.select_avatar;
    }

}
