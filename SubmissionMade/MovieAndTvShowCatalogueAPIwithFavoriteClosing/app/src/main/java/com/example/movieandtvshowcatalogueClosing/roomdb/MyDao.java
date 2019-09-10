package com.example.movieandtvshowcatalogueClosing.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


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


