package com.example.devansh.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{


    private int weight;
    private double gender = 0.55; // gender constant. 0.68 for Male, 0.55 for Female
    private double bac = 0.0;

    private int saved = 0, added = 0; // # of times the save button was clicked

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((SeekBar) findViewById(R.id.seekAlcoholPercent)).setOnSeekBarChangeListener(this);


    }

    /* setError message used for Part 2 Question 1:
     * The BAC level cannot be calculated without a weight and gender value.
     * If a user does not enter in and save a weight value and tries to add a drink, use the setError()
     * method to display an error message informing the user to “Enter the weight in lbs.”
     *
     * @return false if there is an error, true otherwise
     */
    public boolean setError(){
        EditText a = (EditText) findViewById(R.id.txtEditWeight);

        if(TextUtils.isEmpty(a.getText())){
            Toast.makeText(this, "Enter the weight in lbs.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!(Integer.parseInt(a.getText() + "")>0)){
            Toast.makeText(this, "Must have a weight larger than 0.", Toast.LENGTH_SHORT).show();
            ((EditText) findViewById(R.id.txtEditWeight)).setText("");
            return false;
        }
        return true;
        /*
        if(weight == -2) {
            Toast.makeText(this, "You need to Enter and Save your Weight in lbs!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(weight == 0){
            Toast.makeText(this, "Your weight should be greater than 0. Please Enter and Save Weight in lbs!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
        */
    }

    //Saves the current weight and gender. User cannot be smart .. hehe
    public void onSaveButtonClick(View v){
        Log.d("test", "We are now Saving stuff into this profile");

        EditText a = (EditText) findViewById(R.id.txtEditWeight);
        Switch x = (Switch) findViewById(R.id.switchChooseGender);
        double A = bac * weight * gender / 6.24;
        // May only continue IF there was no error (hence setError() returns true)

        if(setError()){
            weight = Integer.parseInt(a.getText() + "");
            gender = x.isChecked()? 0.68 : 0.55;

            Log.d("test", "Weight : " + weight);
            Log.d("test", "Gender constant : " + gender);
            ((EditText) findViewById(R.id.txtEditWeight)).setText("" + weight);
            saved++;
        }

        if(added >=1)
            calculateBAC(A);

    }


    /*
     * Calculate BAC Level using the following:
     * “Widmark BAC Formula:” % BAC = (A x 6.24 / (W x r))
     * A = liquid ounces of alcohol consumed = ounces * alcohol percentage (i.e. 5 x .15)
     * W = a person’s weight in pounds
     * r = a gender constant of alcohol distribution (.68 for men and .55 for women)
     */
    public void onAddDrinkButtonClick(View v){
        //Sends Error messages to the users if something wrong or else calculates and
        // updates the BAC values
       if(saved < 1){
            Toast.makeText(this, "Please make sure you have saved your weight and gender first.", Toast.LENGTH_SHORT).show();
        }
        else if(setError() && weight > 0){
            Log.d("test", "We are now Adding a Drink");

            calculateBAC();

        }

        int x = ((ProgressBar) findViewById(R.id.pbBACLevel)).getProgress();
        Log.d("test2", "The BAC progress is :" + x);
        String output = x < 8? "You\'re safe" : x < 20? "Be Careful...": "Over the limit!";
        ((TextView)findViewById(R.id.txtResultStatus)).setText("" + output);

    }

    public void calculateBAC(){
        // We should already have weight and gender in variables, gained from
        // when the user clicked the SAVE button.
        // Now we need OUNCES and ALCOHOL %
        int ounces;
        int percent;

        // Get ounces
        RadioGroup rg = (RadioGroup) findViewById(R.id.radGroupDrinkSize);
        if(rg.getCheckedRadioButtonId() == R.id.radOne){
            ounces = 1;
        }
        else if(rg.getCheckedRadioButtonId() == R.id.radFive){
            ounces = 5;
        }
        else{
            ounces = 12;
        }
        Log.d("test", "Drink size is " + ounces);

        // Get alcohol percentage
        SeekBar sb = (SeekBar) findViewById(R.id.seekAlcoholPercent);
        percent = sb.getProgress();
        Log.d("test", "Alcohol percent is %" + percent);

        //“Widmark BAC Formula:” % BAC = (A x 6.24 / (W x r))
        // Calculate BAC level for current drink
        double currentBAC = (((ounces * percent) * 6.24) / (weight * gender))/100;
        Log.d("test", "Current BAC level is: " + currentBAC);

        // Add on to existing total BAC
        bac += currentBAC;
        Log.d("test", "Total BAC level is now: " + bac);

        // Update the displayed text
        NumberFormat numberFormat = new DecimalFormat("0.##");
        ((TextView) findViewById(R.id.txtBACLevel)).setText("BAC Level: " + numberFormat.format(bac));

        // Update the progress bar
        ((ProgressBar) findViewById(R.id.pbBACLevel)).setProgress((int)Math.round(bac * 100));
        added++;
        if(bac >= 0.25){

            findViewById(R.id.btnSave).setEnabled(false);
            findViewById(R.id.btnAddDrink).setEnabled(false);
            Toast.makeText(this,"No more drinks for you.",Toast.LENGTH_SHORT).show();
        }
    }

    //Part 2 Question 7: Resets Everything
    public void onResetButtonClick(View v){
        Log.d("test", "We are now Resetting Everything");
        weight = 0;
        gender = .55;
        bac = 0;
        saved = 0;
        added = 0;
        findViewById(R.id.btnSave).setEnabled(true);
        findViewById(R.id.btnAddDrink).setEnabled(true);
        ((EditText) findViewById(R.id.txtEditWeight)).setText("");
        ((Switch)findViewById(R.id.switchChooseGender)).setChecked(false);
        ((RadioButton)findViewById(R.id.radOne)).setChecked(true);
        ((SeekBar)findViewById(R.id.seekAlcoholPercent)).setProgress(5);
        ((TextView)findViewById(R.id.txtResultStatus)).setText("You're safe");
        ((TextView)findViewById(R.id.txtBACLevel)).setText("BAC Level : 0.00");
        ((ProgressBar) findViewById(R.id.pbBACLevel)).setProgress(0);
    }

    public void calculateBAC(double A){

        bac = (A * 6.24)/ (weight * gender);
        Log.d("test", "Total BAC level is now: " + bac);

        // Update the displayed text
        NumberFormat numberFormat = new DecimalFormat("0.##");
        ((TextView) findViewById(R.id.txtBACLevel)).setText("BAC Level: " + numberFormat.format(bac));

        // Update the progress bar
        ((ProgressBar) findViewById(R.id.pbBACLevel)).setProgress((int)Math.round(bac * 100));
        added++;
        String output = bac < 0.8? "You\'re safe" : bac < 0.20? "Be Careful...": "Over the limit!";
        ((TextView)findViewById(R.id.txtResultStatus)).setText("" + output);
        if(bac >= 0.25){

            findViewById(R.id.btnSave).setEnabled(false);
            findViewById(R.id.btnAddDrink).setEnabled(false);
            Toast.makeText(this,"No more drinks for you.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v){
        Log.d("test", "Some Random Button Clicked!! BUGGGG");
    }


    //----------------------------------------------------------------------------------------------
    //This part is for seekbar and updating the value next to it
    //These are the method implementations of the interface Seeker.onSeekBarChangeListener
    @Override
    public void onStartTrackingTouch(SeekBar seekBar){
        Log.d("test", "seekbar was clicked");
        SeekBar one = (SeekBar) findViewById(R.id.seekAlcoholPercent);
        int p = one.getProgress();
        ((TextView) findViewById(R.id.txtAlcoholPercent)).setText(p + "%");
    }
    @Override
    public void onProgressChanged (SeekBar seekBar, int progresValue, boolean fromUser){
        Log.d("test", "seekbar is changing");
        SeekBar one = (SeekBar) findViewById(R.id.seekAlcoholPercent);
        int p = one.getProgress();
        ((TextView) findViewById(R.id.txtAlcoholPercent)).setText(p + "%");
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar){
        Log.d("test", "seekbar was stopped to touch");
        SeekBar one = (SeekBar) findViewById(R.id.seekAlcoholPercent);
        int p = one.getProgress();
        ((TextView) findViewById(R.id.txtAlcoholPercent)).setText(p + "%");
    }
    //----------------------------------------------------------------------------------------------
}
