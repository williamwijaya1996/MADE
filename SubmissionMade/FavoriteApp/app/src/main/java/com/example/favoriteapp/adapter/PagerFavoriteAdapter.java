package com.example.favoriteapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.favoriteapp.fragment.MovieFavoriteFragment;
import com.example.favoriteapp.fragment.TvShowFavoriteFragment;

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
