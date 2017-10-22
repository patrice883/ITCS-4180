package com.example.devansh.personalhealthcarechatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnLogIn).setOnClickListener(this);
        findViewById(R.id.btnSignUp).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnLogIn){
            Log.d("test-click", "Log in button was clicked");
            Intent intent = new Intent(MainActivity.this, LogIn.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.btnSignUp){
            Log.d("test-click", "Sign Up button was clicked");
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        }else{
            Log.d("test-click", "Something random was clicked");
        }
    }
}
