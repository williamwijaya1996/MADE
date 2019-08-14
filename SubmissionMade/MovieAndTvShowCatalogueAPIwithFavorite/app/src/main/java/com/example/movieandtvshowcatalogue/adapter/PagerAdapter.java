package com.example.movieandtvshowcatalogue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.movieandtvshowcatalogue.fragment.MovieFragment;
import com.example.movieandtvshowcatalogue.fragment.TvShowFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numTabs;

    public PagerAdapter(FragmentManager fm,int NumberofTabs) {
        super(fm);
        this.numTabs = NumberofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MovieFragment movieFragment = new MovieFragment();
                return movieFragment;
            case 1:
                TvShowFragment tvShowFragment = new TvShowFragment();
                return tvShowFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
