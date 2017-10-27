package itcs4180.homework5;

/**
 * Created by Aileen on 10/26/2017.
 */

public class Podcast {

    String title, summary, releaseDate, updatedDate;
    String imageURlsmall, imageURLlarge;

    public Podcast() {
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", imageURlsmall='" + imageURlsmall + '\'' +
                ", imageURLlarge='" + imageURLlarge + '\'' +
                '}';
    }
}
