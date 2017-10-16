package itcs4180.practicetopgrossingiphoneapps;

import java.util.ArrayList;

/**
 * Created by Aileen on 10/16/2017.
 */

class Result {
    String artistUrl, artistId, artistName, artworkUrl100, copyright;
    ArrayList<Genre> genres;
    String id, kind, name, releaseDate, url;

    /** Default Constructor */
    public Result(){

    }

    @Override
    public String toString() {
        return "Result{" +
                "\nartistUrl='" + artistUrl + '\'' +
                ", artistId='" + artistId + '\'' +
                ", artistName='" + artistName + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", copyright='" + copyright + '\'' +
                ", genres=" + genres +
                ", id='" + id + '\'' +
                ", kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
