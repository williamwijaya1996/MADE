package com.example.favoriteapp;

import android.database.Cursor;

public interface LoadCallback {

    void postExecute(Cursor cursor);
}
