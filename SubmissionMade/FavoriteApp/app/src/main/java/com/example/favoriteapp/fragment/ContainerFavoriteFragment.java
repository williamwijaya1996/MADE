package com.example.favoriteapp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.favoriteapp.R;
import com.example.favoriteapp.adapter.PagerFavoriteAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerFavoriteFragment extends Fragment {
    TabLayout tabLayout;
    private ViewPager viewPager;

    public ContainerFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayoutFavorite);
        viewPager = view.findViewById(R.id.pagerFavorite);

        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.tv_show)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        final PagerFavoriteAdapter pagerFavoriteAdapter = new PagerFavoriteAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerFavoriteAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
