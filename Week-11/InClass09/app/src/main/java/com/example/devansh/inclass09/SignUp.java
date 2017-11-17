package com.example.devansh.inclass09;

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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String email = "";
    String password = "";
    String password2 = "";
    String fname = "";
    String lname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.btnSignUpFinal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = ((TextView)findViewById(R.id.editEmail)).getText() + "";
                password = ((TextView)findViewById(R.id.editPass)).getText() + "";
                password2 = ((TextView)findViewById(R.id.editPassRepeat)).getText() + "";
                fname = ((TextView)findViewById(R.id.editFirstName)).getText() + "";
                lname = ((TextView)findViewById(R.id.editLastName)).getText() + "";

                if(isValid())
                    userSignUp(email, password);
                else{
                    ((TextView)findViewById(R.id.editPass)).setText("");
                    ((TextView)findViewById(R.id.editPassRepeat)).setText("");
                }
            }


        });
    }

    private boolean isValid() {

        boolean go = true;

        if(fname.equals("")){
            Toast.makeText(this, "First Name is Empty! Try Again!", Toast.LENGTH_SHORT).show();
            go = false;
        }

        if(lname.equals("")){
            Toast.makeText(this, "Last Name is Empty! Try Again!", Toast.LENGTH_SHORT).show();
            go = false;
        }

        if(email.equals("")){
            Toast.makeText(this, "Email is Empty! Try Again!", Toast.LENGTH_SHORT).show();
            go = false;
        }

        if(!(password.equals(password2) && !password.equals(""))){
            Toast.makeText(this, "Passwords dont match! Try Again!", Toast.LENGTH_SHORT).show();
            go = false;
        }
        return go;
    }

    private void userSignUp(String email, String password) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Test", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.


                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Signup Failed",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignUp.this, "User Successfully Signed Up",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                // Name, email address, and profile photo Url



                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(fname + " " + lname)
                                        .build();
                                user.updateProfile(profileUpdates);

                                String name = user.getDisplayName();
                                String email = user.getEmail();
                                Uri photoUrl = user.getPhotoUrl();

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getToken() instead.
                                String uid = user.getUid();

                                Log.d("Test", "onComplete: name : "  +  " email : " + email + " photo : " + photoUrl + " \nUID : " + uid);
                            }

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference(user.getUid());


                            finish();
                        }

                        // ...
                    }
                });
    }
}
