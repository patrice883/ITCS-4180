package com.example.devansh.homework02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewContact extends AppCompatActivity {

    static final String CONTACT = "CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        if(getIntent() != null && getIntent().getExtras() != null){


            Contact thisperson = (Contact) getIntent().getExtras().get(CONTACT);
            setTitle(thisperson.getFname() + " " + thisperson.getLname());
            ((ImageView) findViewById(R.id.viewprofilephoto)).setImageDrawable(getResources().getDrawable(R.drawable.default_image));
            ((TextView) findViewById(R.id.viewfirstname)).setText(thisperson.getFname());
            ((TextView) findViewById(R.id.viewlastname)).setText(thisperson.getLname());
            ((TextView) findViewById(R.id.viewaddress)).setText("Address : " + thisperson.getAddress());
            ((TextView) findViewById(R.id.viewbirthday)).setText(thisperson.getBirthday());
            ((TextView) findViewById(R.id.viewcompany)).setText("Company : " + thisperson.getCompany());
            ((TextView) findViewById(R.id.viewemail)).setText("Email : " + thisperson.getEmail());
            ((TextView) findViewById(R.id.viewfburl)).setText("Facebook URL : " + thisperson.getFbURL());
            ((TextView) findViewById(R.id.viewnickname)).setText(thisperson.getNickname());
            ((TextView) findViewById(R.id.viewphone)).setText(thisperson.getPhone());
            ((TextView) findViewById(R.id.viewskype)).setText("Skype : " + thisperson.getSkypeURL());
            ((TextView) findViewById(R.id.viewtwitterurl)).setText("Twitter URL : " + thisperson.getTwitterURL());
            ((TextView) findViewById(R.id.viewurl)).setText("Personal URL : " + thisperson.getUrl());
            ((TextView) findViewById(R.id.viewyoutube)).setText("Youtube Channel : " + thisperson.getYoutubeChannel());

        }

    }
}
