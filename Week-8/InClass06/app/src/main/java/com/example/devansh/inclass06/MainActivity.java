package com.example.devansh.inclass06;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private String SOURCE_URL = "https://newsapi.org/v1/sources";
    static String SOURCE_KEY = "SOURCE";
    ListView listView;

    ArrayList<Source> sources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("News Channels");
        listView = (ListView) findViewById(R.id.sourcesView);
        if(isConnected()){
            Log.d("Test-Connecting", "Is connected to internet. Ready to connect");
            new GetSourcesAsync(sources).execute(SOURCE_URL);
        }

        else {
            Log.d("Test-Connecting", "Is not connected to internet.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    private class GetSourcesAsync extends AsyncTask<String, Void, ArrayList<Source> > {

        private ArrayList<Source> sources;

        public GetSourcesAsync(ArrayList<Source> keywords) {
            this.sources = keywords;
        }


        ProgressDialog loadDictionary;
        @Override
        protected void onPreExecute() {
            loadDictionary = new ProgressDialog(MainActivity.this);
            loadDictionary.setCancelable(false);
            loadDictionary.setMessage("Loading Sources ...");
            loadDictionary.setProgress(0);
            loadDictionary.show();
        }

        @Override
        protected ArrayList<Source> doInBackground(String... strings) {
            StringTokenizer st = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));




                String json = IOUtils.toString(connection.getInputStream(), "UTF8");
                JSONObject root = new JSONObject(json);
                JSONArray keys = root.getJSONArray("sources");


                for(int i = 0; i < keys.length();i++){

                    Source currentSource = new Source();
                    JSONObject currentObj = keys.getJSONObject(i);
                    currentSource.id = currentObj.getString("id");
                    currentSource.name = currentObj.getString("name");
                    sources.add(currentSource);
                }

                Log.d("test_keys", "keywords are : " + keys);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return sources;
        }


        @Override
        protected void onPostExecute(ArrayList<Source> strings) {
            if(strings != null){
                Log.d("test", strings.toString());
                loadDictionary.setMessage("Sources Loaded!");
                loadDictionary.setProgress(100);
                loadDictionary.dismiss();
                loadDictionary.setProgress(0);

                generateListView();

                //GENERATE SCREEN HERE


            }else {
                Log.d("test_getSourcePost", "source is null");
            }


        }

    }

    public void generateListView(){

        // Just watched a tutorial on ListView last night. This is so scary I hope I don't mess up!!!
        // Shows the name
        String[] names = new String[sources.size()];
        for(int i = 0; i < sources.size(); i++){
            names[i] = sources.get(i).name;
            Log.d("test", "Converting to names and it is : " + names[i].toString());
        }


        ListAdapter sourceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(sourceAdapter);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String item = String.valueOf(parent.getItemAtPosition(position));

                        Log.d("Test-ListView", sources.get(position).name + " was clicked!");
                        Source clickedSource = (Source) sources.get(position);

                        Intent intent = new Intent(MainActivity.this, ShowArticles.class);
                        intent.putExtra(SOURCE_KEY, clickedSource);

                        startActivity(intent);

                    }
                }
        );

    }


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
