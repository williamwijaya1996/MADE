package com.example.moviecatalogue.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int photo,rating;
    private String judul;
    private String description;
    private String runtime;
    private String budget;
    private String revenue;



    private String genre;



    private String tanggal;

    public Movie(){

    }

    protected Movie(Parcel in) {
        photo = in.readInt();
        rating = in.readInt();
        judul = in.readString();
        description = in.readString();
        runtime = in.readString();
        budget = in.readString();
        revenue = in.readString();
        genre = in.readString();
        tanggal = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
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

    public String getJudul() {
        return judul;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setJudul(String judul) {
        this.judul = judul;
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

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeInt(rating);
        dest.writeString(judul);
        dest.writeString(description);
        dest.writeString(runtime);
        dest.writeString(budget);
        dest.writeString(revenue);
        dest.writeString(genre);
        dest.writeString(tanggal);
    }
}
