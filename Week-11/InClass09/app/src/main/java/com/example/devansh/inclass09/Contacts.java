package com.example.devansh.inclass09;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        setTitle("Contacts");
        contacts = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(user.getUid()).child("Contacts");

        findViewById(R.id.btnLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
            }
        });

        findViewById(R.id.btnCreateNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Contacts.this, "Create New Contact is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Contacts.this, CreateContact.class);
                startActivity(intent);

            }
        });

        generateView();

        myRef.addChildEventListener(new ChildEventListener() {
            String TAG = "test-ChildListener";
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                contacts.add(getContact(dataSnapshot));
            }

            // Can we ignore this method, since user cannot edit contact?
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            }

            private Contact getContact(DataSnapshot dataSnapshot){
                Contact c = new Contact();
                c.dept = "" + dataSnapshot.child("dept").getValue();
                c.email = "" + dataSnapshot.child("email").getValue();
                c.name = "" + dataSnapshot.child("name").getValue();
                c.phone = "" + dataSnapshot.child("phone").getValue();
                c.image = Integer.parseInt("" + dataSnapshot.child("image").getValue()); // uhhhh
                //Log.d(TAG, "Dept is " + dataSnapshot.child("dept").getValue());

                return c;
            }
        });


    }

    public void generateView(){


        Log.d("test", "Database Receiver : " + myRef.toString());


    }
}
