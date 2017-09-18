package itcs4180.alertdialogdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CharSequence[] items = {"Red", "Green", "Olive", "Poop", "Blue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // VARIOUS TYPES OF ALERT DIALOGS ~~~
        // This is so messy b/c I was rushed :'(!

        // ----------------------------------------------------------------------------------------------

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading...");

        // And then in the button on click method...:
        /*
            progress.show(); (will need to also declare the progress itself as final, because scope issue)
         */

        // ----------------------------------------------------------------------------------------------

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a color!")
                /* If you want multi choice items:
                .setMultiChoiceItems(..... blablabla parameters and stuff
                2nd parameter signifies which items are checked or not checked at the start

                The on click for this will also give you an "isChecked" parameter.
                ... new DialogInterface.OnMultiChoiceClickListener() { ... }
                 */

                // -1 (2nd parameter) means that nothing is preselected! You can put the index
                // of the item to be preselected
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("demo", items[i] + " was pressed!");
                    }
                })

                /*
                // Commented out to see radio buttons above ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                .setItems(items,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("demo", items[i] + " was pressed!");
                    }
                });
                */

                // Commented out for now to see the items dialog alert. ~~~~~~~~~~~~~~~~~~~~~~
                //.setMessage("Are you sure?") // Omg you can do many methods together this way. So cool!
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("demo", "Ok was pressed!");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("demo", "Cancelled!");
                    }
                })
                .setCancelable(false) // Setting this means that user cannot click on the side of the box
                                      // to cancel out >:)!!! They cannot escape!!
                ;

        final AlertDialog alert = builder.create();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When button is clicked, show alert dialog
                alert.show(); // but it's not happy :'(
                              // because it does not exist in this scope.
                // But if we change it to final, it works!
            }
        });
    }
}
