package com.example.devansh.inclass08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
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

public class MessageThreads extends AppCompatActivity {

    TextView user;
    private final OkHttpClient client = new OkHttpClient();

    String API_GET_THREAD = "http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread";
    String API_ADDTHREAD = "http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/add";
    String API_DELETETHREAD = "http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/delete/1";

    UserToken token = null;
    ManyThreads threads = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_threads);

        if(getIntent() != null) {
            token = (UserToken) getIntent().getExtras().get(MainActivity.TOKEN_KEY);

            user = (TextView) findViewById(R.id.txtUser);
            user.setText(token.user_fname + " " + token.user_lname);

            getThreads();

        }
        else{
            printToast("Error. Returning to Login");
            finish();
        }


    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout button
    ///////////////////////////////////////////////////////////////////////////
    public void logoutClick(View v){
        Log.d("test-msgs", "Logout was clicked");

        // Delete user token
        token = null;

        // Go back to Login
        Intent intent = new Intent(MessageThreads.this, MainActivity.class);
        intent.putExtra(MainActivity.TOKEN_KEY, token);
        startActivity(intent);
        finish();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Add New Thread Button
    ///////////////////////////////////////////////////////////////////////////
    public void addNewThreadClick(View v){
        Log.d("test-msgs", "Add New Thread was clicked");

        // Get text from edit text
        String threadName = (findViewById(R.id.editAddNewThread)).toString();
        addThread(threadName);

    }

    private void addThread(String threadName){
        RequestBody formBody = new FormBody.Builder()
                .add("title", threadName)
                .build();

        Request request = new Request.Builder()
                .url(API_GET_THREAD)
                .header("Authorization", "BEARER " + token.token)
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
                    Log.d("test-msgs", "Added a Thread. Time to update listview");

                    // Update ListView
                }

            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get Thread Info
    ///////////////////////////////////////////////////////////////////////////
    private void getThreads(){

        Request request = new Request.Builder()
                .url(API_GET_THREAD)
                .header("Authorization", "BEARER " + token.token)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                printToast("Failure to Connect to Server");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();

                if(response.isSuccessful()){
                    Log.d("test-msgs", "Successfull YAYY");
                    //Toast.makeText(SignUp.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                    //finish();

                    threads = gson.fromJson(response.body().string(), ManyThreads.class);
                    generateListViewV2();
                }
                else{
                    //parse JSON to get the error message
                    SignUp.Message msg = gson.fromJson(response.body().string(), SignUp.Message.class);
                    printToast(msg.message);

                }
            }
        });

    }

    ///////////////////////////////////////////////////////////////////////////
    // Generate Listview
    ///////////////////////////////////////////////////////////////////////////
    private void generateListViewV2(){

        Log.d("test-generatingListView", threads.threads.toString());
        final ListView listView = (ListView)findViewById(R.id.listView);
        final ThreadsAdapter adapter = new ThreadsAdapter(this, R.layout.thread_layout, threads.threads);


        /**
         * @ISSUE:
         * The threads displayed are starting to repeat.
         * The thread arraylist getting sent in is correct... but for some reason,
         * it's taking time and the first one's sent in are just repeating
         * When clicking a thread, the correct title is shown in the Log, even though
         * the wrong title is being displayed
         */

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("test-msgs", String.valueOf(parent.getItemAtPosition(position)) + " was clicked");

                    }
                }
        );

    }

    private void generateListViewV1(){

        String[] t = new String[threads.threads.size()];

        for(int i = 0; i < t.length; i++){
            t[i] = threads.threads.get(i).title;
            //Log.d("test", "Converting to names and it is : " + t[i].toString());
        }


        Log.d("test-msgs", "Generating List View... got array of threads");

        ListAdapter sourceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, t);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(sourceAdapter);

        Log.d("test-msgs", "Set up list view)");

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Log.d("test-msgs", String.valueOf(parent.getItemAtPosition(position)) + " was clicked");
                        /*
                        String item = String.valueOf(parent.getItemAtPosition(position));

                        Log.d("Test-ListView", sources.get(position).name + " was clicked!");
                        Source clickedSource = (Source) sources.get(position);

                        Intent intent = new Intent(MainActivity.this, ShowArticles.class);
                        intent.putExtra(SOURCE_KEY, clickedSource);

                        startActivity(intent);
                        */

                    }
                }
        );


    }


    ///////////////////////////////////////////////////////////////////////////
    // Supporting Stuff
    ///////////////////////////////////////////////////////////////////////////
    void printToast(final String msg){
        user.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MessageThreads.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class Message{
        String status, message;


    }
}
