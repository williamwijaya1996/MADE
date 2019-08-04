package com.example.movieandtvshowcatalogue;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieandtvshowcatalogue.config.Constants;
import com.example.movieandtvshowcatalogue.model.Movie;
import com.example.movieandtvshowcatalogue.model.MovieApi;
import com.example.movieandtvshowcatalogue.model.TvShow;
import com.example.movieandtvshowcatalogue.model.TvShowApi;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDescription,tvTitle,tvDate,tvPopularity,tvPopularityTvShow,tvVoteCount,tvVoteCountTvShow,
    tvVoteAverage,tvVoteAverageTvShow,tvOriginalLang,tvOriginalLangTvShow,tvAdult,tvOriginCountry;

    private ImageView imgPhoto;
    private int position;
    private String from;
    View informationMovie,informationTvShow;

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
            informationTvShow.setVisibility(View.GONE);

            ArrayList<MovieApi> movies = getIntent().getParcelableArrayListExtra(EXTRA_MOVIE);
            Picasso.with(this).load(Constants.START_IMAGE+ movies.get(position).getPoster_path()).into(imgPhoto);
            tvTitle.setText(movies.get(position).getTitle());
            tvDate.setText(movies.get(position).getRelease_date());
            tvDescription.setText(movies.get(position).getOverview());
            tvPopularity.setText(String.format("%s%%",movies.get(position).getPopularity()));
            tvOriginalLang.setText(movies.get(position).getOriginal_language());
            tvVoteCount.setText(String.format("%s%%",movies.get(position).getVote_count()));
            tvVoteAverage.setText(String.format("%s%%",movies.get(position).getVote_average()));
            if(movies.get(position).isAdult()){
                tvAdult.setText("Yes");
            }else {
                tvAdult.setText("No");
            }


            setActionBarTitle(getString(R.string.detail_movie));


        }else{

            informationMovie.setVisibility(View.GONE);
            informationTvShow.setVisibility(View.VISIBLE);

            ArrayList<TvShowApi> tvShows = getIntent().getParcelableArrayListExtra(EXTRA_TVSHOW);
            Picasso.with(this).load(Constants.START_IMAGE+ tvShows.get(position).getPoster_path()).into(imgPhoto);
            tvTitle.setText(tvShows.get(position).getName());
            tvDate.setText(tvShows.get(position).getFirst_air_date());
            tvDescription.setText(tvShows.get(position).getOverview());
            tvVoteAverageTvShow.setText(String.format("%s%%",tvShows.get(position).getVote_average()));
            tvVoteCountTvShow.setText(String.format("%s%%",tvShows.get(position).getVote_count()));
            tvPopularityTvShow.setText(String.format("%s%%",tvShows.get(position).getPopularity()));
            tvOriginalLangTvShow.setText(tvShows.get(position).getOriginal_language());
            tvOriginCountry.setText(tvShows.get(position).getOrigin_country()[0]);

            setActionBarTitle(getString(R.string.detail_tvshow));

        }


    }

    private void initId(){
        imgPhoto = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tvTitleDetail);
        tvDate = findViewById(R.id.tvDateDetail);
        tvDescription = findViewById(R.id.tvDescriptionDetail);
        tvOriginalLang = findViewById(R.id.tv_ori_lang);
        tvOriginalLangTvShow = findViewById(R.id.tv_ori_lang_tvShow);
        tvOriginCountry = findViewById(R.id.tv_origin_country_tvShow);
        tvAdult = findViewById(R.id.tv_adult);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvPopularityTvShow = findViewById(R.id.tv_popularity_tvShow);
        tvVoteAverage = findViewById(R.id.tv_vote_average);
        tvVoteAverageTvShow = findViewById(R.id.tv_vote_average_tvShow);
        tvVoteCount = findViewById(R.id.tv_vote_count);
        tvVoteCountTvShow = findViewById(R.id.tv_vote_count_tvShow);


        informationMovie = findViewById(R.id.layout_movie);
        informationTvShow = findViewById(R.id.layout_tvshow);
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
