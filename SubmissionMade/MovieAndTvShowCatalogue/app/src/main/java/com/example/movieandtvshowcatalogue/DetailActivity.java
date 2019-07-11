package com.example.movieandtvshowcatalogue;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieandtvshowcatalogue.model.Movie;
import com.example.movieandtvshowcatalogue.model.TvShow;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView tvRuntime,tvBudget,tvRevenue,tvGenre,tvDescription,tvRating,tvTitle,tvDate,
            tvStatus,tvLanguage,tvGenreTvShow,tvRuntimeTvShow;
    private ImageView imgPhoto;
    private int position;
    private String from;
    View informationMovie,informatonTvShow;

    public static final String EXTRA_MOVIE ="movie";
    public static final String EXTRA_TVSHOW = "tvShow";
    public static final String EXTRA_POSITION ="position";
    public static final String EXTRA_FROM = "from";
    public static final String EXTRA_FROM_MOVIE = "from_movie";
    public static final String EXTRA_FROM_TVSHOW = "from_tvshow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initId();
        init();
    }

    private void init(){

        from = getIntent().getStringExtra(EXTRA_FROM);
        position = getIntent().getIntExtra(EXTRA_POSITION,0);

        if(from.equals(EXTRA_FROM_MOVIE)){

            informationMovie.setVisibility(View.VISIBLE);
            informatonTvShow.setVisibility(View.GONE);

            ArrayList<Movie> movies = getIntent().getParcelableArrayListExtra(EXTRA_MOVIE);
            imgPhoto.setImageResource(movies.get(position).getPhoto());
            tvTitle.setText(movies.get(position).getTitle());
            tvDate.setText(movies.get(position).getDate());
            tvDescription.setText(movies.get(position).getDescription());
            tvRating.setText(movies.get(position).getRating()+"%");
            tvGenre.setText(movies.get(position).getGenre());
            tvRuntime.setText(movies.get(position).getRuntime());
            tvBudget.setText(movies.get(position).getBudget());
            tvRevenue.setText(movies.get(position).getRevenue());
            setActionBarTitle(getString(R.string.detail_movie));


        }else{

            informationMovie.setVisibility(View.GONE);
            informatonTvShow.setVisibility(View.VISIBLE);

            ArrayList<TvShow> tvShows = getIntent().getParcelableArrayListExtra(EXTRA_TVSHOW);
            imgPhoto.setImageResource(tvShows.get(position).getPhoto());
            tvTitle.setText(tvShows.get(position).getTitle());
            tvDate.setText(tvShows.get(position).getDate());
            tvDescription.setText(tvShows.get(position).getDescription());
            tvRating.setText(tvShows.get(position).getRating()+"%");
            tvGenreTvShow.setText(tvShows.get(position).getGenre());
            tvRuntimeTvShow.setText(tvShows.get(position).getRuntime());
            tvStatus.setText(tvShows.get(position).getStatus());
            tvLanguage.setText(tvShows.get(position).getLanguage());
            setActionBarTitle(getString(R.string.detail_tvshow));

        }


    }

    private void initId(){
        imgPhoto = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tvTitleDetail);
        tvDate = findViewById(R.id.tvDateDetail);
        tvDescription = findViewById(R.id.tvDescriptionDetail);
        tvRating = findViewById(R.id.tvRatingDetail);
        tvGenre = findViewById(R.id.tvGenre);
        tvBudget = findViewById(R.id.tvBudget);
        tvRevenue = findViewById(R.id.tvRevenue);
        tvRuntime = findViewById(R.id.tvRuntime);
        tvStatus = findViewById(R.id.tvStatusTvShow);
        tvLanguage = findViewById(R.id.tvLanguageTvShow);
        tvGenreTvShow = findViewById(R.id.tvGenreTvShow);
        tvRuntimeTvShow = findViewById(R.id.tvRuntimeTvShow);
        informationMovie = findViewById(R.id.layout_movie);
        informatonTvShow = findViewById(R.id.layout_tvshow);
    }

    private void setActionBarTitle(String title){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.topColor)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }else{
            Log.d("Detail Activity","Error getSupportActionBar");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
