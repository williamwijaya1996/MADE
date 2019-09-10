package com.example.movieandtvshowcatalogueClosing.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.movieandtvshowcatalogueClosing.fragment.MovieFragment;
import com.example.movieandtvshowcatalogueClosing.fragment.TvShowFragment;

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
