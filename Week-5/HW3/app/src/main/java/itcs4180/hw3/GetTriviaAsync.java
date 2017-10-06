package itcs4180.hw3;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aileen on 10/5/2017.
 */

public class GetTriviaAsync extends AsyncTask<String, Void, ArrayList<String>>{

    private ArrayList<String> questions; // Hold each String line of data before parsing
    private ArrayList<Question> result; // ArrayList of final Question objects

    ImageView imgTrivia;
    TextView txtStatus;
    Button btnStart;

    public GetTriviaAsync(ImageView img, TextView txt, Button btn, ArrayList<Question> r){
        imgTrivia = img; // to make image visible from MainActivity after data is fully loaded
        txtStatus = txt; // to change shown text status from MainActivity after data is loaded
        btnStart = btn; // to enable the button again :') forgot to do that the first time. Oops!

        result = r; // same address as the one passed in, to share Question data with calling class
        questions = new ArrayList<String>(); // new empty ArrayList<String> to hold each line of data
    }

    // Gets each line of data (Strings before parsed) from URL and passes to onPostExecute
    @Override
    protected ArrayList<String> doInBackground(String... strings) {

        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(strings[0]);

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.d("test", "GetTrivaAsync: Connection has been made. Saving question data...");

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    questions.add(line);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close open connections and reader
            if (connection != null) {
                connection.disconnect();
                Log.d("test", "GetTrivaAsync: Connection has been closed.");
            }
            if (reader != null) {
                try {
                    reader.close();
                    Log.d("test", "GetTrivaAsync: BufferedReader has been closed.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return questions;
    }

    // Gets the data from doInbackground and passes each line into a new Question object,
    // which then parses it in its constructor
    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        // Making sure info was saved correctly ------------------------------
        /*
        Log.d("test", "\nExample Question:");
        String[] result = (strings.get(0)).split(";");
        for(int i = 0; i < result.length; i++){
            Log.d("test", result[i]);
        }
        */

        // Since result shares the address as the ArrayList<Question> passed into
        // this AsyncTask, this information should then be shared with the calling class
        Log.d("test", "We got this far..");
        for (String q: strings) {
            //Log.d("test", "Adding another question!");
            Question potatoesAreYummy = new Question(q);
            result.add(potatoesAreYummy);
        }

        Log.d("test", "Everything has been loaded. Ready to play!");
        // Setting Stuff
        txtStatus.setText(R.string.ready);
        imgTrivia.setVisibility(View.VISIBLE);
        btnStart.setEnabled(true);

    }

} // end AsyncTask
