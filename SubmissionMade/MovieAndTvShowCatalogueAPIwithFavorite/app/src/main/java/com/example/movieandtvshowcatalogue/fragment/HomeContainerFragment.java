package com.example.movieandtvshowcatalogue.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieandtvshowcatalogue.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeContainerFragment extends Fragment {


    public HomeContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_container, container, false);
    }

}
