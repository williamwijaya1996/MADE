package com.example.mypreloaddata.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NAME = "table_mahasiswa";
    static final class MahasiswaColumns implements BaseColumns {
        static String NAMA = "nama";
        static String NIM = "nim";
    }
}
