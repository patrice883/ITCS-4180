package itcs4180.intentsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnGoto2nd).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Intent needs a context and the class
                Intent intent = new Intent(MainActivity.this, SecondActivity.class); // explicit -> hard binding
                startActivity(intent); // starts it i guess?
            }
        });


        // Note... implicit - soft binding
        findViewById(R.id.btnGotoImplicitly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Specify the ACTION now in the implicit intent
                Intent intent = new Intent("itcs4180.intentsdemo.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT); // set category you want
                startActivity(intent);
            }
        });


    }
}
