package itcs4180.hw3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Assignment: HW3
 * File Name: MainActivity.java
 * @author Group 2 - Devansh Desai, Aileen Benedict
 * @date 10/05/2017
 *
 * The Main activity is responsible for the loading of the trivia contents.
 */

public class MainActivity extends AppCompatActivity {

    String TRIVIA_URL = "http://dev.theappsdr.com/apis/trivia_json/trivia_text.php";

    private static ArrayList<Question> questionData;
    public static final String QUESTIONS = "QUESTION_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionData = new ArrayList<Question>(); // new empty ArrayList to hold question data
        loadTrivia();

    }

    ///////////////////////////////////////////////////////////////////////////
    // On Click Methods
    ///////////////////////////////////////////////////////////////////////////
    public void onExitClick (View view){
        Log.d("test", "Exit Button was clicked!");
        finish();
    }

    public void onStartClick(View view){
        Log.d("test", "Start Button was clicked!");

        // Start the Trivia! Also send questionData to new activity
        Intent intent = new Intent(this, Trivia.class);
        intent.putExtra(QUESTIONS, questionData);
        startActivity(intent);


    }

    ///////////////////////////////////////////////////////////////////////////
    // Loading Trivia Questions
    ///////////////////////////////////////////////////////////////////////////
    public void loadTrivia(){
        ImageView img = (ImageView)findViewById(R.id.imgTrivia);
        TextView txt = (TextView)findViewById(R.id.txtLoadingStatus);
        Button btn = (Button)findViewById(R.id.btnStart);

        // Start button should be disabled until Trivia is fully loaded
        //btn.setClickable(false);
        btn.setEnabled(false);
        // Image is also invisible until everything is loaded
        img.setVisibility(View.INVISIBLE);

        // Connect to Trivia API
        if(isConnected()){
            Log.d("test", "Is connected to internet. Ready to connect yayayayayy");
            //new GetDataAsync(keywordList).execute("http://dev.theappsdr.com/apis/photos/keywords.php");
            new GetTriviaAsync(img, txt, btn, questionData).execute(TRIVIA_URL);
        }
        else {
            Log.d("test", "Is not connected to internet. You poopoo.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Supporting Methods that Do Stuff :D~
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
