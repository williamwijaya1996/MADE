package com.example.movieandtvshowcatalogue.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieandtvshowcatalogue.DetailActivity;
import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.adapter.ListItemListener;
import com.example.movieandtvshowcatalogue.adapter.ListMovieAdapter;
import com.example.movieandtvshowcatalogue.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    RecyclerView recyclerView;
    private String[] dataTitle, dataDescription, dataRuntime, dataBudget, dataRevenue, dataDate, dataGenre;
    private int[] dataRating;
    private TypedArray dataPhoto;
    private ArrayList<Movie> movieList = new ArrayList<>();

    public MovieFragment() {
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
        recyclerView.setHasFixedSize(true);

        dataTitle = getResources().getStringArray(R.array.data_title);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataRuntime = getResources().getStringArray(R.array.data_runtime);
        dataBudget = getResources().getStringArray(R.array.data_budget);
        dataRevenue = getResources().getStringArray(R.array.data_revenue);
        dataDate = getResources().getStringArray(R.array.data_date);
        dataGenre = getResources().getStringArray(R.array.data_genre);
        dataRating = getResources().getIntArray(R.array.data_rating);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);

        getDataMovie();
        showRecyclerViewList();
    }

    private void showRecyclerViewList(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(getContext());
        listMovieAdapter.setListMovie(movieList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listMovieAdapter);

        recyclerView.addOnItemTouchListener(new ListItemListener(getContext(), recyclerView, new ListItemListener.NotifyClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void getDataMovie() {

        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(dataTitle[i]);
            movie.setDescription(dataDescription[i]);
            movie.setRuntime(dataRuntime[i]);
            movie.setBudget(dataBudget[i]);
            movie.setRevenue(dataRevenue[i]);
            movie.setDate(dataDate[i]);
            movie.setGenre(dataGenre[i]);
            movie.setRating(dataRating[i]);
            movie.setPhoto(dataPhoto.getResourceId(i,-1));

            movieList.add(movie);
        }

    }

}
