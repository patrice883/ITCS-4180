package com.example.devansh.inclass06;

import java.io.Serializable;

/**
 * Created by devansh on 10/16/17.
 */

public class Article implements Serializable {
    String author, title, description, url, urlToImage, publishedAt;

    public Article() {

    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
