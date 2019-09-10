package com.example.movieandtvshowcatalogueClosing.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.movieandtvshowcatalogueClosing.fragment.MovieFavoriteFragment;
import com.example.movieandtvshowcatalogueClosing.fragment.TvShowFavoriteFragment;

public class PagerFavoriteAdapter extends FragmentStatePagerAdapter {

    int numTabs;

    public PagerFavoriteAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MovieFavoriteFragment movieFavoriteFragment = new MovieFavoriteFragment();
                return movieFavoriteFragment;
            case 1:
                TvShowFavoriteFragment tvShowFavoriteFragment = new TvShowFavoriteFragment();
                return tvShowFavoriteFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
