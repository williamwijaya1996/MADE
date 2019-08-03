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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.movieandtvshowcatalogue.DetailActivity;
import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.adapter.ListItemListener;
import com.example.movieandtvshowcatalogue.adapter.ListTvShowAdapter;
import com.example.movieandtvshowcatalogue.api.ApiInterface;
import com.example.movieandtvshowcatalogue.config.Constants;
import com.example.movieandtvshowcatalogue.model.TvShow;
import com.example.movieandtvshowcatalogue.model.TvShowApi;
import com.example.movieandtvshowcatalogue.model.TvShowApiResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    private ArrayList<TvShow> tvShowsList = new ArrayList<>();
    private ArrayList<TvShowApi> tvShowListApi = new ArrayList<>();
    ListTvShowAdapter listTvShowAdapter;
    private final String TAG = "Mydebug_TvShow";

    public TvShowFragment() {
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


        if(savedInstanceState == null){
            progressBar.setVisibility(View.VISIBLE);
            getTvShowApi();
        }else{
            tvShowListApi = savedInstanceState.getParcelableArrayList("tvShowApi");
            showRecyclerViewList();
        }


    }

    private void getTvShowApi(){
        Retrofit tvShowApi = new Retrofit.Builder().baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiInterface apiInterface = tvShowApi.create(ApiInterface.class);
        Call<TvShowApiResponse> callTvShow = apiInterface.apiTvShow(Constants.KEY_API,"en-US",1);
        callTvShow.enqueue(new Callback<TvShowApiResponse>() {
            @Override
            public void onResponse(Call<TvShowApiResponse> call, Response<TvShowApiResponse> response) {
                Log.d(TAG,response.code()+"");
                if(response.code() == 200 && response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    tvShowListApi.addAll(response.body().getResults());
                    showRecyclerViewList();
                }
            }

            @Override
            public void onFailure(Call<TvShowApiResponse> call, Throwable t) {
                Log.d(TAG,t.toString());
            }
        });

    }

    private void showRecyclerViewList(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listTvShowAdapter = new ListTvShowAdapter(tvShowListApi,getContext());
        listTvShowAdapter.setListTvShow(tvShowListApi);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listTvShowAdapter);

        recyclerView.addOnItemTouchListener(new ListItemListener(getContext(), recyclerView, new ListItemListener.NotifyClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intentTvShow = new Intent(getActivity(), DetailActivity.class);
                intentTvShow.putExtra(DetailActivity.EXTRA_TVSHOW,tvShowsList);
                intentTvShow.putExtra(DetailActivity.EXTRA_FROM,DetailActivity.EXTRA_FROM_TVSHOW);
                intentTvShow.putExtra(DetailActivity.EXTRA_POSITION,position);
                startActivity(intentTvShow);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("tvShowApi",tvShowListApi);
    }

    @Override
    public void onStart() {
        super.onStart();
       // listTvShowAdapter.notifyDataSetChanged();
        recyclerView.invalidate();
    }
}
