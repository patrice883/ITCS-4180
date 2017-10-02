package com.example.devansh.homework02;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewContact extends AppCompatActivity implements View.OnClickListener{

    static final String CONTACT = "CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        if(getIntent() != null && getIntent().getExtras() != null){


            Contact thisperson = (Contact) getIntent().getExtras().get(CONTACT);
            setTitle(thisperson.getFname() + " " + thisperson.getLname());

            //get bytearray from contact and then display it on imageview
            byte[] byteArray = thisperson.getPhoto();
            Log.d("test", "We are in View Contact and profile image is " + BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
            ((ImageView) findViewById(R.id.viewprofilephoto)).setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
            ((TextView) findViewById(R.id.viewfirstname)).setText(thisperson.getFname());
            ((TextView) findViewById(R.id.viewlastname)).setText(thisperson.getLname());
            ((TextView) findViewById(R.id.viewaddress)).setText(thisperson.getAddress());
            ((TextView) findViewById(R.id.viewbirthday)).setText(thisperson.getBirthday());
            ((TextView) findViewById(R.id.viewcompany)).setText(thisperson.getCompany());
            ((TextView) findViewById(R.id.viewemail)).setText(thisperson.getEmail());
            ((TextView) findViewById(R.id.viewfburl)).setText(thisperson.getFbURL());
            ((TextView) findViewById(R.id.viewnickname)).setText(thisperson.getNickname());
            ((TextView) findViewById(R.id.viewphone)).setText(thisperson.getPhone());
            ((TextView) findViewById(R.id.viewskype)).setText(thisperson.getSkypeURL());
            ((TextView) findViewById(R.id.viewtwitterurl)).setText(thisperson.getTwitterURL());
            ((TextView) findViewById(R.id.viewurl)).setText(thisperson.getUrl());
            ((TextView) findViewById(R.id.viewyoutube)).setText(thisperson.getYoutubeChannel());

        }

        if(!((TextView)findViewById(R.id.viewurl)).getText().toString().trim().isEmpty())
            findViewById(R.id.viewurl).setOnClickListener(this);
        if(!((TextView)findViewById(R.id.viewfburl)).getText().toString().trim().isEmpty())
            findViewById(R.id.viewfburl).setOnClickListener(this);
        if(!((TextView)findViewById(R.id.viewskype)).getText().toString().trim().isEmpty())
            findViewById(R.id.viewskype).setOnClickListener(this);
        if(!((TextView)findViewById(R.id.viewtwitterurl)).getText().toString().trim().isEmpty())
            findViewById(R.id.viewtwitterurl).setOnClickListener(this);
        if(!((TextView)findViewById(R.id.viewyoutube)).getText().toString().trim().isEmpty())
            findViewById(R.id.viewyoutube).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Log.d("Test", "Some link was clicked");
        Intent x = new Intent("android.intent.action.VIEW");
        Log.d("test", ("\"" +((TextView)findViewById( view.getId())).getText()+ "\""));
        x.setData(Uri.parse(((TextView)findViewById(view.getId())).getText().toString()));
        startActivity(x);

    }
}
