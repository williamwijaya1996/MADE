package com.example.movieandtvshowcatalogueClosing.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.movieandtvshowcatalogueClosing.model.MoviesFavorite;
import com.example.movieandtvshowcatalogueClosing.roomdb.Movies;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.DATE;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.ID;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.POPULARITY;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.POSTER;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.TABLE_NAME_MOVIES_FAVORITE;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.TITLE;

public class MoviesHelper {

    private static final String DATABASE_TABLE_MOVIES = TABLE_NAME_MOVIES_FAVORITE;
    private final DatabaseHelperMovies databaseHelperMovies;
    private static MoviesHelper INSTANCE;

    private SQLiteDatabase database;

            private MoviesHelper(Context context){
                databaseHelperMovies = new DatabaseHelperMovies(context);
            }

            public static MoviesHelper getInstance(Context context){
                if(INSTANCE == null){
                    synchronized (SQLiteOpenHelper.class){
                        if(INSTANCE==null){
                            INSTANCE=new MoviesHelper(context);
                        }
                    }
                }
                return INSTANCE;
            }

            public void open() throws SQLException{
                database = databaseHelperMovies.getReadableDatabase();
            }

            public void close(){
                databaseHelperMovies.close();

                if(database.isOpen())
                    database.close();
            }

            public ArrayList<MoviesFavorite> query(){
                ArrayList<MoviesFavorite> arrayList = new ArrayList<>();
                Cursor cursor = database.query(DATABASE_TABLE_MOVIES,
                        null,
                        null,
                        null,
                        null,
                        null,
                        _ID+" DESC",
                        null);

                cursor.moveToFirst();
                MoviesFavorite movies;
                if(cursor.getCount()>0){
                    do{
                        movies = new MoviesFavorite();
                        movies.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                        movies.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                        movies.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                        movies.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(POPULARITY)));
                        movies.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));

                        arrayList.add(movies);
                        cursor.moveToNext();
                    }while (!cursor.isAfterLast());
                }
                cursor.close();
                return arrayList;
            }

            public long insert(Movies movies){
                ContentValues initValues = new ContentValues();
                initValues.put(TITLE,movies.getTitle());
                initValues.put(DATE,movies.getRelease_date());
                initValues.put(POSTER,movies.getPoster_path());
                initValues.put(POPULARITY,movies.getPopularity());


                return database.insert(DATABASE_TABLE_MOVIES,null,initValues);
            }

            public int delete(int id){
                return database.delete(TABLE_NAME_MOVIES_FAVORITE,_ID+" = '"+id +"'",null);
            }

            public Cursor queryProvider(){
                return database.query(DATABASE_TABLE_MOVIES
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID+" ASC");
            }

            public Cursor queryByIdProvider(String id){
                return database.query(DATABASE_TABLE_MOVIES,null
                , _ID+" = ?"
                , new String[]{id}
                ,null
                ,null
                ,null
                ,null);
            }

            public long insertProvider(ContentValues values){
                return database.insert(DATABASE_TABLE_MOVIES,null,values);
            }

            public int updateProvider(String id, ContentValues values){
            return database.update(DATABASE_TABLE_MOVIES,values,_ID+" = ?",new String[]{id});
            }

            public int deleteProvider(String id){
                return database.delete(DATABASE_TABLE_MOVIES,ID+ " = ?",new String[]{id});
            }
}
