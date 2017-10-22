package com.example.uncc.midterm;

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

public class MainActivity extends AppCompatActivity {

    private String SOURCE_URL = "https://newsapi.org/v1/sources";
    static String SOURCE_KEY = "SOURCE";

    private static ArrayList<Source> sources;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.sourcesListView);

        sources = new ArrayList<Source>();
        connectToAPI(); // For Sources



    }

    ///////////////////////////////////////////////////////////////////////////
    // Connection Stuff
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

    private void connectToAPI() {

        if(isConnected()){
            Log.d("Test-Connecting", "Is connected to internet. Ready to connect yayayayayy");
            //new AsyncSource(sources, MainActivity.this).execute(SOURCE_URL);
            new AsyncSrc(sources).execute(SOURCE_URL);
        }

        else {
            Log.d("Test-Connecting", "Is not connected to internet.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // List View Thingy
    ///////////////////////////////////////////////////////////////////////////
    public void generateListView(){

        // Just watched a tutorial on ListView last night. This is so scary I hope I don't mess up!!!
        // Shows the name
        String[] names = new String[sources.size()];
        for(int i = 0; i < sources.size(); i++){
            names[i] = sources.get(i).getName();
        }

        ListAdapter sourceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(sourceAdapter);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String item = String.valueOf(parent.getItemAtPosition(position));

                        Log.d("Test-ListView", sources.get(position).getName() + " was clicked!");
                        Source clickedSource = (Source) sources.get(position);

                        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                        intent.putExtra(SOURCE_KEY, clickedSource);

                        startActivity(intent);

                    }
                }
        );

    }


    ///////////////////////////////////////////////////////////////////////////
    // AsyncTask: Source
    ///////////////////////////////////////////////////////////////////////////

    public class AsyncSrc extends AsyncTask<String, Void, ArrayList<Source>> {

        ArrayList<Source> sources;
        //Context context;
        ProgressDialog loading;


        public AsyncSrc(ArrayList<Source> s){
            sources = s;

        }


        @Override
        protected void onPreExecute() {
            loading = new ProgressDialog(MainActivity.this);
            loading.setCancelable(false);

            loading.setMessage("Loading Sources ...");
            loading.setProgress(0);
            loading.show();
        }

        @Override
        protected ArrayList<Source> doInBackground(String... strings) {


            HttpURLConnection connection = null;
            try {
                Log.d("Test-Async", "Attempting to connect to " + strings[0] + "...");
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.d("Test-Async", "We got in!");
                    String json = IOUtils.toString(connection.getInputStream(), "UTF8");
                    JSONObject root = new JSONObject(json);

                    JSONArray srcs = root.getJSONArray("sources");
                    for (int i=0;i<srcs.length();i++) {
                        //Log.d("Test-Async", "We got in loop");
                        JSONObject sourceJson = srcs.getJSONObject(i);
                        Source source = new Source();

                        source.id = sourceJson.getString("id");
                        source.name = sourceJson.getString("name");
                        source.description = sourceJson.getString("description");
                        source.url = sourceJson.getString("url");
                        source.category = sourceJson.getString("category");
                        source.language = sourceJson.getString("language");
                        source.country = sourceJson.getString("country");
                        //Log.d("Test-Async", "We got id through country");

                        Url urlsToLogos = new Url();
                        JSONObject urlJson = sourceJson.getJSONObject("urlsToLogos");
                        if(!urlJson.isNull("small"))
                            urlsToLogos.small = urlJson.getString("small");
                        if(!urlJson.isNull("medium"))
                            urlsToLogos.small = urlJson.getString("medium");
                        if(!urlJson.isNull("large"))
                            urlsToLogos.small = urlJson.getString("large");
                        source.urlsToLogos = urlsToLogos;
                        //Log.d("Test-Async", "We got the URL thingy");

                        ArrayList<String> s = new ArrayList<>();
                        JSONArray sortsJson = sourceJson.getJSONArray("sortBysAvailable");
                        for(int j = 0; j < sortsJson.length(); j++){
                            //Log.d("Test-Async", "" + j);
                            s.add(""+ sortsJson.get(j)); // ---- not sure if this is correct
                            //Log.d("Test-Async", "hi");
                        }
                        source.sortBysAvailable = s;
                        //Log.d("Test-Async", "We got sorts thingy");

                        sources.add(source);
                    }

                } // end if
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
        protected void onPostExecute(ArrayList<Source> sources) {
            if(sources != null && sources.size() != 0){
                Log.d("Test-Async", "We got our sources!");
                Log.d("Test-Async", "" + sources.toString());

                loading.setMessage("Photo Loaded!");
                loading.setProgress(100);
                loading.dismiss();
                loading.setProgress(0);

                generateListView();

            }
            else{
                Log.d("Test-Async", "Something's wrong!");
            }
        }
    }


}
