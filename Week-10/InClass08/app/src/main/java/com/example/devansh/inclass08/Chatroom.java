package com.example.devansh.inclass08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Chatroom extends AppCompatActivity implements View.OnClickListener {

    private final OkHttpClient client = new OkHttpClient();
    AThread aThread = null;
    UserToken token = null;
    TextView user;

    ManyMessages messages = null;

    String API_GET_MESSAGES = "http://ec2-54-164-74-55.compute-1.amazonaws.com/api/messages/";
    String API_ADDMESSAGES = "http://ec2-54-164-74-55.compute-1.amazonaws.com/api/message/add";
    String API_DELETEMESSAGE = "http://ec2-54-164-74-55.compute-1.amazonaws.com/api/message/delete/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        setTitle("Chatroom");
        user = (TextView) findViewById(R.id.txtChatroomName);

        if (getIntent() != null) {
            Log.d("test-hmm", " ---> " + getIntent().getExtras().get("Thread"));
            aThread = (AThread) getIntent().getExtras().get("Thread");
            token = (UserToken) getIntent().getExtras().get(MainActivity.TOKEN_KEY);

            ((TextView) findViewById(R.id.txtChatroomName)).setText(aThread.title);

            getMessages();

        } else {
            printToast("Error. Returning to Thread Screen");
            finish();
        }

        findViewById(R.id.imgSendMessage).setOnClickListener(this);
        findViewById(R.id.imgGoBack).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imgGoBack) {
            Log.d("test-chatroom", "Home Button was clicked");
            finish();
        } else if (view.getId() == R.id.imgSendMessage) {
            Log.d("test-chatroom", "Send Button was clicked");
            addMessage();
        } else {
            Log.d("test-chatroom", "Some Random Button was clicked");
        }
    }

    public void addMessage() {
        String msg = ((TextView) (findViewById(R.id.editAddNewThread))).getText() + "";
        RequestBody formBody = new FormBody.Builder()
                .add("message", msg)
                .add("thread_id", aThread.id)
                .build();

        Request request = new Request.Builder()
                .url(API_ADDMESSAGES)
                .header("Authorization", "BEARER " + token.token)
                .post(formBody)
                .build();

        Log.d("Test-INFO", request.toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                printToast("Failure to Connect to Server");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                if (response.isSuccessful()) {
                    Log.d("test-msgs", "Added a Message. Time to update listview");

                    // Update ListView
                    getMessages();
                    generateListView();
                } else {
                    MainActivity.Message msg = gson.fromJson(response.body().string(), MainActivity.Message.class);
                    printToast(msg.message);
                }

            }
        });

        for (int i = 0; i < 100000; i++) ;
        ((TextView) (findViewById(R.id.editAddNewThread))).setText("");
    }

    public void deleteMessage(View view) {
        Log.d("test-delete", "Delete Thread Button was Clicked");
        View parentView = (View) view.getParent();
        String x = "" + ((TextView) parentView.findViewById(R.id.txtMessage)).getText();
        Log.d("test-delete", "Delete Title = " + x);

        Request request = new Request.Builder()
                .url(API_DELETEMESSAGE + "" + ((TextView) parentView.findViewById(R.id.txtMessageID)).getText())
                .header("Authorization", "BEARER " + token.token)
                .build();

        Log.d("test-delete", "link = " + request.toString());
        Log.d("test-delete", "link = " + request);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                printToast("Failure to Connect to Server");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();

                if (response.isSuccessful()) {
                    Log.d("test-msgs", "Delete was Successfull YAYY");

                    getMessages();
                    generateListView();
                } else {
                    //parse JSON to get the error message
                    SignUp.Message msg = gson.fromJson(response.body().string(), SignUp.Message.class);
                    printToast(msg.message);

                }
            }
        });
    }

    public void getMessages() {


        Request request = new Request.Builder()
                .url(API_GET_MESSAGES + "" + aThread.id)
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

                if (response.isSuccessful()) {
                    Log.d("test-msgs", "Successfull YAYY");
                    //Toast.makeText(SignUp.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                    //finish();

                    messages = gson.fromJson(response.body().string(), ManyMessages.class);

                    for (int i = 0; i < messages.messages.size(); i++) {
                        if (token.user_id == Integer.parseInt(messages.messages.get(i).user_id)) {
                            messages.messages.get(i).user_created = true;
                        }
                    }

                    Collections.reverse(messages.messages);

                    generateListView();
                } else {
                    //parse JSON to get the error message
                    SignUp.Message msg = gson.fromJson(response.body().string(), SignUp.Message.class);
                    printToast(msg.message);

                }
            }


        });
    }

    private void generateListView() {
        user.post(new Runnable() {
            @Override
            public void run() {
                Log.d("test-generatingListView", messages.messages.toString());
                ListView listView = (ListView) findViewById(R.id.listViewMessages);
                MessageAdapter adapter = new MessageAdapter(Chatroom.this, R.layout.message_layout, messages.messages);


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
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Log.d("test-msgs", String.valueOf(parent.getItemAtPosition(position)) + " was clicked");

//                                AThread athread = (AThread) parent.getItemAtPosition(position);
//
//                                Intent intent = new Intent(MessageThreads.this, Chatroom.class);
//                                intent.putExtra("Thread", athread);
//                                startActivity(intent);

                            }
                        }
                );
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // Supporting Stuff
    ///////////////////////////////////////////////////////////////////////////
    void printToast(final String msg) {
        user.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Chatroom.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class Message {
        String status, message;


    }
}
