package com.example.movieandtvshowcatalogueClosing.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "com.example.movieandtvshowcatalogueClosing.provider";
    private static final String SCHEME = "content";

    private DatabaseContract() {
    }


    public static final class FavoriteColumn implements BaseColumns {
        public static final String TABLE_NAME_MOVIES_FAVORITE = "moviesFavorite";
        public static final String TABLE_NAME_TVSHOW_FAVORITE = "tvshowFavorite";
        public static final String TITLE = "title";
        public static final String DATE = "release_date";
        public static final String POSTER = "poster_path";
        public static final String POPULARITY = "popularity";
        public static final String ID = "realId";

        public static final Uri CONTENT_URI_MOVIES = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME_MOVIES_FAVORITE)
                .build();

        public static final Uri CONTENT_URI_TVSHOW = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME_TVSHOW_FAVORITE)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }
}
