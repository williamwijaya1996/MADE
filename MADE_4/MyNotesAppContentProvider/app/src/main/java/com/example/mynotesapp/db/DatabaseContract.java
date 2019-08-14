package com.example.mynotesapp.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import static android.provider.ContactsContract.AUTHORITY;
import static android.service.notification.Condition.SCHEME;

public class DatabaseContract {

    static String TABLE_NOTE = "note";
    public static final class NoteColumns implements BaseColumns {
        public static final String TABLE_NAME = "note";
       public static String TITLE = "title";
       public static String DESCRIPTION = "description";
       public static String DATE = "date";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
