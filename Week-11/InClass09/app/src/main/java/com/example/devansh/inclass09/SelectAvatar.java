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
        intent.putExtra("image", view.getId());
        setResult(RESULT_OK, intent);

        finish();
    }
}
