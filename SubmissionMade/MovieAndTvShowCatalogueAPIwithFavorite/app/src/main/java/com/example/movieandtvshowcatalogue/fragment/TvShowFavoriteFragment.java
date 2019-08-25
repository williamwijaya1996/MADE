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
import com.example.movieandtvshowcatalogue.adapter.ListTvShowFavoriteAdapter;
import com.example.movieandtvshowcatalogue.roomdb.TvShows;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    public static ArrayList<TvShows> tvShowListFavorite = new ArrayList<>();
    ListTvShowFavoriteAdapter listTvShowFavoriteAdapter;
    List<TvShows> tvShowsList;
    final String TAG = "Favorite TvShow";


    public TvShowFavoriteFragment() {
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

    private void showRecyclerViewList(){

        Log.d(TAG,"==== Show RecyclerView ===");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listTvShowFavoriteAdapter = new ListTvShowFavoriteAdapter(tvShowListFavorite,getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listTvShowFavoriteAdapter);

        recyclerView.addOnItemTouchListener(new ListItemListener(getContext(), recyclerView, new ListItemListener.NotifyClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intentTvShow = new Intent(getActivity(), DetailActivity.class);
                intentTvShow.putExtra(DetailActivity.EXTRA_TVSHOW,tvShowListFavorite);
                intentTvShow.putExtra(DetailActivity.EXTRA_FROM,DetailActivity.EXTRA_FAVORITE_TVSHOW);
                intentTvShow.putExtra(DetailActivity.EXTRA_POSITION,position);
                intentTvShow.putExtra(DetailActivity.EXTRA_CHECKED,true);
                startActivity(intentTvShow);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        tvShowsList = MainActivity.myMoviesDataBase.tvShowDao().getTvshows();
        Log.d(TAG, "" + tvShowsList);
        Log.d(TAG, "Size: " + tvShowsList.size());
        tvShowListFavorite.clear();
        tvShowListFavorite.addAll(tvShowsList);

        showRecyclerViewList();
        listTvShowFavoriteAdapter.notifyDataSetChanged();
    }
}
