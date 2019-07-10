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
import com.example.movieandtvshowcatalogue.adapter.ListTvShowAdapter;
import com.example.movieandtvshowcatalogue.model.TvShow;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    RecyclerView recyclerView;
    private String [] dataTitle,dataDescription,dataRuntime,dataStatus,dataLanguage,dataDate,dataGenre;
    private int[] dataRating;
    private TypedArray dataPhoto;
    private ArrayList<TvShow> tvShowsList = new ArrayList<>();

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
        recyclerView.setHasFixedSize(true);

        dataTitle = getResources().getStringArray(R.array.data_tvshow_title);
        dataDescription = getResources().getStringArray(R.array.data_tvshow_description);
        dataRuntime = getResources().getStringArray(R.array.data_tvshow_runtime);
        dataDate = getResources().getStringArray(R.array.data_tvshow_date);
        dataGenre = getResources().getStringArray(R.array.data_tvshow_genre);
        dataRating = getResources().getIntArray(R.array.data_tvshow_rating);
        dataStatus = getResources().getStringArray(R.array.data_tvshow_status);
        dataLanguage = getResources().getStringArray(R.array.data_tvshow_orilanguage);
        dataPhoto = getResources().obtainTypedArray(R.array.data_tvshow_photo);

        getDataTvShow();
        showRecyclerViewList();

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

    private void showRecyclerViewList(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ListTvShowAdapter listTvShowAdapter = new ListTvShowAdapter(getContext());
        listTvShowAdapter.setListTvShow(tvShowsList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listTvShowAdapter);
    }

    private void getDataTvShow(){

        for (int i =0; i<dataTitle.length;i++){
            TvShow tvShow = new TvShow();
            tvShow.setTitle(dataTitle[i]);
            tvShow.setDescription(dataDescription[i]);
            tvShow.setRuntime(dataRuntime[i]);
            tvShow.setDate(dataDate[i]);
            tvShow.setGenre(dataGenre[i]);
            tvShow.setRating(dataRating[i]);
            tvShow.setStatus(dataStatus[i]);
            tvShow.setLanguage(dataLanguage[i]);
            tvShow.setPhoto(dataPhoto.getResourceId(i,-1));

            tvShowsList.add(tvShow);
        }
    }
}
