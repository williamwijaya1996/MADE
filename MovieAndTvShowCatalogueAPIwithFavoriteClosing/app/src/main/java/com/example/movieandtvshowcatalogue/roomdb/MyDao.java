package com.example.movieandtvshowcatalogue.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addMovies(Movies movies);

    @Query("select * from movies")
    public List<Movies> getMovies();

    @Delete
    public void deleteMovies(Movies movies);


}


