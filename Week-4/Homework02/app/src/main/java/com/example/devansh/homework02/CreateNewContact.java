package com.example.devansh.homework02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewContact extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);
        setTitle("Create New Contact");



        findViewById(R.id.btnSaveContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname, lname, company = "", phone, email = "", url = "", address = "", birthday ="", nickname = "", fbURL ="", twitterURL = "", skypeURL ="", youtubeChannel="";
                boolean fnameCheck = true, lnameCheck = true, phoneCheck = true;
                if(((EditText)findViewById(R.id.firstName)).getText().toString() == null) {
                    fnameCheck = false;
                    Toast.makeText(CreateNewContact.this, "Please enter your first name", Toast.LENGTH_SHORT).show();
                }

                if(fnameCheck && ((EditText)findViewById(R.id.lastName)).getText().toString() == null) {
                    lnameCheck = false;
                    Toast.makeText(CreateNewContact.this, "Please enter your last name", Toast.LENGTH_SHORT).show();
                }

                if(fnameCheck && lnameCheck && ((EditText)findViewById(R.id.phone)).getText().toString() == null) {
                    phoneCheck = false;
                    Toast.makeText(CreateNewContact.this, "Please enter your Phone", Toast.LENGTH_SHORT).show();
                }

                if(lnameCheck && fnameCheck && phoneCheck){
                    fname =  ((EditText)findViewById(R.id.firstName)).getText().toString();
                    lname =  ((EditText)findViewById(R.id.lastName)).getText().toString();
                    phone =  ((EditText)findViewById(R.id.phone)).getText().toString();

                    if(((EditText)findViewById(R.id.companyName)).getText().toString() != null)
                        company =  ((EditText)findViewById(R.id.companyName)).getText().toString();
                    if(((EditText)findViewById(R.id.email)).getText().toString() != null)
                        email =  ((EditText)findViewById(R.id.email)).getText().toString();

                    if(((EditText)findViewById(R.id.url)).getText().toString() != null)
                        url =  ((EditText)findViewById(R.id.url)).getText().toString();

                    if(((EditText)findViewById(R.id.address)).getText().toString() != null)
                        address =  ((EditText)findViewById(R.id.address)).getText().toString();

                    if(((EditText)findViewById(R.id.birthday)).getText().toString() != null)
                        birthday =  ((EditText)findViewById(R.id.birthday)).getText().toString();

                    if(((EditText)findViewById(R.id.nickname)).getText().toString() != null)
                        nickname =  ((EditText)findViewById(R.id.nickname)).getText().toString();

                    if(((EditText)findViewById(R.id.facebookURL)).getText().toString() != null)
                        fbURL =  ((EditText)findViewById(R.id.facebookURL)).getText().toString();

                    if(((EditText)findViewById(R.id.twitterURL)).getText().toString() != null)
                        twitterURL =  ((EditText)findViewById(R.id.twitterURL)).getText().toString();

                    if(((EditText)findViewById(R.id.skype)).getText().toString() != null)
                        skypeURL =  ((EditText)findViewById(R.id.skype)).getText().toString();

                    if(((EditText)findViewById(R.id.youtubeChannel)).getText().toString() != null)
                        youtubeChannel =  ((EditText)findViewById(R.id.youtubeChannel)).getText().toString();

                    Contact newContact = new Contact( fname, lname, company, phone, email, url, address, birthday, nickname, fbURL, twitterURL, skypeURL, youtubeChannel);

                    Intent intent = new Intent();
                    intent.putExtra("NEW_CONTACT", newContact);
                    setResult(RESULT_OK, intent);

                    finish();

                }
            }
        });

    }
}
