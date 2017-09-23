package itcs4180.startingactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // REQUEST CODE
    // Use to identify the request
    public static final int REQ_CODE = 100;

    // set some constant value for the received string value's key
    public static final String VALUE_KEY = "value";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set up Listener
        findViewById(R.id.btnGoToSecond).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, REQ_CODE); // start for result. use the request code
            }
        });

    }

    // Can use Ctrl + O as shortcut to override methods
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // dont need this
        //super.onActivityResult(requestCode, resultCode, data);

        // first filter out the request
        // do this b/c an activity can instantiate lots of other activities
        // use this to specify WHICH data you are getting (bc there can be  A LOT)
        // would need to design a REQUEST CODE for each activity data
        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK){
                String value = data.getExtras().getString(VALUE_KEY);
                Log.d("demo", "Value received is: " +  value);
            } else if (resultCode == RESULT_CANCELED){
                Log.d("demo", "Didn't receive anything :( NULL received.");
            }
        }


    }
}
