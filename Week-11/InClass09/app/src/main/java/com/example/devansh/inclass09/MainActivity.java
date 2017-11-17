package com.example.devansh.inclass09;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Test", "onAuthStateChanged:signed_in:" + user.getUid());


                } else {
                    // User is signed out
                    Log.d("Test", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        //myRef.setValue("Mytery Text");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Test", "Value is: " + value);
                //((TextView)findViewById(R.id.editTextEmail)).setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Test", "Failed to read value.", error.toException());
            }
        });


        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x = ((TextView)findViewById(R.id.editTextPassword)).getText() + "";
                Log.d("test", "onClick: x = " + x);
                //myRef.setValue(x);

                String email = ((TextView)findViewById(R.id.editTextEmail)).getText() + "";
                String password = ((TextView)findViewById(R.id.editTextPassword)).getText() + "";

                if(!(email.equals("") || password.equals("")))
                    signIn(email, password);
                else
                    Toast.makeText(MainActivity.this, "Username or Password Empty", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(this, Contacts.class);
            startActivity(intent);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Test", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Test", "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "Log in Failed",
                                    Toast.LENGTH_SHORT).show();
                        }else {



                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String name = user.getDisplayName();
                            String email = user.getEmail();
                            Uri photoUrl = user.getPhotoUrl();
                            String uid = user.getUid();

                            Log.d("Test", "onComplete: name : " + name +  " email : " + email + " photo : " + photoUrl + " \nUID : " + uid);

                            Intent intent = new Intent(MainActivity.this, Contacts.class);
                            startActivity(intent);
                            ((TextView)findViewById(R.id.editTextEmail)).setText("");
                            ((TextView)findViewById(R.id.editTextPassword)).setText("");
                        }

                        // ...
                    }
                });
    }

}
