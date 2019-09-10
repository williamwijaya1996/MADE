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
import com.example.favoriteapp.adapter.FavoriteTvShowAdapter;
import com.example.favoriteapp.db.DatabaseContract;
import com.example.favoriteapp.model.TvShows;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.favoriteapp.db.DatabaseContract.FavoriteColumn.CONTENT_URI_MOVIES;
import static com.example.favoriteapp.db.DatabaseContract.FavoriteColumn.CONTENT_URI_TVSHOW;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment implements LoadCallback {

    RecyclerView recyclerView;
    public static ArrayList<TvShows> tvShowsListFavorite = new ArrayList<>();
    FavoriteTvShowAdapter favoriteTvShowAdapter;

    public TvShowFavoriteFragment() {
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

        favoriteTvShowAdapter = new FavoriteTvShowAdapter( getContext());
        new TvShowFavoriteFragment.getData(getContext(), this).execute();

    }

    public static ArrayList<TvShows> mapCursorToArrayList(Cursor cursor) {
        ArrayList<TvShows> moviesList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.TITLE));
            String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POSTER));
            double popularity = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POPULARITY));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.DATE));
            int realId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.ID));


            moviesList.add(new TvShows(id,name, popularity, poster_path, date, realId));
        }
        return moviesList;
    }

    @Override
    public void postExecute(Cursor cursor) {
        tvShowsListFavorite = mapCursorToArrayList(cursor);
        if(tvShowsListFavorite.size()>0){
            favoriteTvShowAdapter.notifyDataSetChanged();
            favoriteTvShowAdapter.setListTvShow(tvShowsListFavorite);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(favoriteTvShowAdapter);
            Log.d("post ", "ada data" + tvShowsListFavorite.size());
        }else{
            favoriteTvShowAdapter.notifyDataSetChanged();
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
                return weakContext.get().getContentResolver().query(CONTENT_URI_TVSHOW, null, null, null, null);

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
        tvShowsListFavorite.clear();
        new getData(getContext(),this).execute();
    }
}
