package com.example.movieandtvshowcatalogueClosing.helper;

import android.content.Intent;
import android.database.Cursor;

import com.example.movieandtvshowcatalogueClosing.db.DatabaseContract;
import com.example.movieandtvshowcatalogueClosing.model.MoviesFavorite;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<MoviesFavorite> mapCursorToArrayList(Cursor moviesCursor) {
        ArrayList<MoviesFavorite> moviesList = new ArrayList<>();
        while (moviesCursor.moveToNext()) {
            int id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.ID));
            String title = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.TITLE));
            String release_date = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.DATE));
            String poster_path = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POSTER));
            Double popularity = moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POPULARITY));
            int realId = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.ID));
            moviesList.add(new MoviesFavorite(id, title, release_date, poster_path,popularity,realId));
        }
        return moviesList;
    }
}
