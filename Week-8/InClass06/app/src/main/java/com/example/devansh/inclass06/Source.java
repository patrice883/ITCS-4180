package com.example.devansh.inclass06;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by devansh on 10/16/17.
 */

public class Source implements Serializable {
    String id, name, description, url, category, language, country;
    UrlsToLogos urlsToLogos;
    ArrayList<String> sortBysAvailable;

    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Source() {
    }
}
