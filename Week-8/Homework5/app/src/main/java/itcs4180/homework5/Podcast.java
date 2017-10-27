package itcs4180.homework5;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aileen on 10/26/2017.
 */

public class Podcast implements Serializable{

    String title, summary, releaseDate, updatedDate;
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
                "title='" + title + '\'' + "updateDate=" + updatedDate +
                '}' + "\n";
    }


}
