package itcs4180.parampassingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static String NAME_KEY = "NAME"; // create our name key

    // Serializable
    static String USER_KEY = "USER";

    // Parcelable
    static String PERSON_KEY = "PERSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Main Activity");

        findViewById(R.id.btnGoTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                /*
                // Sending value?
                // normal put extra / data passing ------------------------------------------
                intent.putExtra(NAME_KEY, "Bob Smith");

                // Create User
                User user = new User("Alice Smith", 25.5);
                // Using Serializable --------------------------------------------------
                intent.putExtra(USER_KEY,user); // b/c user is something that is serializable
                */

                // Using Parcel ---------------------------------------------------
                intent.putExtra(PERSON_KEY, new Person("Alice", 20.3, "Charlotte"));


                startActivity(intent);
            }
        });
    }
}
