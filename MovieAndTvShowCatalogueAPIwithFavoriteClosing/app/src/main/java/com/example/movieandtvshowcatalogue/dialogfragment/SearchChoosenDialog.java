package com.example.movieandtvshowcatalogue.dialogfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.SearchActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchChoosenDialog extends DialogFragment {


    TextView searchMovie,searchTvShow;

    public SearchChoosenDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_choosen_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchMovie = view.findViewById(R.id.search_movie);
        searchTvShow = view.findViewById(R.id.search_tvshow);
        searchTvShow.setText(getResources().getString(R.string.search_tvshow));
        searchMovie.setText(getResources().getString(R.string.search_movie));

        init();
    }

    private void init(){

        searchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearchMovie = new Intent(getActivity(), SearchActivity.class);
                intentSearchMovie.putExtra(SearchActivity.FROM,SearchActivity.SEARCH_MOVIE);
                startActivity(intentSearchMovie);
                getDialog().dismiss();
            }
        });

        searchTvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearchTvShow = new Intent(getActivity(),SearchActivity.class);
                intentSearchTvShow.putExtra(SearchActivity.FROM,SearchActivity.SEARCH_TVSHOW);
                startActivity(intentSearchTvShow);
                getDialog().dismiss();
            }
        });
    }
}
