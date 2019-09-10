package com.example.movieandtvshowcatalogueClosing.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.movieandtvshowcatalogueClosing.db.DatabaseContract;

import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.getColumnInt;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.getColumnString;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.getColumnDouble;

public class MoviesFavorite implements Parcelable {
    String title;
    String release_date;
    String poster_path;
    double popularity;
    int id;
    int realId;

    public MoviesFavorite() {

    }

    protected MoviesFavorite(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        popularity = in.readDouble();
        id = in.readInt();
        realId = in.readInt();
    }

    public static final Creator<MoviesFavorite> CREATOR = new Creator<MoviesFavorite>() {
        @Override
        public MoviesFavorite createFromParcel(Parcel in) {
            return new MoviesFavorite(in);
        }

        @Override
        public MoviesFavorite[] newArray(int size) {
            return new MoviesFavorite[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRealId() {
        return realId;
    }

    public void setRealId(int realId) {
        this.realId = realId;
    }

    public MoviesFavorite(int id, String title, String release_date, String poster_path, double popularity,int realId) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.realId = realId;

    }

    public MoviesFavorite(Cursor cursor) {
        this.id = getColumnInt(cursor, DatabaseContract.FavoriteColumn._ID);
        this.title = getColumnString(cursor, DatabaseContract.FavoriteColumn.TITLE);
        this.release_date = getColumnString(cursor, DatabaseContract.FavoriteColumn.DATE);
        this.poster_path = getColumnString(cursor, DatabaseContract.FavoriteColumn.POSTER);
        this.popularity = getColumnDouble(cursor, DatabaseContract.FavoriteColumn.POPULARITY);
        this.realId = getColumnInt(cursor,DatabaseContract.FavoriteColumn.ID);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeDouble(popularity);
        dest.writeInt(id);
    }
}
