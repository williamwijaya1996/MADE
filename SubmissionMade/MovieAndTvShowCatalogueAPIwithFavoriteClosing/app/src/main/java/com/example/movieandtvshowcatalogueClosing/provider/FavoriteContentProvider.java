package com.example.movieandtvshowcatalogueClosing.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import com.example.movieandtvshowcatalogueClosing.MainActivity;
import com.example.movieandtvshowcatalogueClosing.db.MoviesHelper;
import com.example.movieandtvshowcatalogueClosing.db.TvShowHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.AUTHORITY;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.CONTENT_URI_MOVIES;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.TABLE_NAME_MOVIES_FAVORITE;
import static com.example.movieandtvshowcatalogueClosing.db.DatabaseContract.FavoriteColumn.TABLE_NAME_TVSHOW_FAVORITE;

public class FavoriteContentProvider extends ContentProvider {

    private static final int MOVIES = 1;
    private static final int MOVIE_ID = 2;
    private static final int TVSHOW =3;
    private static final int TVSHOW_ID = 4;
    private MoviesHelper moviesHelper;
    private TvShowHelper tvShowHelper;


    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, TABLE_NAME_MOVIES_FAVORITE, MOVIES);
        MATCHER.addURI(AUTHORITY,TABLE_NAME_MOVIES_FAVORITE+"/#",MOVIE_ID);
        MATCHER.addURI(AUTHORITY,TABLE_NAME_TVSHOW_FAVORITE,TVSHOW);
        MATCHER.addURI(AUTHORITY,TABLE_NAME_TVSHOW_FAVORITE+"/#",TVSHOW_ID);

    }

    @Override
    public boolean onCreate() {
        moviesHelper = MoviesHelper.getInstance(getContext());
        tvShowHelper = TvShowHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        moviesHelper.open();
        tvShowHelper.open();
        Cursor cursor;
        switch (MATCHER.match(uri)) {
            case MOVIES:
                cursor = moviesHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = moviesHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            case TVSHOW:
                cursor = tvShowHelper.queryProvider();
                break;
            case TVSHOW_ID:
                cursor = tvShowHelper.queryByIdProvider(uri.getLastPathSegment());
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        moviesHelper.open();
        tvShowHelper.open();
        long added;
        switch (MATCHER.match(uri)) {
            case MOVIES:
                added = moviesHelper.insertProvider(values);
                break;
            case TVSHOW:
                added = tvShowHelper.insertProvider(values);
            default:
                added = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI_MOVIES, new MainActivity.DataObserver(new Handler(), getContext()));
        return Uri.parse(CONTENT_URI_MOVIES + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        moviesHelper.open();
        tvShowHelper.open();
        int deleted;
        switch (MATCHER.match(uri)) {
            case MOVIE_ID:
                deleted = moviesHelper.deleteProvider(uri.getLastPathSegment());
                break;
            case TVSHOW_ID:
                deleted = tvShowHelper.deleteProvider(uri.getLastPathSegment());
            default:
                deleted = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI_MOVIES,new MainActivity.DataObserver(new Handler(),getContext()));
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        moviesHelper.open();
        int updated;
        switch (MATCHER.match(uri)) {
            case MOVIES:
                updated = moviesHelper.updateProvider(uri.getLastPathSegment(), values);
                break;
            default:
                updated = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI_MOVIES, new MainActivity.DataObserver(new Handler(), getContext()));
        return updated;
    }

}
