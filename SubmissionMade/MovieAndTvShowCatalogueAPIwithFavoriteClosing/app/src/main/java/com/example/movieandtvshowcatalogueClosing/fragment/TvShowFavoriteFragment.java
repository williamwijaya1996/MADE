package com.example.movieandtvshowcatalogueClosing.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.movieandtvshowcatalogueClosing.DetailActivity;
import com.example.movieandtvshowcatalogueClosing.MainActivity;
import com.example.movieandtvshowcatalogueClosing.R;
import com.example.movieandtvshowcatalogueClosing.adapter.ListItemListener;
import com.example.movieandtvshowcatalogueClosing.adapter.ListTvShowFavoriteAdapter;
import com.example.movieandtvshowcatalogueClosing.roomdb.TvShows;

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
