package com.example.movieandtvshowcatalogueClosing.fragment;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieandtvshowcatalogueClosing.R;
import com.example.movieandtvshowcatalogueClosing.adapter.PagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeContainerFragment extends Fragment {

    TabLayout tabLayout;
    private ViewPager viewPager;

    public HomeContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     tabLayout = view.findViewById(R.id.tabLayout);
     viewPager = view.findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.tv_show)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        final PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
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
