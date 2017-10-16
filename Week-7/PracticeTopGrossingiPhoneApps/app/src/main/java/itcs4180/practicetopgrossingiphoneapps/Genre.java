package itcs4180.practicetopgrossingiphoneapps;

/**
 * Created by Aileen on 10/16/2017.
 */

class Genre {
    String genreId, name, url;

    public Genre() {
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId='" + genreId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
