package com.example.devansh.inclass09;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by devansh on 10/30/17.
 */

public class ContactsAdapter extends ArrayAdapter<Contact> {



    public ContactsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Contact contact = getItem(position);

        ViewPoopy viewPoopy;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
            viewPoopy = new ViewPoopy();
            viewPoopy.name = (TextView) convertView.findViewById(R.id.txtName);
            viewPoopy.email = (TextView) convertView.findViewById(R.id.txtPhone);
            viewPoopy.phone = (TextView) convertView.findViewById(R.id.txtEmail);
            viewPoopy.dept = (TextView) convertView.findViewById(R.id.txtDept);
            viewPoopy.profilepic = (ImageView) convertView.findViewById(R.id.imgProfile);



            convertView.setTag(viewPoopy);

        } else {
            viewPoopy = (ViewPoopy) convertView.getTag();
        }


            viewPoopy.name.setText(contact.name);
            viewPoopy.email.setText(contact.email);
            viewPoopy.phone.setText(contact.phone);
            viewPoopy.dept.setText(contact.dept);
            viewPoopy.profilepic.setImageDrawable(getContext().getResources().getDrawable(contact.image));




        return convertView;
    }
    // View Holder to cache the views
    private static class ViewPoopy{
        TextView name;
        TextView email;
        TextView phone;
        TextView dept;
        ImageView profilepic;
    }
}
