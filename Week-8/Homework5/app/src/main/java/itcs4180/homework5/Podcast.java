package itcs4180.homework5;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aileen on 10/26/2017.
 */

public class Podcast implements Comparable<Podcast>{

    String title, summary, releaseDate, updatedDate;
    Date rDate, uDate;
    String imageURlsmall, imageURLlarge;
    boolean color = false;

    public Podcast() {
    }

    ///////////////////////////////////////////////////////////////////////////
    // toString and compareTo()
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "Podcast{" +
                "title='" + title + '\'' + "date=" + releaseDate +
                '}' + "\n";
    }

    @Override
    public int compareTo(Podcast o) {

        Date one = null;
        Date two = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
            one = simpleDateFormat.parse(this.releaseDate);
            two = simpleDateFormat.parse(o.releaseDate);
        } catch (ParseException e) {
            Log.d("test-ERROR", "one date could not be parsed");
        }

        return one.compareTo(two);
    }

}
