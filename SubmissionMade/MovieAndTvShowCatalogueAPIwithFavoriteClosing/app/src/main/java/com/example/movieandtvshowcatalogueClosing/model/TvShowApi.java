package com.example.movieandtvshowcatalogueClosing.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowApi implements Parcelable {

    private int id;
    private String name;
    private double vote_count;
    private String first_air_date;
    private String original_language;
    private double vote_average;
    private String overview;
    private String poster_path;
    private String[] origin_country;
    private double popularity;

    public TvShowApi(String name, double vote_count, String first_air_date, String original_language,
                     double vote_average, String overview, String poster_path, String[] origin_country, double popularity) {
        this.name = name;
        this.vote_count = vote_count;
        this.first_air_date = first_air_date;
        this.original_language = original_language;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
        this.origin_country = origin_country;
        this.popularity = popularity;
    }

    protected TvShowApi(Parcel in) {
        id = in.readInt();
        name = in.readString();
        vote_count = in.readDouble();
        first_air_date = in.readString();
        original_language = in.readString();
        vote_average = in.readDouble();
        overview = in.readString();
        poster_path = in.readString();
        origin_country = in.createStringArray();
        popularity = in.readDouble();
    }

    public static final Creator<TvShowApi> CREATOR = new Creator<TvShowApi>() {
        @Override
        public TvShowApi createFromParcel(Parcel in) {
            return new TvShowApi(in);
        }

        @Override
        public TvShowApi[] newArray(int size) {
            return new TvShowApi[size];
        }
    };

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

    public void setVote_count(int vote_count) {
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

    public void setVote_average(int vote_average) {
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

    public String[] getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String[] origin_country) {
        this.origin_country = origin_country;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(vote_count);
        parcel.writeString(first_air_date);
        parcel.writeString(original_language);
        parcel.writeDouble(vote_average);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
        parcel.writeStringArray(origin_country);
        parcel.writeDouble(popularity);
    }
}
