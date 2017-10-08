package itcs4180.hw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Trivia extends AppCompatActivity {

    ArrayList<Question> questionData;

    // Layout Stuffies
    LinearLayout layout;
    TextView txtQuestion;
    ImageView imgPhoto;
    TextView txtNumber;
    TextView txtTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent() != null && getIntent().getExtras() != null){
            questionData = (ArrayList<Question>) getIntent().getExtras().get(MainActivity.QUESTIONS);
            Log.d("test", "We got question data!");

            // Retrieve all layout stuffies
            layout = (LinearLayout) findViewById(R.id.layoutOptions);
            txtQuestion = (TextView)findViewById(R.id.txtQuestion);
            imgPhoto = (ImageView)findViewById(R.id.imgPic);
            txtNumber = (TextView)findViewById(R.id.txtQNum);
            txtTimer = (TextView)findViewById(R.id.txtTime);

            // Call to update display
            updateDisplay(0);
        }

    }

    public void updateDisplay(int index) {
        Log.d("test", "We called updateDisplay()");
        Question question = questionData.get(index);
        Log.d("test", "We got the specific question object");

        // Set Question Text
        txtQuestion.setText((question.getQuestion()));
        Log.d("test", "We set the question text");

        // Get Options
        String[] options = question.getAnswerChoices();

        int i = 0;
/*
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (layout.getWidth(), layout.getHeight());
        params.setMargins(25,10,25,10);

        TextView text = new TextView(this); // Option
        text.setId(View.generateViewId());
        text.setLayoutParams(params);
        text.setText(options[i]);
        text.setTextSize(16);
        // set on click listener
        layout.addView(text);

*/
    }

} // class
