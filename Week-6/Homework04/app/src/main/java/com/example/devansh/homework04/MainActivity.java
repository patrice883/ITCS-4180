package com.example.devansh.homework04;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> keywordList = new ArrayList<String>();
    private ArrayList<String> urls = new ArrayList<>();
    private int currentPhoto = 0;

    private String keywordUrl = "http://dev.theappsdr.com/apis/photos/keywords.php";
    private String ImagesUrl = "http://dev.theappsdr.com/apis/photos/index.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ImageView)findViewById(R.id.imgDisplay)).setImageBitmap(null);
        setTitle("Main Activity");

        // ------------------------------------------------------------------------------------

        ((findViewById(R.id.btnPrevious))).setVisibility(View.INVISIBLE);
        ((findViewById(R.id.btnNext))).setVisibility(View.INVISIBLE);

        // ------------ Get Keywords API ----------------------------- :^) ------------- :) ---- :)
        if(isConnected()){
            Log.d("test_onCreate", "Is connected to internet. Ready to connect yayayayayy");
            new GetDataAsync(keywordList).execute(keywordUrl);
        }
        else {
            Log.d("test_onCreate", "Is not connected to internet. You poopoo.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }




    }


    ///////////////////////////////////////////////////////////////////////////
    // All the OnClick Methods :D:D:D:D:D:D:D:D
    ///////////////////////////////////////////////////////////////////////////

    public void onGoClick(View view){
        Log.d("test_onGoClick", "Button was clicked");

        final String[] keywordArray = new String[keywordList.size()];

        for(int i = 0; i < keywordArray.length; i++) {
            keywordArray[i] = keywordList.get(i);
            Log.d("test_onGoClick", "THE keywords ARE " + keywordArray[i]);
        }


        // Our AlertDialog :D
        AlertDialog.Builder keywordPicker = new AlertDialog.Builder(MainActivity.this);
        keywordPicker.setTitle("Choose a Keyword")
                .setSingleChoiceItems(keywordArray,-1, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("test_onGoClickAlert", keywordArray[i] + " was pressed!");

                        ((TextView)findViewById(R.id.txtSearch)).setText(keywordArray[i]);

                        // Reset URL (for photos) array list
                        urls = new ArrayList<>();
                        currentPhoto = 0;
                        // Dismiss dialogInterface
                        dialogInterface.dismiss();

                        // After keyword is selected, use another AsyncTask to connect to the Get URLs API
                        // Retrieve image urls related to the selected keyword
                        if(isConnected()){
                            Log.d("test_onGoClickAlert", "Is connected to internet. Ready to connect to Get URLs :D");
                            new GetLinksAsync(urls,((TextView)findViewById(R.id.txtSearch)).getText() + "").execute(ImagesUrl);
                        }
                        else {
                            Log.d("test_onGoClickAlert", "Is not connected to internet. You poopoo.");
                            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
                        }

                        //Log.d("We got URLS")
                    }
                });

        final AlertDialog alert = keywordPicker.create();
        alert.show();


    }

    public void onPrevClick(View view){

        // If currentPhoto is already 0, go to the last photo instead
        if(currentPhoto == 0)
            currentPhoto = urls.size()-1;
        else
            currentPhoto--;

        if(isConnected()){
            Log.d("test_onPrevClick", "Is connected to internet. Ready to connect to Get URLs :D");
            new LoadingImages(((ImageView)findViewById(R.id.imgDisplay)),MainActivity.this).execute(urls.get(currentPhoto));
        }
        else {
            Log.d("test_onPrevClick", "Is not connected to internet. You poopoo.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }

    }

    public void onNextClick(View view){



        if(currentPhoto == urls.size()-1)
            currentPhoto = 0;
        else
            currentPhoto++;


        if(isConnected()){
            Log.d("test_onNextClick", "Is connected to internet. Ready to connect to Get URLs :D");
            new LoadingImages(((ImageView)findViewById(R.id.imgDisplay)),MainActivity.this).execute(urls.get(currentPhoto));
        }
        else {
            Log.d("test_onNextClick", "Is not connected to internet. You poopoo.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // AsyncTask to get keywords (keywords api) when the Activity starts
    ///////////////////////////////////////////////////////////////////////////

    private class GetDataAsync extends AsyncTask<String, Void, ArrayList<String> > {
        public GetDataAsync(ArrayList<String> keywords) {
            this.keywords = keywords;
        }
        private ArrayList<String> keywords;

        ProgressDialog loadDictionary;
        @Override
        protected void onPreExecute() {
            loadDictionary = new ProgressDialog((Context)MainActivity.this);
            loadDictionary.setCancelable(false);

            loadDictionary.setMessage("Loading Dictionary ...");
            loadDictionary.setProgress(0);
            loadDictionary.show();
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            StringTokenizer st = null;

            try {
                URL url = new URL(strings[0] + "?type=json");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line = reader.readLine();
                JSONObject root = new JSONObject(line);
                JSONArray keys = root.getJSONArray("categories");

                for(int i = 0; i < keys.length(); i++){
                    keywords.add((String) keys.get(i));
                }

                Log.d("test_keys", "keywords are : " + keys);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return keywords;
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            if(strings != null){
                Log.d("test", strings.toString());
                loadDictionary.setMessage("Dictionary Loaded!");
                loadDictionary.setProgress(100);
                loadDictionary.dismiss();
                loadDictionary.setProgress(0);

            }else {
                Log.d("test_getDataPost", "s is null");
            }





        }


    }

    ///////////////////////////////////////////////////////////////////////////
    // AsyncTask to get the image URLs based on the user's chosen keyword
    ///////////////////////////////////////////////////////////////////////////

    private class GetLinksAsync extends AsyncTask<String, Void, ArrayList<String> >{

        private ArrayList<String> urls;
        private String keyword;




        public GetLinksAsync(ArrayList<String> keywords, String key) {
            this.keyword = key;
            this.urls = keywords;

            Log.d("test_getLinksPre", "\nKeyword is " + key);
            Log.d("test_getLinksPre", "Array of URLs is " + keywords.toString());
        }



        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                String x = strings[0] + "?type=json&" + "keyword=" + keyword;

                URL url = new URL(x);
                Log.d("test",x);


                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));


                String line = reader.readLine();
                JSONObject root = new JSONObject(line);
                JSONArray keys = root.getJSONArray("urls");

                for(int i = 0; i < keys.length(); i++){
                    urls.add((String) keys.get(i));
                }

                Log.d("test_keys", "keywords are : " + keys);


//
//                String line = "";
//                while((line = reader.readLine()) != null){
//                    urls.add(line);
//                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return urls;
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            if(strings != null && !strings.isEmpty()){
                Log.d("test_getLinksPost", strings.toString());

                Log.d("test_getLinksPost", "The URLS are " + strings.toString());
                if(isConnected()){
                    Log.d("test_getLinksPost", "Is connected to internet. Ready to connect to Get URLs :D");
                    new LoadingImages(((ImageView)findViewById(R.id.imgDisplay)),MainActivity.this).execute(strings.get(0));
                }
                else {
                    Log.d("test_getLinksPost", "Is not connected to internet. You poopoo.");
                    Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
                }


                if(strings.size() > 1) {
                    ((findViewById(R.id.btnPrevious))).setVisibility(View.VISIBLE);
                    ((findViewById(R.id.btnNext))).setVisibility(View.VISIBLE);
                }else{
                    ((findViewById(R.id.btnPrevious))).setVisibility(View.INVISIBLE);
                    ((findViewById(R.id.btnNext))).setVisibility(View.INVISIBLE);
                }
                // Set buttons to visible :D Now that we have images

            }else {
                Toast.makeText(MainActivity.this, "No Images Found", Toast.LENGTH_SHORT).show();

                ((ImageView)findViewById(R.id.imgDisplay)).setImageBitmap(null);
                ((findViewById(R.id.btnPrevious))).setVisibility(View.INVISIBLE);
                ((findViewById(R.id.btnNext))).setVisibility(View.INVISIBLE);

                Log.d("test_getLinksPost", "URL list is null");
            }
            // Image Stuff
            // Call LoadingImages AsyncTask to load and set the first image




        }


    }

    ///////////////////////////////////////////////////////////////////////////
    // Aileeen .. OMG
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Other Methods
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
