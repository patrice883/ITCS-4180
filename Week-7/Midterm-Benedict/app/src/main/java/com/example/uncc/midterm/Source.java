package com.example.uncc.midterm;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by diondrelewis on 10/16/17.
 */

public class Source implements Serializable {
    String name, id, description, url, category, language, country;
    Url urlsToLogos;
    ArrayList<String> sortBysAvailable = new ArrayList<>();

    public Source(){

    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", urlsToLogos=" + urlsToLogos +
                ", sortBysAvailable=" + sortBysAvailable +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public Url getUrlsToLogos() {
        return urlsToLogos;
    }

    public ArrayList<String> getSortBysAvailable() {
        return sortBysAvailable;
    }
}
