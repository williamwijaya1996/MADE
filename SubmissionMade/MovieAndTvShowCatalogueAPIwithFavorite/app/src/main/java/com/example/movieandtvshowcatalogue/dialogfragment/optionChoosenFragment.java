package com.example.movieandtvshowcatalogue.dialogfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieandtvshowcatalogue.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class optionChoosenFragment extends Fragment {


    public optionChoosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_choosen, container, false);
    }

}
