package com.example.favoriteapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {

    private int id;
    private String title;
    private double popularity;
    private String poster_path;
    private String release_date;
    private int realId;


    public Movies(int id, String title, String release_date, String poster_path, double popularity, int realId) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.realId = realId;
    }

    protected Movies(Parcel in) {
        id = in.readInt();
        title = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        release_date = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRealId() {
        return realId;
    }

    public void setRealId(int realId) {
        this.realId = realId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeDouble(popularity);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
    }
}
