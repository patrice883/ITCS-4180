package itcs4180.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PodcastDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_details);

        if(getIntent() != null){

            Podcast podcast = (Podcast) getIntent().getExtras().get("PODCAST");
            ((TextView) findViewById(R.id.txtpodTitle)).setText(podcast.title);
            ((TextView) findViewById(R.id.txtSummary)).setText(podcast.summary);
            if(podcast.imageURLlarge.equals("") || podcast.imageURLlarge == null){
                Log.d("Test", "Null image.");
            }else
                Picasso.with(this).load(podcast.imageURLlarge).into((ImageView) findViewById(R.id.imageBig));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            Log.d("test-Details", "Podcast Updated Date: " + podcast.updatedDate);

            Date date = null;
            try {
                date = format.parse(podcast.updatedDate);
                Log.d("test-Details", "Date : " + date.toString());

                format.applyPattern("MM/dd/YYYY h:mm a");

                String d = format.format(date);
                Log.d("test-Details", "Date : " + d);
                ((TextView) findViewById(R.id.txtDate)).setText("Updated on: " + d);
            } catch (ParseException e) {
                Log.d("test-Details", "We are getting some error");
            }


        }
    }
}
