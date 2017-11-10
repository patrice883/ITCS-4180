package com.example.devansh.inclass08;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aileen on 11/9/2017.
 */

class ThreadsAdapter extends ArrayAdapter<AThread> {

    public ThreadsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<AThread> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        AThread thread = getItem(position);
        Log.d("test-threadAdapter", "Currently ON : " + thread.title + " and Position : " + position);

        AThreadViewThingy viewThingy;


        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.thread_layout, parent, false);
            viewThingy = new AThreadViewThingy();
        } else {
            viewThingy = (AThreadViewThingy) convertView.getTag();

            Log.d("test-info", "id : " + convertView.findViewById(R.id.txtThreadName));

        }



        viewThingy.txtThreadName = (TextView) convertView.findViewById(R.id.txtThreadName);
        viewThingy.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);

        viewThingy.txtThreadName.setText(thread.title);

        if(thread.user_created == true){
            viewThingy.imgDelete.setVisibility(View.VISIBLE);
        }



        return convertView;
    }
    // View Holder to cache the views
    private static class AThreadViewThingy{
        TextView txtThreadName;
        ImageView imgDelete;
    }


}
