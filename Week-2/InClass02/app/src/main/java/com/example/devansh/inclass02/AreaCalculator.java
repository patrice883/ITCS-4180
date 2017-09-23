package com.example.devansh.inclass02;
/**
 * Assignment 2 - InClass02
 * File Name: AreaCalculator.java
 * Full name: Devansh Desai and Aileen Benedict
 *
 * Pseudocode (kind of.. mostly just planning notes):
 * - Get info from the shape button the user selected.
 *      -> When a particular shape button is selected, store the shape name in variable
 *      -> If applicable, hide the "Length2" text thingy (for square of circle)
 *      -> Update text view to show name of shape (other than "Select a shape pleaseee")
 *
 * - Calculate the area IF user clicks CalculateButton AND has inputted required numericoaisdfil values
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AreaCalculator extends AppCompatActivity implements View.OnClickListener {

    // Hold the user's current shape selection
    private String selection = "none";
    private int msgCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        findViewById(R.id.imgBtnTriangle).setOnClickListener(this);
        findViewById(R.id.imgBtnCircle).setOnClickListener(this);
        findViewById(R.id.imgBtnSquare).setOnClickListener(this);



    }

    public void clrButtonClick(View v){
        selection = "none";

        // Set Length 2 stuff to visible
        (findViewById(R.id.txtLength2)).setVisibility(View.VISIBLE);
        (findViewById(R.id.inputLength2)).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.txtAnswer)).setText("");
        ((EditText) findViewById(R.id.inputLength1)).setText("");
        ((EditText) findViewById(R.id.inputLength2)).setText("");

        ((TextView) findViewById(R.id.txtShape)).setText("Select a shape");
        msgCounter = 0;

    }

    // Click thing for Shape Buttons
    public void onClick(View v){

        TextView abc = (TextView) findViewById(R.id.txtShape);


        if(v.getId() == R.id.imgBtnTriangle) {
            Log.d("test", "Triangle was Clicked");

            // Set selection based on shape
            selection = "Triangle";

            // Set Length 2 stuff to visible
            (findViewById(R.id.txtLength2)).setVisibility(View.VISIBLE);
            (findViewById(R.id.inputLength2)).setVisibility(View.VISIBLE);
        }
        else if(v.getId() == R.id.imgBtnCircle) {
            Log.d("test", "Circle was Clicked");
            ((EditText) findViewById(R.id.inputLength2)).setText("");
            // Set selection based on shape
            selection = "Circle";

            // Set Length 2 stuff to invisible
            (findViewById(R.id.txtLength2)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.inputLength2)).setVisibility(View.INVISIBLE);
        }
        else if(v.getId() == R.id.imgBtnSquare) {
            Log.d("test", "Square was Clicked");
            ((EditText) findViewById(R.id.inputLength2)).setText("");
            // Set selection based on shape
            selection = "Square";

            // Set Length 2 stuff to invisible
            (findViewById(R.id.txtLength2)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.inputLength2)).setVisibility(View.INVISIBLE);
        }
        abc.setText(selection);
    }


    public void calculateButtonClick(View v){
        Log.d("test", "spacecows will rule the world. jk calc button was pressed.");

        EditText num1view = (EditText) findViewById(R.id.inputLength1);
        EditText num2view = (EditText) findViewById(R.id.inputLength2);
        TextView abc = (TextView) findViewById(R.id.txtAnswer);
        double area = 0;

        if(selection.equals("Triangle")){


            Log.d("test", String.valueOf(num1view.getText())+" => Num1  ");
            Log.d("test", num2view.getText() +" => Num2  ");

            if(!TextUtils.isEmpty(num1view.getText()) && !TextUtils.isEmpty(num2view.getText())) {
                Log.d("test", String.valueOf(num1view.getText()));
                Log.d("test", String.valueOf(num2view.getText()));
                area = (Double.parseDouble("" + num1view.getText())) * (Double.parseDouble("" + num2view.getText())) * 0.5;
                abc.setText(area + "");
            }
            else {
                Toast.makeText(this,"Please enter values for Length 1 and Length 2",Toast.LENGTH_SHORT).show();
            }

        }else if(selection.equals("Circle")){
            if(!TextUtils.isEmpty(num1view.getText())) {
                Log.d("test", String.valueOf(num1view.getText()));
                area = (Double.parseDouble(String.valueOf(num1view.getText()))) * (Double.parseDouble(String.valueOf(num1view.getText()))) * Math.PI;
                abc.setText(area + "");
            }
            else {
                Toast.makeText(this,"Please enter values for Length 1",Toast.LENGTH_SHORT).show();
            }

        }else if(selection.equals("Square")){
            if(!TextUtils.isEmpty(num1view.getText())) {
                Log.d("test", String.valueOf(num1view.getText()));
                area = (Double.parseDouble(String.valueOf(num1view.getText()))) * (Double.parseDouble(String.valueOf(num1view.getText())));
                abc.setText(area + "");
            }
            else {
                Toast.makeText(this,"Please enter values for Length 1",Toast.LENGTH_SHORT).show();
            }

        }else{
            Log.d("test", "No selection made but calculate button pressed");
            if(msgCounter < 5) {
                ((TextView) findViewById(R.id.txtShape)).setText("Please Select a Shape First!");
            }
            else if(msgCounter < 10){
                ((TextView) findViewById(R.id.txtShape)).setText("Come on already. Select a shape!!");
            }
            else{
                ((TextView) findViewById(R.id.txtShape)).setText("Pick a shape!!!! >:(. Its been " + msgCounter + " times already!!");
            }
            msgCounter++;
        }


    }
}
