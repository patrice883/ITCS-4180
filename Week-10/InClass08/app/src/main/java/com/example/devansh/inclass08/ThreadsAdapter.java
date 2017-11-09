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
        //Log.d("test-threadAdapter", objects.toString());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        AThread thread = getItem(position);
        Log.d("test-threadAdapter", thread.title);

        AThreadViewThingy viewThingy;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.thread_layout, parent, false);
            viewThingy = new AThreadViewThingy();

            viewThingy.txtThreadName = (TextView) convertView.findViewById(R.id.txtThreadName);
            viewThingy.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);


            viewThingy.txtThreadName.setText(thread.title);
            if(thread.user_created == true){
                viewThingy.imgDelete.setVisibility(View.VISIBLE);
                viewThingy.imgDelete.setClickable(true);
                viewThingy.imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("test-threadAdapter", "Delete was clicked");

                        // Delete thread





                    }
                });

            }

        } else {
            viewThingy = (AThreadViewThingy) convertView.getTag();
        }

        return convertView;
    }
    // View Holder to cache the views
    private static class AThreadViewThingy{
        TextView txtThreadName;
        ImageView imgDelete;
    }


}
