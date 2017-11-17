package com.example.devansh.inclass09;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectAvatar extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        Log.d("testt", "onCreateView: WE ARE HEREEEE");
        setTitle("Select Avatar");

        findViewById(R.id.imgBoy1).setOnClickListener(this);
        findViewById(R.id.imgBoy2).setOnClickListener(this);
        findViewById(R.id.imgBoy3).setOnClickListener(this);
        findViewById(R.id.imgGirl1).setOnClickListener(this);
        findViewById(R.id.imgGirl2).setOnClickListener(this);
        findViewById(R.id.imgGirl3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Log.d("test", "An avatar was clicked!");

        Log.d("test", "onClick: " + view.getId());

        Intent intent = new Intent();

        switch(view.getId()){
            case R.id.imgGirl1:
                intent.putExtra("image", Contact.GIRL1);
                break;
            case R.id.imgGirl2:
                intent.putExtra("image", Contact.GIRL2);
                break;
            case R.id.imgGirl3:
                intent.putExtra("image", Contact.GIRL3);
                break;
            case R.id.imgBoy1:
                intent.putExtra("image", Contact.BOY1);
                break;
            case R.id.imgBoy2:
                intent.putExtra("image", Contact.BOY2);
                break;
            case R.id.imgBoy3:
                intent.putExtra("image", Contact.BOY3);
                break;
            default:
                intent.putExtra("image", 0);
                break;
        }

        //intent.putExtra("image", view.getId());
        setResult(RESULT_OK, intent);

        finish();
    }
}
