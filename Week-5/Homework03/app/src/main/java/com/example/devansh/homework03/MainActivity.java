package com.example.devansh.homework03;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    String url = "http://dev.theappsdr.com/apis/trivia_json/trivia_text.php";
    ArrayList<Question> questions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Trivia Time");
        (findViewById(R.id.btnStart)).setEnabled(false);


        if(isConnected()){
            Log.d("test", "Is connected to internet. Ready to connect yayayayayy");
            //new GetDataAsync(questions).execute(url);
            new GetQuestions(questions,this).execute(url);
            ((ImageView)findViewById(R.id.imageView)).setImageDrawable(getResources().getDrawable(R.drawable.trivia));

            findViewById(R.id.imageView).setVisibility(View.VISIBLE);
            findViewById(R.id.txtTrivia).setVisibility(View.VISIBLE);
            (findViewById(R.id.btnStart)).setEnabled(true);
        }
        else {
            Log.d("test", "Is not connected to internet. You poopoo.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // OnClick() methods for the 2 buttons
    ///////////////////////////////////////////////////////////////////////////
    public void onExitClick(View view){
        Log.d("Test", "Exit Button was Clicked");
        finish();
    }

    public void onStartTriviaClick(View view) {
        Log.d("Test", "Start Trivia Button was Clicked");
        Intent intent = new Intent(MainActivity.this, Trivia.class);
        intent.putExtra("TRIVIA", questions);
        startActivity(intent);
    }


    ///////////////////////////////////////////////////////////////////////////
    // isConnected() method to check if there is a live internet connection.
    ///////////////////////////////////////////////////////////////////////////
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

}
