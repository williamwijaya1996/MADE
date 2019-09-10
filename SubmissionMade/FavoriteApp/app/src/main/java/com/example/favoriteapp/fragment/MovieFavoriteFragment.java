package com.example.favoriteapp.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.favoriteapp.LoadCallback;
import com.example.favoriteapp.R;
import com.example.favoriteapp.adapter.FavoriteMovieAdapter;
import com.example.favoriteapp.db.DatabaseContract;
import com.example.favoriteapp.model.Movies;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.favoriteapp.db.DatabaseContract.FavoriteColumn.CONTENT_URI_MOVIES;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment implements LoadCallback {

    RecyclerView recyclerView;
    public static ArrayList<Movies> moviesListFavorite = new ArrayList<>();
    FavoriteMovieAdapter favoriteMovieAdapter;


//    public static final String COLUMN_TITLE = "title";
//    public static final String COLUMN_DATE = "release_date";
//    public static final String COLUMN_POPULARITY="popularity";
//    public static final String COLUMN_POSTER = "poster_path";
//    public static final String COLUMN_VOTECOUNT = "vote_count";
//    public static final String COLUMN_VOTEAVERAGE = "vote_average";
//    public static final String COLUMN_ORI_LANG = "original_langguge";
//    public static final String COLUMN_ADULT = "adult";
//    public static final String COLUMN_OVERVIEW = "overview";

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        favoriteMovieAdapter = new FavoriteMovieAdapter( getContext());
        new getData(getContext(), this).execute();



    }

    public static ArrayList<Movies> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Movies> moviesList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.TITLE));
            String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POSTER));
            double popularity = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POPULARITY));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.DATE));
            int realId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.ID));


            moviesList.add(new Movies(id, title, date, poster_path, popularity, realId));
        }
        return moviesList;
    }

    @Override
    public void postExecute(Cursor cursor) {
        moviesListFavorite = mapCursorToArrayList(cursor);
        if (moviesListFavorite.size() > 0) {
            favoriteMovieAdapter.notifyDataSetChanged();
            favoriteMovieAdapter.setListData(moviesListFavorite);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(favoriteMovieAdapter);
            Log.d("post ", "ada data" + moviesListFavorite.size());
        } else {
            favoriteMovieAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "Tidak ada data saat ini", Toast.LENGTH_SHORT).show();
        }

    }

    private static class getData extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadCallback> weakCallback;

        private getData(Context context, LoadCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Log.d("movie", "doinbackground");
            try{
                return weakContext.get().getContentResolver().query(CONTENT_URI_MOVIES, null, null, null, null);

            }catch (Exception e){
                Log.d("Movie",e.toString());
            }
            return null;
            }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            Log.d("movie", "postExecute");
            weakCallback.get().postExecute(cursor);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        moviesListFavorite.clear();
        new getData(getContext(),this).execute();
    }
}
