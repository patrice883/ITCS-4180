package itcs4180.practicetopgrossingiphoneapps;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aileen on 10/16/2017.
 *
 * NOTES ---
 * --> How to add Library <--
 * 1. Right click app folder
 * 2. > Open Module Settings
 * 3. > Dependencies
 * 4. > + Button
 * 5. > Search "apache.commons.io"
 */

public class AsyncJSONiTunes extends AsyncTask<String, Void, Feed> {

    @Override
    protected Feed doInBackground(String... strings) {
        Feed feed = new Feed();
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

                JSONObject feedJson = root.getJSONObject("feed");

                feed.title = feedJson.getString("title");
                //    Log.d("Test-Async", "Feed Title: " + feed.title);
                feed.id = feedJson.getString("id");
                //    Log.d("Test-Async", "Feed ID: " + feed.id);
                Log.d("Test-Async", "Finished Title and Id..");

                JSONObject authorJson = feedJson.getJSONObject("author");

                Author author = new Author();
                author.name = authorJson.getString("name");
                author.uri = authorJson.getString("uri");
                feed.author = author;
                Log.d("Test-Async", "Finished Author..");

                ArrayList<Link> links = new ArrayList<>();
                JSONArray linksJson = feedJson.getJSONArray("links");
                for (int i = 0; i < linksJson.length(); i++) {
                    JSONObject lJson = linksJson.getJSONObject(i);
                    Link l = new Link();
                    if(!lJson.isNull("self"))
                        l.self = lJson.getString("self");
                    if(!lJson.isNull("alternate"))
                        l.alternate = lJson.getString("alternate");

                    links.add(l);
                }
                feed.links = links;
                Log.d("Test-Async", "Finished Links..");

                feed.copyright = feedJson.getString("copyright");
                feed.country = feedJson.getString("country");
                feed.icon = feedJson.getString("icon");
                feed.updated = feedJson.getString("updated");

                ArrayList<Result> results = new ArrayList<>();
                JSONArray rJson = feedJson.getJSONArray("results");
                for (int i = 0; i < rJson.length(); i++) {
                    JSONObject resultsJson = rJson.getJSONObject(i);

                    Result r = new Result();
                    r.artistUrl = resultsJson.getString("artistUrl");
                    r.artistId = resultsJson.getString("artistId");
                    r.artistName = resultsJson.getString("artistName");
                    r.artworkUrl100 = resultsJson.getString("artworkUrl100");
                    r.copyright = resultsJson.getString("copyright");
                    Log.d("Test-Async", "Finished in a Result: artistUrl through copyright");

                    ArrayList<Genre> genres = new ArrayList<>();
                    JSONArray gJson = resultsJson.getJSONArray("genres");
                    for (int j = 0; j < gJson.length(); j++) {
                        JSONObject genreJson = gJson.getJSONObject(j);
                        Genre g = new Genre();

                        g.genreId = genreJson.getString("genreId");
                        g.name = genreJson.getString("name");
                        g.url = genreJson.getString("url");

                        genres.add(g);
                    }
                    r.genres = genres;
                    Log.d("Test-Async", "Finished in a Result: Genre");

                    r.id = resultsJson.getString("id");
                    r.kind = resultsJson.getString("kind");
                    r.name = resultsJson.getString("name");
                    r.releaseDate = resultsJson.getString("releaseDate");
                    r.url = resultsJson.getString("url");

                    results.add(r);
                    Log.d("Test-Async", "Finished one result");

                }

                feed.results = results;

            } // end if
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return feed;

    } // end doInBackground

    @Override
    protected void onPostExecute(Feed feed) {
        if(feed != null){
            Log.d("Test-Async", "We got our feed! \n" + feed.toString());
            Log.d("Test-Async", "Size of Results:" + (feed.getResults()).size());
        }
        else{
            Log.d("Test-Async", "Something's wrong!");
        }
    }
} // end class

/*
  HttpURLConnection connection = null;
    ArrayList<Person> result = new ArrayList<>();
    try {
        URL url = new URL(params[0]);
        connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String json = IOUtils.toString(connection.getInputStream(), "UTF8");

            JSONObject root = new JSONObject(json);
            JSONArray persons = root.getJSONArray("persons");
            for (int i=0;i<persons.length();i++) {
                JSONObject personJson = persons.getJSONObject(i);
                Person person = new Person();
                person.name = personJson.getString("name");
                person.id = personJson.getLong("id");
                person.age = personJson.getInt("age");

                JSONObject addressJson = personJson.getJSONObject("address");

                Address address = new Address();
                address.line1 = addressJson.getString("line1");
                address.city = addressJson.getString("city");
                address.state = addressJson.getString("state");
                address.zip = addressJson.getString("zip");

                person.address = address;
                result.add(person);
            }
        }

 */