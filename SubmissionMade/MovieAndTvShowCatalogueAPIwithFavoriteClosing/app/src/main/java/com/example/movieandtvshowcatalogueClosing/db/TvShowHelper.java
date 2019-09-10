package com.example.movieandtvshowcatalogueClosing.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.DATE;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.ID;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.POPULARITY;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.POSTER;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.TABLE_NAME_TVSHOW_FAVORITE;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.TITLE;


public class TvShowHelper {

    private static final String DATABASE_TABLE_TVSHOW = TABLE_NAME_TVSHOW_FAVORITE;
    private final DatabaseHelper databaseHelper;
    private static TvShowHelper INSTANCE;

    private SQLiteDatabase database;

    private TvShowHelper(Context context){
        databaseHelper= new DatabaseHelper(context);
    }

    public static TvShowHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new TvShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public void open() throws SQLException {
        database = databaseHelper.getReadableDatabase();
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE_TVSHOW
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID+" ASC");
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE_TVSHOW,null
                , _ID+" = ?"
                , new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE_TVSHOW,null,values);
    }

    public int updateProvider(String id, ContentValues values){
        return database.update(DATABASE_TABLE_TVSHOW,values,_ID+" = ?",new String[]{id});
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE_TVSHOW,ID+ " = ?",new String[]{id});
    }

}
