package com.example.movieandtvshowcatalogueClosing;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.movieandtvshowcatalogueClosing.adapter.ListItemListener;
import com.example.movieandtvshowcatalogueClosing.adapter.ListMovieAdapter;
import com.example.movieandtvshowcatalogueClosing.adapter.ListTvShowAdapter;
import com.example.movieandtvshowcatalogueClosing.api.ApiInterface;
import com.example.movieandtvshowcatalogueClosing.config.Constants;
import com.example.movieandtvshowcatalogueClosing.model.MovieApi;
import com.example.movieandtvshowcatalogueClosing.model.MovieApiResponse;
import com.example.movieandtvshowcatalogueClosing.model.TvShowApi;
import com.example.movieandtvshowcatalogueClosing.model.TvShowApiResponse;
import com.example.movieandtvshowcatalogueClosing.roomdb.Movies;
import com.example.movieandtvshowcatalogueClosing.roomdb.TvShows;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    public static final String SEARCH_MOVIE = "sarch_movie";
    public static final String SEARCH_TVSHOW = "search_tvshow";
    public static final String FROM = "from";
    public static final String EXTRA_MOVIE = "movie_search_data";
    public static final String EXTRA_TVSHOW = "tvshow_search_data";
    final String TAG = "mydebug_search";
    String from;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private ArrayList<MovieApi> moviesSearchListApi = new ArrayList<>();
    private ArrayList<TvShowApi> tvShowSearchListApi = new ArrayList<>();
    ListMovieAdapter listMovieAdapter;
    ListTvShowAdapter listTvShowAdapter;
    TextView tvNoResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initId(savedInstanceState);
        setActionBar();
    }

    private void initId(Bundle savedInstanceState) {
        from = getIntent().getStringExtra(FROM);
        recyclerView = findViewById(R.id.rvListSearch);
        progressBar = findViewById(R.id.progressbarSearch);
        tvNoResult = findViewById(R.id.tv_noresult);
        recyclerView.setHasFixedSize(true);

        if (savedInstanceState != null) {
            if (from.equals(SEARCH_MOVIE)) {
                moviesSearchListApi = savedInstanceState.getParcelableArrayList(EXTRA_MOVIE);
                showRecyclerViewListMovie();
            } else {
                tvShowSearchListApi = savedInstanceState.getParcelableArrayList(EXTRA_TVSHOW);
                showRecyclerViewListTvShow();
            }

        }

    }

    private void setActionBar() {
        if (getSupportActionBar() != null) {
            if (FROM.equals(SEARCH_MOVIE)) {
                getSupportActionBar().setTitle(getResources().getString(R.string.search_movie));
            } else {
                getSupportActionBar().setTitle(getResources().getString(R.string.search_tvshow));
            }

            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.topColor)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            Log.d("Detail Activity", "Error getSupportActionBar");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) (menu.findItem(R.id.search_view).getActionView());
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                moviesSearchListApi.clear();
                tvShowSearchListApi.clear();

                progressBar.setVisibility(View.VISIBLE);
                tvNoResult.setVisibility(View.GONE);

                if (from.equals(SEARCH_MOVIE)) {
                    getStringSearchMovie(s);
                } else {
                    getStringSearchTvShow(s);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void getStringSearchMovie(final String name) {
        Log.d(TAG, "===== SEARCH MOVIE =====");
        Log.d(TAG, "Get Search :" + name);
        final Retrofit movieApi = new Retrofit.Builder().baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = movieApi.create(ApiInterface.class);
        Call<MovieApiResponse> callMovieSearch = apiInterface.apiSearchMovie(Constants.KEY_API, "en-US", name);
        callMovieSearch.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                Log.d(TAG, "Response Code:" + response.code());
                if (response.code() == 200 && response.isSuccessful()) {
                    moviesSearchListApi.addAll(response.body().getResults());
                    Log.d(TAG, moviesSearchListApi.toString());
                    progressBar.setVisibility(View.GONE);
                    if (moviesSearchListApi.size() == 0) {
                        tvNoResult.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        tvNoResult.setVisibility(View.GONE);
                    }
                    showRecyclerViewListMovie();

                }

            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setMessage(getResources().getString(R.string.error_message));
                builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getStringSearchMovie(name);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void getStringSearchTvShow(final String name) {
        Log.d(TAG, "===== SEARCH TV SHOW =====");
        Log.d(TAG, "Get Search :" + name);
        final Retrofit tvShowApi = new Retrofit.Builder().baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = tvShowApi.create(ApiInterface.class);
        Call<TvShowApiResponse> callTvShowSearch = apiInterface.apiSearchTvShow(Constants.KEY_API, "en-US", name);
        callTvShowSearch.enqueue(new Callback<TvShowApiResponse>() {
            @Override
            public void onResponse(Call<TvShowApiResponse> call, Response<TvShowApiResponse> response) {
                Log.d(TAG, "Response Code:" + response.code());
                if (response.code() == 200 && response.isSuccessful()) {
                    tvShowSearchListApi.addAll(response.body().getResults());
                    Log.d(TAG, tvShowSearchListApi.toString());
                    progressBar.setVisibility(View.GONE);
                    if (tvShowSearchListApi.size() == 0) {
                        tvNoResult.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        tvNoResult.setVisibility(View.GONE);
                    }
                    showRecyclerViewListTvShow();
                }
            }

            @Override
            public void onFailure(Call<TvShowApiResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setMessage(getResources().getString(R.string.error_message));
                builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getStringSearchTvShow(name);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void showRecyclerViewListMovie() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listMovieAdapter = new ListMovieAdapter();
        listMovieAdapter.notifyDataSetChanged();
        listMovieAdapter.setListMovie(moviesSearchListApi, this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listMovieAdapter);

        recyclerView.addOnItemTouchListener(new ListItemListener(SearchActivity.this, recyclerView, new ListItemListener.NotifyClickListener() {
            @Override
            public void onClick(View view, int position) {
                List<Movies> moviesList = MainActivity.myMoviesDataBase.myDao().getMovies();
                boolean checked =false;
                for(int i =0;i<moviesList.size();i++){
                    if(moviesSearchListApi.get(position).getId() == moviesList.get(i).getId()){
                        checked =true;
                    }
                }

                Intent intentMovie = new Intent(SearchActivity.this, DetailActivity.class);
                intentMovie.putExtra(DetailActivity.EXTRA_MOVIE, moviesSearchListApi);
                intentMovie.putExtra(DetailActivity.EXTRA_FROM, DetailActivity.EXTRA_FROM_MOVIE);
                intentMovie.putExtra(DetailActivity.EXTRA_POSITION, position);
                intentMovie.putExtra(DetailActivity.EXTRA_CHECKED,checked);
                startActivity(intentMovie);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void showRecyclerViewListTvShow() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        listTvShowAdapter = new ListTvShowAdapter(tvShowSearchListApi, this);
        listTvShowAdapter.setListTvShow(tvShowSearchListApi);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listTvShowAdapter);

        recyclerView.addOnItemTouchListener(new ListItemListener(SearchActivity.this, recyclerView, new ListItemListener.NotifyClickListener() {
            @Override
            public void onClick(View view, int position) {
                List<TvShows> tvShowsList = MainActivity.myMoviesDataBase.tvShowDao().getTvshows();
                boolean checked = false;
                for(int i = 0; i<tvShowsList.size();i++){
                    if(tvShowSearchListApi.get(position).getId() == tvShowsList.get(i).getId()){
                        checked = true;
                    }
                }

                Intent intentTvShow = new Intent(SearchActivity.this, DetailActivity.class);
                intentTvShow.putExtra(DetailActivity.EXTRA_TVSHOW,tvShowSearchListApi);
                intentTvShow.putExtra(DetailActivity.EXTRA_FROM,DetailActivity.EXTRA_FROM_TVSHOW);
                intentTvShow.putExtra(DetailActivity.EXTRA_POSITION,position);
                intentTvShow.putExtra(DetailActivity.EXTRA_CHECKED,checked);
                startActivity(intentTvShow);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (from.equals(SEARCH_MOVIE)) {
            outState.putParcelableArrayList(EXTRA_MOVIE, moviesSearchListApi);
        } else {
            outState.putParcelableArrayList(EXTRA_TVSHOW, tvShowSearchListApi);
        }

    }
}
