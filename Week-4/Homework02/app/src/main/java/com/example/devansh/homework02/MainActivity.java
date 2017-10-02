package com.example.devansh.homework02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int CONTACT_CODE = 100;  //This code is used when user makes new contact
    static final int DELETE_CODE = 999; //This code is used when user decides to delete a contact
    static final int EDIT_CODE = 888;   //This code is used when user decides to edit a contact

    static final String CONTACT_KEY = "NEW_CONTACT"; //This key is used to send and recieve a single contact
    static final String UPDATE = "UPDATE";           //This key is used to send and recieve updates on all the contacts

    private ArrayList<Contact> people = new ArrayList<>(); //The list that holds the contacts list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts");  //Just setting the title of the Screen

        //Just Adding Random Contacts
        people.add(new Contact("Devansh", "Desai", "", "412-801-3104","","","","","","","","",""));
        people.add(new Contact("Aileen", "Benedict", "", "412-801-3105","","","","","","","","",""));
//        people.add(new Contact("Erfan", "Al Hossani", "", "+ 91 999-897-6012","","","","","","","","",""));
//        people.add(new Contact("ail", "be", "", "889","","","","","","","","",""));
//        people.add(new Contact("Dev", "De", "", "998","","","","","","","","",""));
//        people.add(new Contact("ail", "be", "", "889","","","","","","","","",""));
//        people.add(new Contact("Dev", "De", "", "998","","","","","","","","",""));
//        people.add(new Contact("ail", "be", "", "889","","","","","","","","",""));

    }

    ///////////////////////////////////////////////////////////////////////////
    // Receiving Intent Data
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Code for New Contact Received -----------------------------------------------------------
        if(requestCode == CONTACT_CODE){
            if(resultCode == RESULT_OK){
                Log.d("Create", "A New Contact is recieved");
                Log.d("Create", data.getExtras().get(CONTACT_KEY).toString() + " THIS IS IT");
                people.add((Contact) data.getExtras().get(CONTACT_KEY));
                Log.d("Create", "A New Contact was received and added to list successfully");

            }
        }
        // Code for Updating Contact ---------------------------------------------------------------
        if(requestCode == EDIT_CODE || requestCode == DELETE_CODE){
            if(resultCode == RESULT_OK){
                Log.d("Edit", "A New Contact List is recieved and will be updated now");
                Log.d("Edit", data.getExtras().get(UPDATE).toString() + " THIS IS IT");
                people = (ArrayList<Contact>) data.getExtras().get(UPDATE);
                Log.d("Edit", "A New Contact List was recevied and updated successfully");
            }
        }


    }

    ///////////////////////////////////////////////////////////////////////////
    // On Click Methods
    ///////////////////////////////////////////////////////////////////////////

    //This method runs when user clicks on Create new contact
    public void onCreateClick(View view){
        Intent intent = new Intent(MainActivity.this, CreateNewContact.class);
        startActivityForResult(intent, CONTACT_CODE);
    }

    //This method runs when user clicks on Edit a contact
    public void onEditClick(View view){

        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);
        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", false);
            intent.putExtra("EDIT", true);
            intent.putExtra("DELETE", false);
            startActivityForResult(intent, EDIT_CODE);
        }else
            Toast.makeText(this, "There are no contacts to edit", Toast.LENGTH_SHORT).show();
    }

    //This method runs when user clicks on Delete a contact
    public void onDeleteClick(View view){
        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);

        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", false);
            intent.putExtra("EDIT", false);
            intent.putExtra("DELETE", true);
            startActivityForResult(intent, DELETE_CODE);
        }else
            Toast.makeText(this, "There are no contacts to Delete", Toast.LENGTH_SHORT).show();
    }

    //This method runs when user clicks on Display a contact
    public void onDisplayClick(View view){
        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);

        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", true);
            intent.putExtra("EDIT", false);
            intent.putExtra("DELETE", false);
            startActivity(intent);
        }else
            Toast.makeText(this, "There are no contacts to show", Toast.LENGTH_SHORT).show();
    }

    //This method runs when user clicks on Finish and you just close the application LOL
    public void onFinishClick(View view){
        finish();
    }

}
