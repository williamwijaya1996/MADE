package com.example.movieandtvshowcatalogueClosing.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
