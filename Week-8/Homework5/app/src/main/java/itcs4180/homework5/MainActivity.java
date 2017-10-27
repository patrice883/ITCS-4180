package itcs4180.homework5;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String ITUNES_URL = "https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectToAPI();
    }


    private void generateListView(ArrayList<Podcast> podcasts){
        Log.d("Test-GenerateListView", "We got here!");


    }

    ///////////////////////////////////////////////////////////////////////////
    // Connect to API
    ///////////////////////////////////////////////////////////////////////////

    private void connectToAPI() {
        if (isConnected()) {
            Log.d("Test-ConnectToAPI", "Is connected to internet. Ready to connect yayayayayy");
            //new AsyncSource(sources, MainActivity.this).execute(SOURCE_URL);
            new AsyncItunes().execute(ITUNES_URL);
        } else {
            Log.d("Test-ConnectToAPI", "Is not connected to internet.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }
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

    public class AsyncItunes extends AsyncTask<String, Void, ArrayList<Podcast>> {

        //Context context;
        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            Log.d("Test-Async", "PreExecute method!");
            loading = new ProgressDialog(MainActivity.this);
            loading.setCancelable(false);

            loading.setMessage("Loading News ...");
            loading.setProgress(0);
            loading.show();
        }

        @Override
        protected ArrayList<Podcast> doInBackground(String... strings) {
            Log.d("Test-Async", "DoInBackground method!");
            HttpURLConnection connection = null;
            ArrayList<Podcast> podcasts = null;

            try{
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    podcasts = PodcastParser.PodcastsPullParser.parsePodcasts(connection.getInputStream());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return podcasts;
        }

        @Override
        protected void onPostExecute(ArrayList<Podcast> podcasts) {
            Log.d("Test-Async", "PostExecute method!");

            if (podcasts != null && podcasts.size() != 0) {
                Log.d("Test-Async", "We got our podcast info!");
                Log.d("Test-Async", "" + podcasts.toString());
                Log.d("Test-Async", "Number of podcasts: " + podcasts.size());

                loading.setMessage("Loaded!");
                loading.setProgress(100);
                loading.dismiss();
                loading.setProgress(0);

                generateListView(podcasts);

            } else {
                Log.d("Test-Async", "Something's wrong!");
            }
        }
    }
}