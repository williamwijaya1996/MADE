package com.example.movieandtvshowcatalogueClosing.roomdb;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DaoFavoriteMovies {

    @Query("select * from movies")
    Cursor selectAll();
}
