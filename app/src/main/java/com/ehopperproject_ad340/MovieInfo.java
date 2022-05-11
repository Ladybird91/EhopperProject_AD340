package com.ehopperproject_ad340;

import org.jetbrains.annotations.Nullable;

public class MovieInfo {

    private final String title;
    private final String year;
    private final String director;
    private final String image;
    private final String description;


    public MovieInfo(String title, String year, String director, String image, String description) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.image = image;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

}
