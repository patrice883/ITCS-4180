package itcs4180.startingactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = (EditText) findViewById(R.id.inputText);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String value = editText.getText().toString(); // user's input. get the value from that
                // control flow
                if(value == null || value.length() == 0){
                    // if user didn't enter anything
                    setResult(RESULT_CANCELED); //basically nothing happened... no result
                }
                else{
                    // 1. Need to create Intent first to hold the info to be passed back to the caller
                    Intent intent = new Intent(); // not targetting anything right now...
                                                  // basically to hold data. doesn't target specific activity. no action
                    intent.putExtra(MainActivity.VALUE_KEY, value);
                    setResult(RESULT_OK, intent); //RESULT_OK got a result c: didn't cancel. and intent = the actual result

                }
                finish(); // DONT FORGET THIS ;-;
            }
        });
    }
}
