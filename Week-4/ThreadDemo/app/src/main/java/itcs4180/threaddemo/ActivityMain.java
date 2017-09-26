package itcs4180.threaddemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* This is so messy Q~Q...
 * Videos:
 * 00 - Threads and Threadpool
 * 10 - Handlers
 * 20 - AsyncTask
 */

///////////////////////////////////////////////////////////////////////////
// Video 3 (20) - AsyncTask
///////////////////////////////////////////////////////////////////////////
public class ActivityMain extends AppCompatActivity {

}

///////////////////////////////////////////////////////////////////////////
// Videos 1 and 2 (00 and 10) - Threads and Threadpools; Handlers
///////////////////////////////////////////////////////////////////////////
/*
public class ActivityMain extends AppCompatActivity {

    //3. Create threadpool
    ExecutorService threadPool;


    // 10 Create Handler (From Handler Video)
    Handler handler; // MAKE SURE TO IMPORT 2ND ONE (android handler) not first

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Progress");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);

        // 11 initialize handler
        handler = new Handler(new Handler.Callback() {


            @Override
            public boolean handleMessage(Message message) {

                /*
                Log.d("demo","Messasge received!" + message.what); // how to get the "what" status
                Log.d("demo", "" + message.obj); //how to get the object message
                */


/*
                TextView edit = (TextView) findViewById(R.id.textView);
                switch(message.what){
                    case DoWork.STATUS_START:
                        edit.setText("Starting...");
                        progressDialog.setProgress(0);
                        progressDialog.show();
                        break;
                    case DoWork.STATUS_PROGRESS:
                        edit.setText("In progress...");
                        progressDialog.setProgress(message.getData().getInt(DoWork.PROGRESS_KEY));
                        // 18. How you use the bundle
                        Log.d("demo", "" + message.getData().getInt(DoWork.PROGRESS_KEY));
                        break;
                    case DoWork.STATUS_STOP:
                        edit.setText("Done!!");
                        progressDialog.dismiss();
                        break;
                }


                return false;
            }
        });

        // 4. Initialize threadPool, and say how many threads (#) are in it
        threadPool = Executors.newFixedThreadPool(4);

    }

    public void clickMe(View view){
        Log.d("test", "button was clicked");

        //1. Create Thread
        Thread thread = new Thread(new DoWork(), "Worker 1");

        //thread.start(); // Start Work

        // 5. How to start using the threadpool now (instead of just starting a whole bunch of individual threads)
        // This allows a max # of threads to exist at one point. If you go over that max, new thread becomes queued instead
        // Helps avoid app crashing from too many threads everywhere :D~
        threadPool.execute(new DoWork());
    }



    // 2. Create Runnable Class
    class DoWork implements Runnable{

        // 14 make the status int constant variables for us to use :')
        static final int STATUS_START = 0x00;
        static final int STATUS_PROGRESS = 0x01;
        static final int STATUS_STOP = 0x02;

        static final String PROGRESS_KEY = "PROGRESS"; // used for bundle

        @Override
        public void run() {
            //Log.d("demo", "started work..............");

            // 14. -------------------------------------------------
            Message startMessage = new Message();
            startMessage.what = STATUS_START;
            handler.sendMessage(startMessage);
            // -----------------------------------------------------

            // fake work for example :'D
            for(int i = 0; i < 100; i ++) {
                for (int j = 0; j < 10000000; j++) {

                }
                // Empty message
                //12. handler.sendEmptyMessage(100);
               /* Message msg = new Message();
               if(i % 100 == 0){
                    // Create message
                    msg.what = (i/100); // some status integer
                    handler.sendMessage(msg);
                }
                */
                // 13 can also send an object
               /* msg.obj = (Integer)i;
                handler.sendMessage(msg);
                */
/* /*
                // 16. -------------------------------------------------
                Message message = new Message();
                message.what = STATUS_PROGRESS;
                //handler.sendMessage(message);
                // -----------------------------------------------------
                // 17. Creating a Bundle
                Bundle bundle = new Bundle();
                bundle.putInt(PROGRESS_KEY, (Integer)i);
                message.setData(bundle);
                handler.sendMessage(message);

            }
                // 15. -------------------------------------------------
                Message stopMessage = new Message();
                stopMessage.what = STATUS_STOP;
                handler.sendMessage(stopMessage);
                // -----------------------------------------------------


            Log.d("demo", "end work...........");
        }
    }
}
*/