package com.example.devansh.inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    String API_SIGNUPURL = "http://ec2-54-164-74-55.compute-1.amazonaws.com/api/signup";
    private final OkHttpClient client = new OkHttpClient();
    String email = "";
    String password = "";
    String password2 = "";
    String fname = "";
    String lname = "";
    EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editEmail = (EditText) findViewById(R.id.editEmail);

        findViewById(R.id.btnSignUpFinal).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnSignUpFinal){
            Log.d("test_signup", "onClick: SIGNUP WAS CLICKED");

            email = ((TextView)findViewById(R.id.editEmail)).getText() + "";
            password = ((TextView)findViewById(R.id.editPass)).getText() + "";
            password2 = ((TextView)findViewById(R.id.editPassRepeat)).getText() + "";
            fname = ((TextView)findViewById(R.id.editFirstName)).getText() + "";
            lname = ((TextView)findViewById(R.id.editLastName)).getText() + "";


            if(isValid())
                userSignUp(email, password, fname, lname);
            else{
                //((TextView)findViewById(R.id.editEmail)).setText("");
                ((TextView)findViewById(R.id.editPass)).setText("");
                ((TextView)findViewById(R.id.editPassRepeat)).setText("");
                //((TextView)findViewById(R.id.editFirstName)).setText("");
                //((TextView)findViewById(R.id.editLastName)).setText("");
            }

        }else if(view.getId() == R.id.btnCancel){
            Log.d("test_signup", "onClick: CANCEL WAS CLICKED");
            finish();

        }else{
            Log.d("test_signup", "onClick: SOMETHING RANDOM WAS CLICKED");

        }
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

    private void userSignUp(String email, String password, String fname, String lname) {

        RequestBody formBody = new FormBody.Builder()
                .add("email", email )
                .add("password", password)
                .add("fname", fname)
                .add("lname", lname)
                .build();

        Request request = new Request.Builder()
                .url(API_SIGNUPURL)
                .post(formBody)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                printToast("Failure to Connect to Server");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()){
                    Log.d("test_signup", "Successfull YAYY");
                    //Toast.makeText(SignUp.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    // parse JSON to get the error message
                    Gson gson = new Gson();
                    Message msg = gson.fromJson(response.body().string(), Message.class);
                    printToast(msg.message);
                    reset();

                }
            }
        });
    }

    void printToast(final String msg){
        editEmail.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SignUp.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void reset(){
        editEmail.post(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.editPass)).setText("");
                ((TextView)findViewById(R.id.editPassRepeat)).setText("");
                ((TextView)findViewById(R.id.editEmail)).setText("");
            }
        });

    }

    static class Message{
        String status, message;


    }
}
