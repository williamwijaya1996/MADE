package com.example.movieandtvshowcatalogue;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.movieandtvshowcatalogue.fragment.FavoriteContainerFragment;
import com.example.movieandtvshowcatalogue.fragment.HomeContainerFragment;
import com.example.movieandtvshowcatalogue.roomdb.MyMoviesDataBase;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "mydebug_MainActivity";
    String fragmentTag = "myfragment";
    private final String EXTRA_FRAGMENT = "extra_fragment";
    private final String EXTRA_TITLE = "extra_title";
    private String title;

    public static MyMoviesDataBase myMoviesDataBase;

    private Fragment selectedFragment = new HomeContainerFragment();

    BottomNavigationView bottomNavigationView;

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

            Log.d(TAG,"SavedInstance title: "+title);
        }

        initId();
        init();


    }

    private void initId() {

        bottomNavigationView = findViewById(R.id.navLayout);

        myMoviesDataBase = Room.databaseBuilder(getApplicationContext(),MyMoviesDataBase.class,"moviesdb").allowMainThreadQueries().build();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.topColor)));
        } else {
            Log.d(TAG, "not Response");
        }
    }

    private void init() {

        Log.d(TAG, "Activity");
        Log.d(TAG, "fragment_tag " + fragmentTag);
        Log.d(TAG,"title:"+title);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        selectedFragment = new HomeContainerFragment();
                        title = getResources().getString(R.string.title_movie_tvshow);

                        break;

                    case R.id.action_favorite:
                        selectedFragment = new FavoriteContainerFragment();
                        title = getResources().getString(R.string.favorite);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                getSupportActionBar().setTitle(title);
                Log.d(TAG,"Title Choose :"+title);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TITLE,title);
        getSupportFragmentManager().putFragment(outState,EXTRA_FRAGMENT,selectedFragment);
    }
}
