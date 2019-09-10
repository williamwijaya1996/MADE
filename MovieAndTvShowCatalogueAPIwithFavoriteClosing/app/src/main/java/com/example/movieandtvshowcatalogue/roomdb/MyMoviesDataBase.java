package com.example.movieandtvshowcatalogue.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Movies.class,TvShows.class},version = 1,exportSchema = false)
public abstract class MyMoviesDataBase extends RoomDatabase {

    @SuppressWarnings("WeakerAccess")
    public abstract MyDao myDao();

    @SuppressWarnings("WeakerAccess")
    public abstract TvShowDao tvShowDao();


}
