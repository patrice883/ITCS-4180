package com.example.uncc.midterm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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

public class NewsActivity extends AppCompatActivity {

    private String ARTICLES_URL = "https://newsapi.org/v1/articles";
    private String src, apiKey = "20e2b2c64316451eb02347c11b4d7cbf"; // I hope I typed this right :')))
    Source source;

    private ArrayList<Articles> articles;

    RelativeLayout layout;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Get Source object from sent Intent
        if (getIntent() != null && getIntent().getExtras() != null) {
            source = (Source) getIntent().getExtras().getSerializable(MainActivity.SOURCE_KEY);

            Log.d("Test-NewsActivity", "We got here!!! Source is " + source.getName());
        }

        // Set Activity Title
        setTitle(source.getName());

        // Update Articles API URL
        src = source.getId();
        ARTICLES_URL += "?source=" + src + "&apiKey=" + apiKey;

        Log.d("Test-NewsCreate", "New API URL is " + ARTICLES_URL);

        // Connect to API to retrieve article information
        articles = new ArrayList<Articles>();
        connectToAPI();

        // Creating Relative Layout
        layout = new RelativeLayout(this);


    }


    ///////////////////////////////////////////////////////////////////////////
    // Display Stuff
    ///////////////////////////////////////////////////////////////////////////
    public void displayStuff(){

        sv = new ScrollView(this);
        sv.setId(View.generateViewId());
        sv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        TextView old = null;

        int index = 0;
        for(;index < articles.size(); index++) {
            final Articles article = articles.get(index);

            // Generate Image ==========================================================
            ImageView image = new ImageView(this);
            image.setId(View.generateViewId());

            String imageURL = article.getUrlToImage();

            if (isConnected()) {
                Log.d("Test-Connecting", "Is connected to internet. Ready to connect yayayayayy");
                //new AsyncSource(sources, MainActivity.this).execute(SOURCE_URL);
                new NewsActivity.AsyncImage(image).execute(imageURL);
            } else {
                Log.d("Test-Connecting", "Is not connected to internet.");
                Toast.makeText(NewsActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
            }

            RelativeLayout.LayoutParams imageDetails = new RelativeLayout.LayoutParams(
                    200, 200
            );
            if(old != null) {
                imageDetails.addRule(RelativeLayout.BELOW, old.getId());
            }
            imageDetails.setMargins(50, 20, 20, 20);

            layout.addView(image, imageDetails);

            // Generate Title ============================================================
            TextView title = new TextView(this);
            title.setId(View.generateViewId());
            title.setText(article.getTitle());
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Test-Article", "Thingy was clicked!");

                    String openThis = article.getUrl();

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(openThis));
                    startActivity(intent);

                }
            });

            RelativeLayout.LayoutParams titleDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            //Give rules to position
            titleDetails.addRule(RelativeLayout.RIGHT_OF, image.getId());
            titleDetails.addRule(RelativeLayout.ALIGN_TOP, image.getId());
            titleDetails.setMargins(50, 0, 50, 20);

            // Add View
            layout.addView(title, titleDetails);

            // Generate Author ============================================================
            TextView author = new TextView(this);
            author.setId(View.generateViewId());
            author.setText(article.getAuthor());

            RelativeLayout.LayoutParams authorDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            //Give rules to position
            authorDetails.addRule(RelativeLayout.BELOW, image.getId());
            authorDetails.setMargins(50, 20, 20, 20);

            // Add View
            layout.addView(author, authorDetails);

            old = author;

            // Generate Date ============================================================
            TextView date = new TextView(this);
            date.setId(View.generateViewId());
            date.setText(article.getPublishedAt());

            RelativeLayout.LayoutParams dateDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            //Give rules to position
            //dateDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            //dateDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            dateDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            dateDetails.addRule(RelativeLayout.ALIGN_TOP, author.getId());
            dateDetails.setMargins(50, 0, 50, 20);

            // Add View
            layout.addView(date, dateDetails);

        }
        // ================================================================================
        // Set Layout to show
        sv.addView(layout);
        setContentView(sv);

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
            new NewsActivity.AsyncArticles(articles).execute(ARTICLES_URL);
        }

        else {
            Log.d("Test-Connecting", "Is not connected to internet.");
            Toast.makeText(NewsActivity.this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }

    }



    ///////////////////////////////////////////////////////////////////////////
    // AsyncTask: Image
    ///////////////////////////////////////////////////////////////////////////
    public class AsyncImage extends AsyncTask<String, Void, Void>{
        ImageView imageView;
        Bitmap bitmap = null;

        public AsyncImage(ImageView iv) {
            imageView = iv;
        }

        @Override
        protected Void doInBackground(String... strings) {


            HttpURLConnection connection = null;
            bitmap = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (bitmap != null && imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // AsyncTask: Articles
    ///////////////////////////////////////////////////////////////////////////

    public class AsyncArticles extends AsyncTask<String, Void, ArrayList<Articles>> {

        ArrayList<Articles> articles;
        ProgressDialog loading;

        public AsyncArticles(ArrayList<Articles> a){
            articles = a;
        }


        @Override
        protected void onPreExecute() {
            loading = new ProgressDialog(NewsActivity.this);
            loading.setCancelable(false);

            loading.setMessage("Loading Stories ...");
            loading.setProgress(0);
            loading.show();
        }

        @Override
        protected ArrayList<Articles> doInBackground(String... strings) {

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

                    JSONArray a = root.getJSONArray("articles");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject articleJson = a.getJSONObject(i);
                        Articles article = new Articles();

                        article.author = articleJson.getString("author");
                        article.title = articleJson.getString("title");
                        article.description = articleJson.getString("description");
                        article.url = articleJson.getString("url");
                        article.urlToImage = articleJson.getString("urlToImage");
                        article.publishedAt = articleJson.getString("publishedAt");


                        articles.add(article);
                    } // end for

                } // end if
            } // end try
            catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return articles;
        } // end doInBackground

        @Override
        protected void onPostExecute(ArrayList<Articles> articles) {
            if(articles != null && articles.size() != 0){
                Log.d("Test-Async", "We got our sources!");
                Log.d("Test-Async", "" + articles.toString());


                loading.setMessage("Photo Loaded!");
                loading.setProgress(100);
                loading.dismiss();
                loading.setProgress(0);

                displayStuff(); //hehe

            }
            else{
                Log.d("Test-Async", "Something's wrong!");
            }
        }
    }
}
