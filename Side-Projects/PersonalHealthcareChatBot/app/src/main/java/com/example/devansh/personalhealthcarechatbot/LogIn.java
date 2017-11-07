package com.example.devansh.personalhealthcarechatbot;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LogIn extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.btnShowChat).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        String usrname = "" + ((TextView)(findViewById(R.id.editLogInUsername))).getText();
        String pass = "" +((TextView)(findViewById(R.id.editLogInPassword))).getText();

        if(sharedPref.contains(usrname)) {
            Log.d("test-login", "username is already added");

            String dictPass = sharedPref.getString(usrname, pass);
            Log.d("test-login", "we click on log in and we got : " + dictPass);
        }else{
            Log.d("test-login", "Username does not exist");
        }


    }
}
