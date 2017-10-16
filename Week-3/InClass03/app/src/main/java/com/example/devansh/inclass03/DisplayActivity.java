package com.example.devansh.inclass03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Log.d("test", "WE FINALLY GOT HERE");
        if(getIntent() != null && getIntent().getExtras () != null){
            Profile user = (Profile) getIntent().getExtras().getSerializable(MainActivity.PROFILE_KEY);
            Log.d("test", "WE FINALLY GOT HERE");
            ((TextView) findViewById(R.id.DisplayName)).setText("Name : " + user.name);
            ((TextView) findViewById(R.id.DisplayDepartment)).setText("Department : " + user.department);
            ((TextView) findViewById(R.id.DisplayEmail)).setText("Email : " + user.email);
            ((TextView) findViewById(R.id.DisplayMood)).setText("I AM " + user.mood.toUpperCase());
            //((ImageView) findViewById(R.id.imageView)).setImageDrawable(user.imgProfile);
           // ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(user.imgMood);
            ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(getResources().getDrawable(user.imgMood));
            ((ImageView) findViewById(R.id.imageView)).setImageDrawable(getResources().getDrawable(user.imgProfile));
            /*

            ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_1));
             */
        }

    }


}
