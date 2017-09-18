package itcs4180.module3prep;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // -----------------------------------------------------------------------------------------
        // Video 2 ----- Creating Dynamic Layouts --------------------------------------------------
        // -----------------------------------------------------------------------------------------

        // Programatically create the layout
        RelativeLayout relativeLayout = new RelativeLayout(this);
        // Relative layout takes two parameters: width and height. We are setting them both to MATCH_PARENT here
        // ........ am I doing this right?
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        // QUESTION ------------------> In the video, these were just LayoutParams.MATCH_PARENT,
        //                              but it wouldn't let me do this. Had to have Something.LayoutParams...

        setContentView(relativeLayout);

        // Create the TextView ---------------------------------------------------------------------
        TextView textView = new TextView(this);
        textView.setText(R.string.hello_world);
        textView.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setPadding(100,50,20,10);
        textView.setId(100); // Setting the textID

        // Associate the TextView with the Relative Layout now by using addView() method
        relativeLayout.addView(textView);

        // Adding a button now! --------------------------------------------------------------------
        Button button = new Button(this);
        button.setText(R.string.click_button);
        // Create button layout (used so we can make button below the textView... use addRule() method)
        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonLayoutParams.addRule(RelativeLayout.BELOW, textView.getId());

        // Add button to relativeLayout now. make sure to ALSO associate the buttonLayoutParams with it
        // Otherise, it won't be noticed.
        relativeLayout.addView(button, buttonLayoutParams);

        // Remove button view (if we wanted to) ----------------------------------------------------
        //relativeLayout.removeView(button);

        Log.d("test", relativeLayout.getChildCount() + "");
        //relativeLayout.getChildCount(); <- this gets the child count??
        // If we remove button, childCount = 1
        // If we don't remove it, childCount = 2



    }
}
