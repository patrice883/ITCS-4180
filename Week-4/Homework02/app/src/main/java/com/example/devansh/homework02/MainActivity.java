package com.example.devansh.homework02;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int CONTACT_CODE = 100;  //This code is used when user makes new contact
    static final int DELETE_CODE = 999; //This code is used when user decides to delete a contact
    static final int EDIT_CODE = 888;   //This code is used when user decides to edit a contact

    static final String CONTACT_KEY = "NEW_CONTACT"; //This key is used to send and recieve a single contact
    static final String UPDATE = "UPDATE";           //This key is used to send and recieve updates on all the contacts

    private static ArrayList<Contact> people = new ArrayList<>(); //The list that holds the contacts list


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts");  //Just setting the title of the Screen

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.default_image);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        people.add(new Contact(byteArray, "Devansh", "Desai", "UNCC", "412-801-3104","ddesai14@uncc.edu","http://www.google.com","1005 somssasdasd asdas","Jan 14, 1997","Devu","http://www.facebook.com","http://www.twitter.com","http://www.skype.com","http://www.youtube.com"));
        people.add(new Contact( byteArray,"Aileen", "Benedict", "", "412-801-3105","","","","","","","","",""));
//        people.add(new Contact("Erfan", "Al Hossani", "", "+ 91 999-897-6012","","","","","","","","",""));
//        people.add(new Contact("ail", "be", "", "889","","","","","","","","",""));
//        people.add(new Contact("Dev", "De", "", "998","","","","","","","","",""));
//        people.add(new Contact("ail", "be", "", "889","","","","","","","","",""));
//        people.add(new Contact("Dev", "De", "", "998","","","","","","","","",""));
//        people.add(new Contact("ail", "be", "", "889","","","","","","","","",""));

        Log.d("test", "Dummy Contacts Created");
    }

    ///////////////////////////////////////////////////////////////////////////
    // Receiving Intent Data
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Code for New Contact Received -----------------------------------------------------------
        if(requestCode == CONTACT_CODE){
            if(resultCode == RESULT_OK){

                Log.d("test", "A New Contact is received");
                Contact person = (Contact) data.getExtras().get(CONTACT_KEY);
                people.add(person);

                Log.d("test", "A New Contact was received and added to list successfully");

            }
        }
        // Code for Updating Contact ---------------------------------------------------------------
        if(requestCode == EDIT_CODE || requestCode == DELETE_CODE){
            if(resultCode == RESULT_OK){
                Log.d("test", "A New Contact List is recieved and will be updated now");
                Log.d("test", data.getExtras().get(UPDATE).toString() + " THIS IS IT");
                people = (ArrayList<Contact>) data.getExtras().get(UPDATE);
                Log.d("test", "A New Contact List was recevied and updated successfully");
            }
        }


    }

    ///////////////////////////////////////////////////////////////////////////
    // On Click Methods
    ///////////////////////////////////////////////////////////////////////////

    //This method runs when user clicks on Create new contact
    public void onCreateClick(View view){

        Log.d("test", "Create new contact was clicked");
        Intent intent = new Intent(MainActivity.this, CreateNewContact.class);
        startActivityForResult(intent, CONTACT_CODE);
        Log.d("test", "Finished Creating new contact");
    }

    //This method runs when user clicks on Edit a contact
    public void onEditClick(View view){

        Log.d("test", "Edit contact was clicked");
        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);
        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", false);
            intent.putExtra("EDIT", true);
            intent.putExtra("DELETE", false);
            startActivityForResult(intent, EDIT_CODE);
        }else
            Toast.makeText(this, "There are no contacts to edit", Toast.LENGTH_SHORT).show();
        Log.d("test", "Finished editing contact");

    }

    //This method runs when user clicks on Delete a contact
    public void onDeleteClick(View view){

        Log.d("test", "Delete contact was clicked");
        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);

        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", false);
            intent.putExtra("EDIT", false);
            intent.putExtra("DELETE", true);
            startActivityForResult(intent, DELETE_CODE);
        }else
            Toast.makeText(this, "There are no contacts to Delete", Toast.LENGTH_SHORT).show();
        Log.d("test", "Finished deleting contact");

    }

    //This method runs when user clicks on Display a contact
    public void onDisplayClick(View view){

        Log.d("test", "Display contact was clicked");

        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);

        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", true);
            intent.putExtra("EDIT", false);
            intent.putExtra("DELETE", false);
            startActivity(intent);
        }else
            Toast.makeText(this, "There are no contacts to show", Toast.LENGTH_SHORT).show();
        Log.d("test", "Finished displaying contact");

    }

    //This method runs when user clicks on Finish and you just close the application LOL
    public void onFinishClick(View view){
        finish();
    }

}
