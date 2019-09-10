package com.example.movieandtvshowcatalogueClosing.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movies.class,TvShows.class},version = 1,exportSchema = false)
public abstract class MyMoviesDataBase extends RoomDatabase {

    @SuppressWarnings("WeakerAccess")
    public abstract MyDao myDao();

    @SuppressWarnings("WeakerAccess")
    public abstract TvShowDao tvShowDao();


}
