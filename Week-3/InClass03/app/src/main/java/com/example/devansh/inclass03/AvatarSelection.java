package com.example.devansh.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AvatarSelection extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_selection);
    }

    @Override
    public void onClick(View v){
        Log.d("test", "An avatar was clicked!");

        Intent intent = new Intent();
        intent.putExtra(MainActivity.VALUE_KEY, v.getId());
        setResult(RESULT_OK, intent);

        finish();
    }

}
