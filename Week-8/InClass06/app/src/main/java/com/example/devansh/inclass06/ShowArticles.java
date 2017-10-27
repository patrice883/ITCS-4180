package com.example.devansh.inclass06;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

public class ShowArticles extends AppCompatActivity implements View.OnClickListener{

    String url = "https://newsapi.org/v1/articles";
    String apiKey = "2bd428d817a44d618763823c0b14efaf";   //DO NOT TOUCH LOL

    ArrayList<Article> articlesArrayList = new ArrayList<>();

    Source source;
    String id;

    //Picasso.with(this).load(articlesArrayList.get(i).urlToImage).into(image);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_articles);

        if(getIntent() != null && getIntent().getExtras() != null) {
            source = (Source) getIntent().getExtras().get("SOURCE");
            setTitle(source.name);

            //generateArticlesOnScreen();
            new GetArticlesAsync(articlesArrayList,source.id).execute(url);
        }




    }


    private class GetArticlesAsync extends AsyncTask<String, Void, ArrayList<Article> > {

        private ArrayList<Article> articles;
        private String keyword;

        public GetArticlesAsync(ArrayList<Article> keywords, String source) {
            this.articles = keywords;
            this.keyword = source;
        }


        ProgressDialog loadDictionary;
        @Override
        protected void onPreExecute() {
            loadDictionary = new ProgressDialog(ShowArticles.this);
            loadDictionary.setCancelable(false);
            loadDictionary.setMessage("Loading Stories ...");
            loadDictionary.setProgress(0);
            loadDictionary.show();
        }

        @Override
        protected ArrayList<Article> doInBackground(String... strings) {
            StringTokenizer st = null;

            try {
                URL url = new URL(strings[0] + "?source=" + keyword + "&apiKey=" + apiKey);
                Log.d("Test-Articles", "" + strings[0] + "?source=" + keyword + "&apiKey=" + apiKey);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                String json = IOUtils.toString(connection.getInputStream(), "UTF8");
                JSONObject root = new JSONObject(json);
                JSONArray keys = root.getJSONArray("articles");

                Log.d("Test-Articles", "The keys are : " + keys.toString());

                Log.d("test-Articles", "We are here and keys length is : " + keys.length());
                for(int i = 0; i < keys.length();i++){

                    Article currentArticle = new Article();
                    JSONObject currentObj = keys.getJSONObject(i);
                    currentArticle.author = currentObj.getString("author");
                    currentArticle.title = currentObj.getString("title");

                    currentArticle.description = currentObj.getString("description");
                    currentArticle.url = currentObj.getString("url");
                    currentArticle.urlToImage = currentObj.getString("urlToImage");
                    currentArticle.publishedAt = currentObj.getString("publishedAt");

                    Log.d("test-Articles", "Current Article: " + currentArticle.toString());
                    articles.add(currentArticle);
                }
                Log.d("test-Articles", "We are here and keys are iterated");

                Log.d("test-keys", "keywords are : " + keys);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return articles;
        }


        @Override
        protected void onPostExecute(ArrayList<Article> strings) {
            if(strings != null){
                Log.d("test", strings.toString());
                loadDictionary.setMessage("Stories Loaded!");
                loadDictionary.setProgress(100);
                loadDictionary.dismiss();
                loadDictionary.setProgress(0);

                //generateArticlesOnScreen();
                Log.d("Test-Articles", "The size of articles is  : " + articlesArrayList.size());
                generateArticlesOnScreen();




            }else {
                Log.d("test_getSourcePost", "source is null");
            }


        }

    }

    private void generateArticlesOnScreen() {

        ListView listView = (ListView)findViewById(R.id.listview);
        ArticlesAdapter adapter = new ArticlesAdapter(this, R.layout.article_layout, articlesArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Log.d("Test-ListView", articlesArrayList.get(position).title + " was clicked!");
                        Article clickedSource = articlesArrayList.get(position);

                        WebView webview = new WebView(ShowArticles.this);
                        setContentView(webview);

                        webview.loadUrl(clickedSource.url);


                    }
                }
        );
    }


    @Override
    public void onClick(View view) {
        //Log.d("Test-OnClick", "Some Button Was Clicked");

//        if(isConnected()){
//            Log.d("Test-Connecting", "Is connected to internet. Ready to connect");
//            Log.d("Test-Show", "Some Article was clicked");
//            Intent x = new Intent("android.intent.action.VIEW");
//            x.setData(Uri.parse(articlesArrayList.get(articlesIDs.indexOf(view.getId())).url));
//            startActivity(x);
//        }
//
//        else {
//            Log.d("Test-Connecting", "Is not connected to internet.");
//            Toast.makeText(this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
//        }
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
