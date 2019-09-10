package com.example.movieandtvshowcatalogueClosing.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperMovies extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbMoviesandTvShow";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIES = String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL);",
            DatabaseContract.FavoriteColumn.TABLE_NAME_MOVIES_FAVORITE,
            DatabaseContract.FavoriteColumn._ID,
            DatabaseContract.FavoriteColumn.TITLE,
            DatabaseContract.FavoriteColumn.DATE,
            DatabaseContract.FavoriteColumn.POSTER,
            DatabaseContract.FavoriteColumn.POPULARITY,
            DatabaseContract.FavoriteColumn.ID);

    DatabaseHelperMovies(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.FavoriteColumn.TABLE_NAME_MOVIES_FAVORITE);
        onCreate(db);
    }
}
