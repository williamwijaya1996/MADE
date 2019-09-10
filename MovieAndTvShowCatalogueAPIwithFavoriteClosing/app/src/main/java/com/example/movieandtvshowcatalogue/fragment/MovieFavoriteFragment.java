package com.example.movieandtvshowcatalogue.fragment;


import android.content.Intent;
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
import android.widget.ProgressBar;

import com.example.movieandtvshowcatalogue.DetailActivity;
import com.example.movieandtvshowcatalogue.MainActivity;
import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.adapter.ListItemListener;
import com.example.movieandtvshowcatalogue.adapter.ListMovieFavoriteAdapter;
import com.example.movieandtvshowcatalogue.roomdb.Movies;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    public static ArrayList<Movies> movieListFavorite = new ArrayList<>();
    ListMovieFavoriteAdapter listMovieFavoriteAdapter;
    List<Movies> moviesList;
    final String TAG = "Favorite Movie";

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvList);
        progressBar = view.findViewById(R.id.progressbar);
        recyclerView.setHasFixedSize(true);



    }


    private void showRecyclerViewList() {
        Log.d(TAG,"==== Show RecyclerView ===");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listMovieFavoriteAdapter = new ListMovieFavoriteAdapter(movieListFavorite, getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listMovieFavoriteAdapter);

        recyclerView.addOnItemTouchListener(new ListItemListener(getContext(), recyclerView, new ListItemListener.NotifyClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intentMovie = new Intent(getActivity(), DetailActivity.class);
                intentMovie.putExtra(DetailActivity.EXTRA_MOVIE, movieListFavorite);
                intentMovie.putExtra(DetailActivity.EXTRA_FROM, DetailActivity.EXTRA_FAVORITE_MOVIE);
                intentMovie.putExtra(DetailActivity.EXTRA_POSITION, position);
                intentMovie.putExtra(DetailActivity.EXTRA_CHECKED, true);
                startActivity(intentMovie);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void onResume() {
        super.onResume();
        moviesList = MainActivity.myMoviesDataBase.myDao().getMovies();
        Log.d(TAG, "" + moviesList);
        Log.d(TAG, "Size: " + moviesList.size());
        movieListFavorite.clear();
        movieListFavorite.addAll(moviesList);


        showRecyclerViewList();
        listMovieFavoriteAdapter.notifyDataSetChanged();
    }
}
