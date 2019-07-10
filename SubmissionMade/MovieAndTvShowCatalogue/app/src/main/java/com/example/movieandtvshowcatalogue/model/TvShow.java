package com.example.movieandtvshowcatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int photo, rating;
    private String title;
    private String description;
    private String runtime;
    private String genre;
    private String date;
    private String status;
    private String language;

    public TvShow(){

    }

    protected TvShow(Parcel in) {
        photo = in.readInt();
        rating = in.readInt();
        title = in.readString();
        description = in.readString();
        runtime = in.readString();
        genre = in.readString();
        date = in.readString();
        status = in.readString();
        language = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeInt(rating);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(runtime);
        dest.writeString(genre);
        dest.writeString(date);
        dest.writeString(status);
        dest.writeString(language);
    }
}
