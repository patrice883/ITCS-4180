package com.example.devansh.homework02;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewContact extends AppCompatActivity {

    private static final int REQUEST_IMG_CAPTURE = 337;

    byte[] byteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);
        setTitle("Create New Contact");


        //Setting the default bitmap unless the user decides to change anything
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();

        findViewById(R.id.btnSaveContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Variables

                String fname, lname, company = "", phone, email = "", url = "", address = "", birthday = "", nickname = "", fbURL = "", twitterURL = "", skypeURL = "", youtubeChannel = "";
                // Variables for mandatory values (user must input these)
                boolean fnameCheck = true, lnameCheck = true, phoneCheck = true;

                // Toast if user doesn't enter first name
                if (((EditText) findViewById(R.id.firstName)).getText().toString().isEmpty()) {
                    Log.d("test", "First Name is empty");

                    fnameCheck = false;
                    Toast.makeText(CreateNewContact.this, "Please enter your first name", Toast.LENGTH_SHORT).show();
                }

                // Toast if user doesn't enter last name
                if (/*fnameCheck &&*/ ((EditText) findViewById(R.id.lastName)).getText().toString().isEmpty()) {
                    Log.d("test", "Last Name is empty");

                    lnameCheck = false;
                    Toast.makeText(CreateNewContact.this, "Please enter your last name", Toast.LENGTH_SHORT).show();
                }

                // Toast if user doesn't enter phone number
                if (/*fnameCheck && lnameCheck &&*/ ((EditText) findViewById(R.id.phone)).getText().toString().isEmpty()) {
                    Log.d("test", "Phone Number is empty");

                    phoneCheck = false;
                    Toast.makeText(CreateNewContact.this, "Please enter your Phone", Toast.LENGTH_SHORT).show();
                }

                // Can only continue if these mandatory fields have been filled
                if (lnameCheck && fnameCheck && phoneCheck) {
                    fname = ((EditText) findViewById(R.id.firstName)).getText().toString();
                    lname = ((EditText) findViewById(R.id.lastName)).getText().toString();
                    phone = ((EditText) findViewById(R.id.phone)).getText().toString();
                    Log.d("test", "Mandatory values:\n\tFirst Name: " + fname + "\n\tLast Name: " +
                            lname + "\n\tPhone Number: " + phone);


                    if (((EditText) findViewById(R.id.companyName)).getText().toString() != null)
                        company = ((EditText) findViewById(R.id.companyName)).getText().toString();

                    if (((EditText) findViewById(R.id.email)).getText().toString() != null)
                        email = ((EditText) findViewById(R.id.email)).getText().toString();

                    if (((EditText) findViewById(R.id.url)).getText().toString() != null)
                        url = ((EditText) findViewById(R.id.url)).getText().toString();

                    if (((EditText) findViewById(R.id.address)).getText().toString() != null)
                        address = ((EditText) findViewById(R.id.address)).getText().toString();

                    if (((EditText) findViewById(R.id.birthday)).getText().toString() != null)
                        birthday = ((EditText) findViewById(R.id.birthday)).getText().toString();

                    if (((EditText) findViewById(R.id.nickname)).getText().toString() != null)
                        nickname = ((EditText) findViewById(R.id.nickname)).getText().toString();

                    if (((EditText) findViewById(R.id.facebookURL)).getText().toString() != null)
                        fbURL = ((EditText) findViewById(R.id.facebookURL)).getText().toString();

                    if (((EditText) findViewById(R.id.twitterURL)).getText().toString() != null)
                        twitterURL = ((EditText) findViewById(R.id.twitterURL)).getText().toString();

                    if (((EditText) findViewById(R.id.skype)).getText().toString() != null)
                        skypeURL = ((EditText) findViewById(R.id.skype)).getText().toString();

                    if (((EditText) findViewById(R.id.youtubeChannel)).getText().toString() != null)
                        youtubeChannel = ((EditText) findViewById(R.id.youtubeChannel)).getText().toString();


                    Contact newContact = new Contact(byteArray, fname, lname, company, phone, email, url, address, birthday, nickname, fbURL, twitterURL, skypeURL, youtubeChannel);
                    Log.d("test","New Contact is " + newContact);


                    Intent intent = new Intent();
                    intent.putExtra("NEW_CONTACT", newContact);
                    setResult(RESULT_OK, intent);

                    finish();

                }
            }
        });

    } // end onCreate()

    ///////////////////////////////////////////////////////////////////////////
    // Open Camera and Get Photo
    // Source: https://developer.android.com/training/camera/photobasics.html
    ///////////////////////////////////////////////////////////////////////////

    public void onCameraClick(View view) {

        Log.d("test", "Camera image was clicked!!");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_IMG_CAPTURE);
        }
        Log.d("test", "Finished With Camera Image");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_IMG_CAPTURE) {

            Log.d("test", "Recieved a Camera Image");
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            // Sets image
            ImageView img = (ImageView) findViewById(R.id.profilepic);
            img.setImageBitmap(imageBitmap);

            //This will save the Bitmap image to bytearray
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            Log.d("test", "Displayed the Camera Image");
        }

    } // end onActivityResult()

} // end Class
