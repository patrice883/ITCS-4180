package com.example.uncc.midterm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by diondrelewis on 10/16/17.
 *
 * No longer need this ;-;
 * Put it in the MainActivity instead.
 */

public class AsyncSource extends AsyncTask<String, Void, ArrayList<Source>> {

    ArrayList<Source> sources;
    Context context;
    ProgressDialog loading;


    public AsyncSource(ArrayList<Source> s, Context c){
        sources = s;
        context = c;

    }


    @Override
    protected void onPreExecute() {
        loading = new ProgressDialog(context);
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


        }
        else{
            Log.d("Test-Async", "Something's wrong!");
        }
    }
}
