package com.example.movieandtvshowcatalogueClosing.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = TvShows.TABLE_NAME_TVSHOW)
public class TvShows implements Parcelable {

public static final String TABLE_NAME_TVSHOW="tvShows";

    @PrimaryKey
    private int id;

    @ColumnInfo (name ="name" )
    private String name;

    @ColumnInfo (name ="vote_count" )
    private double vote_count;


    @ColumnInfo (name ="first_air_date" )
    private String first_air_date;


    @ColumnInfo (name ="original_language" )
    private String original_language;


    @ColumnInfo (name ="vote_average" )
    private double vote_average;


    @ColumnInfo (name ="overview" )
    private String overview;


    @ColumnInfo (name ="poster_path" )
    private String poster_path;




    @ColumnInfo (name ="popularity" )
    private double popularity;

    protected TvShows(Parcel in) {
        id = in.readInt();
        name = in.readString();
        vote_count = in.readDouble();
        first_air_date = in.readString();
        original_language = in.readString();
        vote_average = in.readDouble();
        overview = in.readString();
        poster_path = in.readString();

        popularity = in.readDouble();
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

    public TvShows(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(vote_count);
        dest.writeString(first_air_date);
        dest.writeString(original_language);
        dest.writeDouble(vote_average);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeDouble(popularity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVote_count() {
        return vote_count;
    }

    public void setVote_count(double vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
}
