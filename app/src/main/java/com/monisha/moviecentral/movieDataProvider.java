package com.monisha.moviecentral;

import java.util.ArrayList;

public class movieDataProvider {
    static movieDataProvider mSingleton = null;
    private ArrayList<com.monisha.moviecentral.movie> mmovieList = new ArrayList<com.monisha.moviecentral.movie>();

    // Implements singleton design pattern
    public static movieDataProvider getInstance() {
        if(mSingleton == null) {
            mSingleton = new movieDataProvider();
        }
        return mSingleton;
    }

    // Cosntructor method which poppulates all the values
    private movieDataProvider() {

        mmovieList.add(new movie("movie 1",
                "Richard",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                R.drawable.ic_launcher_background));
        mmovieList.add(new movie("movie 2",
                "Thomas",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                R.drawable.ic_launcher_background));
        mmovieList.add(new movie("movie 3",
                "Abraham",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                R.drawable.ic_launcher_background));
        mmovieList.add(new movie("movie 4",
                "Rahul",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                R.drawable.ic_launcher_background));
        mmovieList.add(new movie("movie 5",
                "Amanda",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                R.drawable.ic_launcher_background));
    }

    // Getter Methods
    public movie getmovieDetails(int index) {
        return mmovieList.get(index);
    }

    public String getmovieUrl(int index) {
        return mmovieList.get(index).getmovieUrl();
    }

    public ArrayList<movie> getAllmovie() {
        return mmovieList;
    }
}
