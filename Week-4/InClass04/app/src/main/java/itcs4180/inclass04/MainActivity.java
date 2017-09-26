package itcs4180.inclass04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private int passCount = 1;
    private int passLength = 8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Update info when either seekbar is moved
        ((SeekBar)findViewById(R.id.seekPassCount)).setOnSeekBarChangeListener(this);
        ((SeekBar)findViewById(R.id.seekPassLength)).setOnSeekBarChangeListener(this);

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
