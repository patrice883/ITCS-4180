package itcs4180.module2prepwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        Log.d("demo", "Hello world!");
        Log.w("demo", "Hi! This is a WARNINGGGGGGGGGGGGGGGG");

        String s = getResources().getString(R.string.are_you_sure);
        Log.d("demo", s);

        String colors[] = getResources().getStringArray(R.array.colors);
        for(String c : colors){
            Log.d("demo", c);
        }

        // Get Button info
        Button btn = (Button) findViewById(R.id.btnOk);
        Log.d("demo", "Button text is: " + btn.getText().toString());

        // EVENT LISTENERS!!
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnOther = (Button) findViewById(R.id.btnOther);
                if(btnOther.getVisibility() == View.VISIBLE){
                    btnOther.setVisibility(View.INVISIBLE);
                }
                else{
                    btnOther.setVisibility(View.VISIBLE);
                }
                Log.d("demo", "OK button was clicked omg!!");
            }
        });

        // calls this onClick method instead of creating a new one like above
        findViewById(R.id.btnCancel).setOnClickListener(this);

    } // end OnCreate

    // Another way to do OnClick thing
    @Override
    public void onClick(View v) {
        Log.d("demo", "Some button was clicked!!!!!");
    }

    // YET ANOTHER way to do OnClick thing c: (also see the activity_main_new.xml text
    // for btnCancel)
    public void otherButtonClick(View v){
        Log.d("demo", "Other button clicked!");
        Button btnOther = (Button) findViewById(R.id.btnCancel);
        if(btnOther.getVisibility() == View.VISIBLE){
            btnOther.setVisibility(View.INVISIBLE);
        }
        else{
            btnOther.setVisibility(View.VISIBLE);
        }
    }
}
