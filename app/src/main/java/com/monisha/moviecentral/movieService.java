package com.monisha.moviecentral;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.monisha.moviecentralservice.movieCentralService;

import java.util.ArrayList;

import static com.monisha.moviecentral.Constants.DIRECTOR;
import static com.monisha.moviecentral.Constants.DIRECTOR_LIST;
import static com.monisha.moviecentral.Constants.BITMAP;
import static com.monisha.moviecentral.Constants.BITMAP_LIST;
import static com.monisha.moviecentral.Constants.TITLE;
import static com.monisha.moviecentral.Constants.TITLE_LIST;

public class movieService extends Service {

    movieDataProvider dataProvider = null;

    // Constructor for movie Service
    public movieService() {
        dataProvider = movieDataProvider.getInstance();

        // Start Foreground Service
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.setComponent(new ComponentName("com.monisha.movieclient", "com.monisha.movieclient.MainActivity"));
//        startForegroundService(intent);
    }


    // Helper Method
    private Bitmap getImageFromResource(int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }

    // Defenition of the AIDL Methods
    private final movieCentralService.Stub mBinder = new movieCentralService.Stub() {
        @Override
        public Bundle getmovieList() throws RemoteException {
            Bundle bundle = new Bundle();
            ArrayList<movie> movieList = dataProvider.getAllmovie();

            ArrayList<String> movieTitles = new ArrayList();
            ArrayList<String> moviedirectorNames = new ArrayList();
            ArrayList<Bitmap> movieThumbnails = new ArrayList();

            for(movie movieData: movieList) {
                movieTitles.add(movieData.getTitle());
                moviedirectorNames.add(movieData.getDirectorName());
                movieThumbnails.add(getImageFromResource(movieData.getThumbnail()));
            }

            bundle.putStringArrayList(TITLE_LIST, movieTitles);
            bundle.putStringArrayList(DIRECTOR_LIST, moviedirectorNames);
            bundle.putParcelableArrayList(BITMAP_LIST, movieThumbnails);

            return bundle;
        }

        // Method returns movie info for a particular movie, given the movie id
        @Override
        public Bundle getmovieInfo(int movieId) throws RemoteException {
            System.out.println("Querying movie info for: " + movieId);
            Bundle bundle = new Bundle();
            movie movieData = dataProvider.getmovieDetails(movieId);

            bundle.putString(TITLE, movieData.getTitle());
            bundle.putString(DIRECTOR, movieData.getDirectorName());
            bundle.putParcelable(BITMAP, getImageFromResource(movieData.getThumbnail()));

            return bundle;
        }

        // Given the movie id, method returns the url
        @Override
        public String getmovieUrl(int movieId) throws RemoteException {
            return dataProvider.getmovieDetails(movieId).getmovieUrl();
        }

        // Method returns Thumbnail image for the given movie id
        @Override
        public Bundle getmovieThumbnail(int movieId) throws RemoteException {
            Bundle bundle = new Bundle();
            int imgResId = dataProvider.getmovieDetails(movieId).getThumbnail();
            bundle.putParcelable(BITMAP, getImageFromResource(imgResId));
            return bundle;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}