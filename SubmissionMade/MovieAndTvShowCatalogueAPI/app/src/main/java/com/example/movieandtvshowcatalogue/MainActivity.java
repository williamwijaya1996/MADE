package com.example.movieandtvshowcatalogue;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.movieandtvshowcatalogue.adapter.PagerAdapter;
import com.example.movieandtvshowcatalogue.fragment.MovieFragment;
import com.example.movieandtvshowcatalogue.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    private final String TAG = "mydebug_MainActivity";
    Fragment currentFragment = new MovieFragment();
    String fragmentTag = "myfragment";
    FragmentManager fragmentManager = getSupportFragmentManager();
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initId();
        init();


    }

    private void initId() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pager);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_movie_tvshow);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.topColor)));
        } else {
            Log.d(TAG, "not Response");
        }
    }

    private void init() {

        Log.d(TAG,"Activity");
        Log.d(TAG,"fragment_tag "+fragmentTag);

        tabLayout.addTab(tabLayout.newTab().setText("Movie"));
        tabLayout.addTab(tabLayout.newTab().setText("TvShow"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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

//
//        if(fragmentManager.findFragmentByTag(fragmentTag) == null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.frame_layout, currentFragment, fragmentTag);
//            transaction.commit();
//        }
//
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                    switch (tab.getPosition()){
//                    case 0:
//                        currentFragment = new MovieFragment();
//                        fragmentTag = "myFragment";
//                        break;
//                    case 1:
//                        currentFragment = new TvShowFragment();
//                        fragmentTag = "secondFragment";
//                        break;
//                }
//
//                Log.d(TAG,"fragment_tag inside "+fragmentTag);
//                if(fragmentManager.findFragmentByTag(fragmentTag) == null) {
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, currentFragment, fragmentTag);
//                    transaction.commit();
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
