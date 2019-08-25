package com.example.movieandtvshowcatalogue.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Movies.class,TvShows.class},version = 1,exportSchema = false)
public abstract class MyMoviesDataBase extends RoomDatabase {

    public abstract MyDao myDao();
    public abstract TvShowDao tvShowDao();
}
