package itcs4180.parampassingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle("Second Activity");

        if(getIntent() != null && getIntent().getExtras () != null){
            /*
            Toast.makeText(this, getIntent().getExtras().getString(MainActivity.NAME_KEY),
                    Toast.LENGTH_SHORT).show();
             */

            // Serializable
            /*
            User user = (User) getIntent().getExtras().getSerializable(MainActivity.USER_KEY);
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
            */

            // Parcel
            Person person = getIntent().getExtras().getParcelable(MainActivity.PERSON_KEY);
            Toast.makeText(this, person.toString(), Toast.LENGTH_SHORT).show();

        }
    }
}
