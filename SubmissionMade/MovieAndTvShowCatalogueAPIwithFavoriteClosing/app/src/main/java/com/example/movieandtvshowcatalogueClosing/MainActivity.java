package com.example.movieandtvshowcatalogueClosing;

import androidx.room.Room;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.movieandtvshowcatalogueClosing.dialogfragment.OptionsChoosenDialog;
import com.example.movieandtvshowcatalogueClosing.dialogfragment.SearchChoosenDialog;
import com.example.movieandtvshowcatalogueClosing.fragment.FavoriteContainerFragment;
import com.example.movieandtvshowcatalogueClosing.fragment.HomeContainerFragment;
import com.example.movieandtvshowcatalogueClosing.roomdb.MyMoviesDataBase;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "mydebug_MainActivity";
    String fragmentTag = "myfragment";
    private final String EXTRA_FRAGMENT = "extra_fragment";
    private final String EXTRA_TITLE = "extra_title";
    private String title;
    private static HandlerThread handlerThread;
    private DataObserver myObserver;

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

            Log.d(TAG, "SavedInstance title: " + title);
        }

        initId();
        init();


    }

    private void initId() {

        bottomNavigationView = findViewById(R.id.navLayout);

        myMoviesDataBase = Room.databaseBuilder(getApplicationContext(), MyMoviesDataBase.class, "moviesdb").allowMainThreadQueries().build();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.topColor)));
        } else {
            Log.d(TAG, "not Response");
        }

        handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler,this);
        //getContentResolver().registerContentObserver(CONTENT_URI_MOVIES,true,myObserver);
    }

    private void init() {

        Log.d(TAG, "Activity");
        Log.d(TAG, "fragment_tag " + fragmentTag);
        Log.d(TAG, "title:" + title);


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
                Log.d(TAG, "Title Choose :" + title);
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
            FragmentManager fm = getSupportFragmentManager();
            OptionsChoosenDialog optionsChoosenDialog = new OptionsChoosenDialog();
            optionsChoosenDialog.show(fm, null);
        } else if (item.getItemId() == R.id.action_search) {
            FragmentManager fm = getSupportFragmentManager();
            SearchChoosenDialog searchChoosenDialog = new SearchChoosenDialog();
            searchChoosenDialog.show(fm, null);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TITLE, title);
        getSupportFragmentManager().putFragment(outState, EXTRA_FRAGMENT, selectedFragment);
    }

    public static class DataObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */

        final Context context;

        public DataObserver(Handler handler,Context context) {
            super(handler);
            this.context = context;
        }
    }
}
