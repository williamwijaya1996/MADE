package com.example.movieandtvshowcatalogueClosing.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = Movies.TABLE_NAME_MOVIES)
public class Movies implements Parcelable {

    public static final String TABLE_NAME_MOVIES = "movies";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "release_date";
    public static final String COLUMN_POPULARITY="popularity";
    public static final String COLUMN_POSTER = "poster_path";
    public static final String COLUMN_VOTECOUNT = "vote_count";
    public static final String COLUMN_VOTEAVERAGE = "vote_average";
    public static final String COLUMN_ORI_LANG = "original_langguge";
    public static final String COLUMN_ADULT = "adult";
    public static final String COLUMN_OVERVIEW = "overview";

    @PrimaryKey
    private int id;

    @ColumnInfo(name = COLUMN_VOTECOUNT)
    private int vote_count;

    @ColumnInfo(name = COLUMN_VOTEAVERAGE)
    private double vote_average;

    @ColumnInfo(name = COLUMN_TITLE)
    private String title;

    @ColumnInfo(name = COLUMN_POPULARITY)
    private double popularity;

    @ColumnInfo(name = COLUMN_POSTER)
    private String poster_path;

    @ColumnInfo(name = COLUMN_ORI_LANG)
    private String original_language;

    @ColumnInfo(name = COLUMN_ADULT)
    private boolean adult;

    @ColumnInfo(name = COLUMN_OVERVIEW)
    private String overview;

    @ColumnInfo(name = COLUMN_DATE)
    private String release_date;

    public Movies() {

    }

    protected Movies(Parcel in) {
        id = in.readInt();
        vote_count = in.readInt();
        vote_average = in.readDouble();
        title = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        original_language = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
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

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
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

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(vote_count);
        dest.writeDouble(vote_average);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(original_language);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(release_date);
    }



}
