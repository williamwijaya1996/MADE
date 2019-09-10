package com.example.favoriteapp;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.favoriteapp.fragment.ContainerFavoriteFragment;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "mydebug_MainActivity";
    String fragmentTag = "myfragment";
    private final String EXTRA_FRAGMENT = "extra_fragment";
    private final String EXTRA_TITLE = "extra_title";
    private String title;
    private Fragment selectedFragment = new ContainerFavoriteFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            title = getResources().getString(R.string.title_movie_tvshow);

        } else {
            selectedFragment = getSupportFragmentManager().getFragment(savedInstanceState, EXTRA_FRAGMENT);
            title = savedInstanceState.getString(EXTRA_TITLE);

            Log.d(TAG, "SavedInstance title: " + title);
        }

        initId();
        init();
    }

    private void initId(){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.topColor)));
        } else {
            Log.d(TAG, "not Response");
        }
    }

    private void init(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment)
                .commit();
        getSupportActionBar().setTitle(title);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TITLE, title);
        getSupportFragmentManager().putFragment(outState, EXTRA_FRAGMENT, selectedFragment);
    }
}
