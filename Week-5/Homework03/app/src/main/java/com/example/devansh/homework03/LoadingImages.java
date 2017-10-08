package com.example.devansh.homework03;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Devansh on 10/5/2017.
 */

public class LoadingImages extends AsyncTask<String, Void, Void> {

    ImageView imageView;
    Bitmap bitmap = null;
    ProgressDialog loadPhoto;

    Context context;

    public LoadingImages(ImageView iv, Context c) {
        imageView = iv;
        context = c;
    }
    @Override
    protected void onPreExecute() {
        loadPhoto = new ProgressDialog(context);
        loadPhoto.setCancelable(false);

        loadPhoto.setMessage("Loading Photo ...");
        loadPhoto.setProgress(0);
        loadPhoto.show();
    }


    @Override
    protected void onPostExecute(Void aVoid) {

        loadPhoto.setMessage("Photo Loaded!");
        loadPhoto.setProgress(100);
        loadPhoto.dismiss();
        loadPhoto.setProgress(0);

        if (bitmap != null && imageView != null) {
            imageView.setImageBitmap(bitmap);


        }
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
                Log.d("test", "We got a new Bitmap");
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close open connection
        }
        return null;
    }

}
