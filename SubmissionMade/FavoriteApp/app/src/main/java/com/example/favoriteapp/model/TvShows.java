package com.example.favoriteapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShows implements Parcelable {

    private int id;
    private String name;
    private double popularity;
    private String poster_path;
    private String release_air_date;
    private int realId;

    public TvShows(int id,String name, double popularity, String poster_path, String release_air_date, int realId) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_air_date = release_air_date;
        this.realId = realId;
    }

    protected TvShows(Parcel in) {
        id = in.readInt();
        name = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        release_air_date = in.readString();
        realId = in.readInt();
    }

    public static final Creator<TvShows> CREATOR = new Creator<TvShows>() {
        @Override
        public TvShows createFromParcel(Parcel in) {
            return new TvShows(in);
        }

        @Override
        public TvShows[] newArray(int size) {
            return new TvShows[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRelease_air_date() {
        return release_air_date;
    }

    public void setRelease_air_date(String release_air_date) {
        this.release_air_date = release_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(release_air_date);
        dest.writeInt(realId);
    }
}
