package com.monisha.moviecentral;

public class movie {
    String title;
    String directorName;
    String movieUrl;
    int thumbnail;

    // Constructor for movie Object
    public movie(String title, String directorName, String movieUrl, int thumbnail) {
        this.title = title;
        this.directorName = directorName;
        this.movieUrl = movieUrl;
        this.thumbnail = thumbnail;
    }

    // Getter Methods:
    public String getTitle() {
        return title;
    }

    public String getmovieUrl() {
        return movieUrl;
    }

    public String getDirectorName() {
        return directorName;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
