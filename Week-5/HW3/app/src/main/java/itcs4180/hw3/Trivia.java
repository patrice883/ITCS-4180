package itcs4180.hw3;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Trivia extends AppCompatActivity {

    ArrayList<Question> questionData;

    // Layout Stuffies
    private LinearLayout layout;
    private TextView txtQuestion;
    private ImageView imgPhoto;
    private TextView txtNumber;
    private TextView txtTimer;
    private TextView[] txtOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);


        if(getIntent() != null && getIntent().getExtras() != null){
            questionData = (ArrayList<Question>) getIntent().getExtras().get(MainActivity.QUESTIONS);
            Log.d("test", "We got question data!");

            // Retrieve all layout stuffies
            layout = (LinearLayout) findViewById(R.id.layoutOptions);
            //layout.setLayoutParams(new RelativeLayout.LayoutParams
            //        (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            txtQuestion = (TextView)findViewById(R.id.txtQuestion);
            imgPhoto = (ImageView)findViewById(R.id.imgPic);
            txtNumber = (TextView)findViewById(R.id.txtQNum);
            txtTimer = (TextView)findViewById(R.id.txtTime);

            // Call to update display
            updateDisplay(0);
        }

    } // end onCreate()

    public void updateDisplay(int index) {
        Log.d("test", "We called updateDisplay()");
        Question question = questionData.get(index);
        Log.d("test", "We got the specific question object");

        // Set Question Number
        txtNumber.setText("Q" + (index + 1));

        // Set Question Text
        txtQuestion.setText((question.getQuestion()));
        Log.d("test", "We set the question text");

        // Get Options
        String[] options = question.getAnswerChoices();
        txtOptions = new TextView[options.length];
        Log.d("test", "Options are: " + Arrays.toString(options));

        float x = layout.getX();
        float y = layout.getY();
        for(int i = 0; i < txtOptions.length; i++, y += 200) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins (25, 10, 25, 10);

            txtOptions[i] = new TextView(this); // Option
            txtOptions[i].setId(View.generateViewId());
            txtOptions[i].setLayoutParams(params);
            txtOptions[i].setX(x);
            txtOptions[i].setY(y);

            txtOptions[i].setText(options[i]);
            txtOptions[i].setTextSize(16);
            // set on click listener
            layout.addView(txtOptions[i]);
        }


    }

} // class
