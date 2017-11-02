package com.example.devansh.personalhealthcarechatbot;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.btnAddUser).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String usrname = "" + ((TextView)(findViewById(R.id.editSignUpUsername))).getText();
        String pass = "" +((TextView)(findViewById(R.id.editSignUpPassword))).getText();

        if(!sharedPref.contains(usrname)) {
            Toast.makeText(this,"Signup Successful", Toast.LENGTH_SHORT).show();
            editor.putString(usrname, pass);
            editor.commit();
            Log.d("test-signup", "We commited the new username and password");
        }else {
            Toast.makeText(this,"Username already Exists. Pls try new Username", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick2(View view) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        String usrname = "" + ((TextView)(findViewById(R.id.editSignUpUsername))).getText();
        String pass = "" +((TextView)(findViewById(R.id.editSignUpPassword))).getText();
        if(sharedPref.contains(usrname)) {
            Log.d("test-login", "username is already added");

            String dictPass = sharedPref.getString(usrname, pass);
            Log.d("test-login", "the password is : " + dictPass);
            Log.d("test-login", "the user entered : " + pass);

            if(pass.equals(dictPass)){
                Toast.makeText(this,"Log in successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Username or Password Incorrect", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Username does not Exist", Toast.LENGTH_SHORT).show();
            Log.d("test-login", "Username does not exist");
        }
    }
}
