package com.example.movieandtvshowcatalogue.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.movieandtvshowcatalogue.adapter.ListMovieAdapter;
import com.example.movieandtvshowcatalogue.api.ApiInterface;
import com.example.movieandtvshowcatalogue.config.Constants;
import com.example.movieandtvshowcatalogue.model.MovieApi;
import com.example.movieandtvshowcatalogue.model.MovieApiResponse;
import com.example.movieandtvshowcatalogue.roomdb.Movies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private final String TAG ="Mydebug_MovieFragment";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private ArrayList<MovieApi> movieListApi = new ArrayList<>();
    ListMovieAdapter listMovieAdapter;

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
        progressBar = view.findViewById(R.id.progressbar);
        recyclerView.setHasFixedSize(true);
        Log.d(TAG,"View");
        if(savedInstanceState == null){
            progressBar.setVisibility(View.VISIBLE);
            getMovieApi();
            Log.d(TAG,"Api");

        }else {
            Log.d(TAG,"Success");
            movieListApi = savedInstanceState.getParcelableArrayList("movieApi");
            Log.d(TAG,movieListApi.toString());
            showRecyclerViewList();
        }

    }

    private void getMovieApi(){

        final Retrofit movieApi = new Retrofit.Builder().baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = movieApi.create(ApiInterface.class);
        Call<MovieApiResponse> callMovie = apiInterface.apiMovie(Constants.KEY_API,"en-US",1,"US");
        callMovie.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if(response.code() ==200 && response.isSuccessful()){

                    progressBar.setVisibility(View.GONE);
                    movieListApi.addAll(response.body().getResults());
                    showRecyclerViewList();
                    Log.d("Mydebug Movie ",movieListApi+"");

                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getResources().getString(R.string.error_message));
                builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getMovieApi();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void showRecyclerViewList() {
        Log.d(TAG,"recycle Show");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listMovieAdapter = new ListMovieAdapter();
        listMovieAdapter.notifyDataSetChanged();
        listMovieAdapter.setListMovie(movieListApi,getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listMovieAdapter);

        recyclerView.addOnItemTouchListener(new ListItemListener(getContext(), recyclerView, new ListItemListener.NotifyClickListener() {
            @Override
            public void onClick(View view, int position) {
                List<Movies> moviesList = MainActivity.myMoviesDataBase.myDao().getMovies();
                boolean checked =false;
                for(int i =0;i<moviesList.size();i++){
                    if(movieListApi.get(position).getId() == moviesList.get(i).getId()){
                        checked =true;
                    }
                }

                Intent intentMovie = new Intent(getActivity(), DetailActivity.class);
                intentMovie.putExtra(DetailActivity.EXTRA_MOVIE, movieListApi);
                intentMovie.putExtra(DetailActivity.EXTRA_FROM, DetailActivity.EXTRA_FROM_MOVIE);
                intentMovie.putExtra(DetailActivity.EXTRA_POSITION, position);
                intentMovie.putExtra(DetailActivity.EXTRA_CHECKED,checked);
                startActivity(intentMovie);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movieApi",movieListApi);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onstart");
    }
}
