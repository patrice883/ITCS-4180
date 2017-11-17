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

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by devansh on 11/9/17.
 */

public class MessageAdapter extends ArrayAdapter<AMessage> {

    public MessageAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<AMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AMessage aMessage = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_layout, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.txtMessage)).setText(aMessage.message);
        ((TextView) convertView.findViewById(R.id.txtMessageID)).setText(aMessage.id);
        ((TextView) convertView.findViewById(R.id.txtName)).setText(aMessage.user_fname +  " " + aMessage.user_lname);
        ((ImageView) convertView.findViewById(R.id.imgTrashIcon)).setVisibility(View.INVISIBLE);

        PrettyTime p = new PrettyTime();

        Date one = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.d("test-date", "Date : " + aMessage.created_at);
            one = simpleDateFormat.parse(aMessage.created_at);
        } catch (ParseException e) {
            Log.d("test-ERROR", "one date could not be parsed");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(one);
        calendar.add(calendar.HOUR, -5);
        //calendar.add(calendar.MINUTE, -15);
        calendar.add(calendar.SECOND, -15);

        Log.d("Test-V2", " --> " + p.format(calendar));

        ((TextView) convertView.findViewById(R.id.txtTimeLeft)).setText(p.format(calendar));


        if(aMessage.user_created) {
            ((ImageView) convertView.findViewById(R.id.imgTrashIcon)).setVisibility(View.VISIBLE);
            ((TextView) convertView.findViewById(R.id.txtName)).setText("Me");
        }

        return convertView;
    }
}
