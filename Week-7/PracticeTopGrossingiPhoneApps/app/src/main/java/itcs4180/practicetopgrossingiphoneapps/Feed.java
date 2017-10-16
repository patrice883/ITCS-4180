package itcs4180.practicetopgrossingiphoneapps;

import java.util.ArrayList;

/**
 * Created by Aileen on 10/16/2017.
 */

public class Feed {
    String title, id;
    Author author;
    ArrayList<Link> links;
    String copyright, country, icon, updated;
    ArrayList<Result> results;

    public Feed() {

    }

    @Override
    public String toString() {
        return "Feed{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", author=" + author +
                ", links=" + links +
                ", copyright='" + copyright + '\'' +
                ", country='" + country + '\'' +
                ", icon='" + icon + '\'' +
                ", updated='" + updated + '\'' +
                ", results=" + results +
                '}';
    }


    ///////////////////////////////////////////////////////////////////////////
    // Getters
    ///////////////////////////////////////////////////////////////////////////

    public ArrayList<Result> getResults() {
        return results;
    }
}
