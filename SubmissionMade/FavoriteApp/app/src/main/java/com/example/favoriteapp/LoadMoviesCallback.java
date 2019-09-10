package com.example.favoriteapp;

import android.database.Cursor;

public interface LoadMoviesCallback {

    void postExecute(Cursor cursor);
}
