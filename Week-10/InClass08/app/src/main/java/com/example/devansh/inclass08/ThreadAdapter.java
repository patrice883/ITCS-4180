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
import android.widget.Toast;

import java.util.List;

/**
 * Created by devansh on 11/9/17.
 */

public class ThreadAdapter extends ArrayAdapter<AThread>{

    public ThreadAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<AThread> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AThread aThread = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.thread_layout, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.txtThreadName)).setText(aThread.title);
        ((TextView) convertView.findViewById(R.id.txtThreadID)).setText(aThread.id);
        ((ImageView) convertView.findViewById(R.id.imgDelete)).setVisibility(View.INVISIBLE);

        if(aThread.user_created) {
            ((ImageView) convertView.findViewById(R.id.imgDelete)).setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}