package itcs4180.inclass04;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private int passCount = 1;
    private int passLength = 8;

    // Array of passwords gotten from the password generator ----------------------------------------
    private String[] results = {"A", "B", "C", "D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Update info when either seekbar is moved
        ((SeekBar)findViewById(R.id.seekPassCount)).setOnSeekBarChangeListener(this);
        ((SeekBar)findViewById(R.id.seekPassLength)).setOnSeekBarChangeListener(this);

        // AlertDialog for choosing the final Password ---------------------------------------------
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color!")
         */
        AlertDialog.Builder passwordPicker = new AlertDialog.Builder(this);
        passwordPicker.setTitle("Passwords")
                       .setSingleChoiceItems(results,-1, new DialogInterface.OnClickListener(){
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               Log.d("demo", results[i] + " was pressed!");

                               ((TextView)findViewById(R.id.txtShowPass)).setText(results[i]);
                           }
                       });
    }

    private void teeHeeSeek(SeekBar seekBar){

        // Separate based on which Seekbar is being used
        if(seekBar.getId() == R.id.seekPassCount){
            Log.d("test", "Pass Count Seekbar is being moved~");

            SeekBar seek = (SeekBar)findViewById(R.id.seekPassCount);
            TextView txt = (TextView)findViewById(R.id.txtShowCount);
            int min = 1;
            passCount = seek.getProgress() + min;

            txt.setText(passCount + "");
        }
        else if(seekBar.getId() == R.id.seekPassLength){
            Log.d("test", "Pass Length Seekbar is being moved~");

            SeekBar seek = (SeekBar)findViewById(R.id.seekPassLength);
            TextView txt = (TextView)findViewById(R.id.txtShowLength);
            int min = 8;
            passLength = seek.getProgress() + min;

            txt.setText(passLength + "");
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        teeHeeSeek(seekBar);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        teeHeeSeek(seekBar);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        teeHeeSeek(seekBar);
    }
}
