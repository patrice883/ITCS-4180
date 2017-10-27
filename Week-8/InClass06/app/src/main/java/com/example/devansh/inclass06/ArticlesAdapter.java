package com.example.devansh.inclass06;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by devansh on 10/23/17.
 */

public class ArticlesAdapter extends ArrayAdapter<Article> {

    public ArticlesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Article article = getItem(position);

        ViewPoopy viewPoopy;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_layout, parent, false);
            viewPoopy = new ViewPoopy();
            viewPoopy.txtAuthor = (TextView) convertView.findViewById(R.id.txtAuthor);
            viewPoopy.txtTitle = (TextView) convertView.findViewById(R.id.txtNewsTitle);
            viewPoopy.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            viewPoopy.imgImageImage = (ImageView) convertView.findViewById(R.id.imgURLImage);



            viewPoopy.txtAuthor.setText(article.author);
            viewPoopy.txtDate.setText(article.publishedAt);
            viewPoopy.txtTitle.setText(article.title);

            if(article.urlToImage.equals("") || article.urlToImage == null){
                Log.d("demo", "THIS HAS HAPPENED");
            }else
                Picasso.with(getContext()).load(article.urlToImage).into(viewPoopy.imgImageImage);

        } else {
            viewPoopy = (ViewPoopy) convertView.getTag();
        }





        return convertView;
    }
     // View Holder to cache the views
    private static class ViewPoopy{
         TextView txtTitle;
         TextView txtAuthor;
         TextView txtDate;
         ImageView imgImageImage;
     }
}
