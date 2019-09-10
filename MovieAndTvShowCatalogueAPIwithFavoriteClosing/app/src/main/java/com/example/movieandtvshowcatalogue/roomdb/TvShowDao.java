package com.example.movieandtvshowcatalogue.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TvShowDao {

    @Insert
    public void addTvShow(TvShows tvShows);

    @Query("select * from tvShows")
    public List<TvShows> getTvshows();

    @Delete
    public void deleteTvShows(TvShows tvShows);
}
