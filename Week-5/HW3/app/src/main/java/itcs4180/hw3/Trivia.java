package itcs4180.hw3;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Trivia extends AppCompatActivity {

    ArrayList<Question> questionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        if(getIntent() != null && getIntent().getExtras() != null){
            questionData = (ArrayList<Question>) getIntent().getExtras().get(MainActivity.QUESTIONS);
            updateDisplay(0);
        }

    }

    private void updateDisplay(int index) {
        Question question = questionData.get(index);

        // Set Question Text
        ((TextView)findViewById(R.id.txtQuestion)).setText(question.getQuestion());

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintTrivia);


        // create the layout params that will be used to define how your
        // button will be displayed
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
        //        (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Set up Linear Layout to start adding the answer choices
        String[] answerChoice = question.getAnswerChoices();
        TextView[] options = new TextView[answerChoice.length];
        //for(int i = 0; i < answerChoice.length; i++) {

            //LinearLayout ll = new LinearLayout(this);
            //ll.setOrientation(LinearLayout.HORIZONTAL);
        //for(int i = 0; i < options.length; i++) {
        int i = 0;
            options[i] = new TextView(this);
            options[i].setId(View.generateViewId());    // Set id
            options[i].setText(answerChoice[i]);        // Set text
            //options[i].setBackgroundResource(R.color.options);
            //options[i].setPadding(5,5,5,5);
            options[i].setTextSize(18);                 // Set text size

            options[i].setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);

        // Set constraints (I think)
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        set.connect(options[i].getId(),ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.connect(options[i].getId(),ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        if(i != 0){
            set.connect(options[i].getId(), ConstraintSet.TOP, options[i-1].getId(), ConstraintSet.TOP, 0);
        }
        set.applyTo(layout);

        layout.addView(options[i]);
        //}

    }

}
