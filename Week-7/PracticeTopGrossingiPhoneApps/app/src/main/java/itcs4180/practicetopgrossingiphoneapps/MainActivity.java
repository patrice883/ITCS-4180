package itcs4180.practicetopgrossingiphoneapps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String ITUNES_URL = "https://rss.itunes.apple.com/api/v1/us/ios-apps/top-grossing/all/25/explicit.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to iTunes JSON API
        connectToAPI();
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
            new AsyncJSONiTunes().execute(ITUNES_URL);
        }

        else {
            Log.d("Test-Connecting", "Is not connected to internet.");
            Toast.makeText(MainActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }

    }


}
