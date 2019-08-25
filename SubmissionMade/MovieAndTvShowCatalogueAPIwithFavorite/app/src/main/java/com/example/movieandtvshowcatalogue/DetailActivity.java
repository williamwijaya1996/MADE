package com.example.movieandtvshowcatalogue;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.movieandtvshowcatalogue.config.Constants;
import com.example.movieandtvshowcatalogue.fragment.MovieFavoriteFragment;
import com.example.movieandtvshowcatalogue.fragment.TvShowFavoriteFragment;
import com.example.movieandtvshowcatalogue.model.MovieApi;
import com.example.movieandtvshowcatalogue.model.TvShowApi;
import com.example.movieandtvshowcatalogue.roomdb.Movies;
import com.example.movieandtvshowcatalogue.roomdb.TvShows;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDescription,tvTitle,tvDate,tvPopularity,tvPopularityTvShow,tvVoteCount,tvVoteCountTvShow,
    tvVoteAverage,tvVoteAverageTvShow,tvOriginalLang,tvOriginalLangTvShow,tvAdult;

    private ImageView imgPhoto;
    private int position;
    private String from,favoriteMovie;
    private ToggleButton toggleButton;
    View informationMovie,informationTvShow;
    private ArrayList<MovieApi> movies;
    private ArrayList<Movies> moviesFavorite;
    private ArrayList<TvShows> tvShowsFavorite;
    private ArrayList<TvShowApi> tvShows;

    private boolean toggleIsChecked;



    public static final String EXTRA_MOVIE ="movie";
    public static final String EXTRA_TVSHOW = "tvShow";
    public static final String EXTRA_POSITION ="position";
    public static final String EXTRA_FROM = "from";
    public static final String EXTRA_FROM_MOVIE = "from_movie";
    public static final String EXTRA_FROM_TVSHOW = "from_tvshow";
    public static final String EXTRA_CHECKED = "checked";
    public static final String EXTRA_FAVORITE_MOVIE ="favorite_movie";
    public static final String EXTRA_FAVORITE_TVSHOW = "favorite_tvshow";

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
        toggleIsChecked = getIntent().getBooleanExtra(EXTRA_CHECKED,false);
        favoriteMovie = getIntent().getStringExtra(EXTRA_FAVORITE_MOVIE);

        if(from.equals(EXTRA_FROM_MOVIE)){

            informationMovie.setVisibility(View.VISIBLE);
            informationTvShow.setVisibility(View.GONE);

            movies = getIntent().getParcelableArrayListExtra(EXTRA_MOVIE);
            Picasso.with(this).load(Constants.START_IMAGE+ movies.get(position).getPoster_path()).into(imgPhoto);
            tvTitle.setText(movies.get(position).getTitle());
            tvDate.setText(movies.get(position).getRelease_date());
            tvDescription.setText(movies.get(position).getOverview());
            tvPopularity.setText(String.format("%s%%",movies.get(position).getPopularity()));
            tvOriginalLang.setText(movies.get(position).getOriginal_language());
            tvVoteCount.setText(String.format("%s%%",movies.get(position).getVote_count()));
            tvVoteAverage.setText(String.format("%s%%",movies.get(position).getVote_average()));

            imgPhoto.setContentDescription(movies.get(position).getTitle());

            if(movies.get(position).isAdult()){
                tvAdult.setText("Yes");
            }else {
                tvAdult.setText("No");
            }


            setActionBarTitle(getString(R.string.detail_movie));


        }else if(from.equals(EXTRA_FAVORITE_MOVIE)) {
            informationMovie.setVisibility(View.VISIBLE);
            informationTvShow.setVisibility(View.GONE);

            moviesFavorite = getIntent().getParcelableArrayListExtra(EXTRA_MOVIE);
            Picasso.with(this).load(Constants.START_IMAGE+ moviesFavorite.get(position).getPoster_path()).into(imgPhoto);
            tvTitle.setText(moviesFavorite.get(position).getTitle());
            tvDate.setText(moviesFavorite.get(position).getRelease_date());
            tvDescription.setText(moviesFavorite.get(position).getOverview());
            tvPopularity.setText(String.format("%s%%",moviesFavorite.get(position).getPopularity()));
            tvOriginalLang.setText(moviesFavorite.get(position).getOriginal_language());
            tvVoteCount.setText(String.format("%s%%",moviesFavorite.get(position).getVote_count()));
            tvVoteAverage.setText(String.format("%s%%",moviesFavorite.get(position).getVote_average()));

            imgPhoto.setContentDescription(moviesFavorite.get(position).getTitle());

            if(moviesFavorite.get(position).isAdult()){
                tvAdult.setText("Yes");
            }else {
                tvAdult.setText("No");
            }


            setActionBarTitle(getString(R.string.detail_movie));

        }else if(from.equals(EXTRA_FAVORITE_TVSHOW)){
            informationMovie.setVisibility(View.GONE);
            informationTvShow.setVisibility(View.VISIBLE);

            tvShowsFavorite = getIntent().getParcelableArrayListExtra(EXTRA_TVSHOW);
            Picasso.with(this).load(Constants.START_IMAGE+ tvShowsFavorite.get(position).getPoster_path()).into(imgPhoto);
            tvTitle.setText(tvShowsFavorite.get(position).getName());
            tvDate.setText(tvShowsFavorite.get(position).getFirst_air_date());
            tvDescription.setText(tvShowsFavorite.get(position).getOverview());
            tvVoteAverageTvShow.setText(String.format("%s%%",tvShowsFavorite.get(position).getVote_average()));
            tvVoteCountTvShow.setText(String.format("%s%%",tvShowsFavorite.get(position).getVote_count()));
            tvPopularityTvShow.setText(String.format("%s%%",tvShowsFavorite.get(position).getPopularity()));
            tvOriginalLangTvShow.setText(tvShowsFavorite.get(position).getOriginal_language());

            imgPhoto.setContentDescription(tvShowsFavorite.get(position).getName());
            setActionBarTitle(getString(R.string.detail_tvshow));

        }
        else{

            informationMovie.setVisibility(View.GONE);
            informationTvShow.setVisibility(View.VISIBLE);

            tvShows = getIntent().getParcelableArrayListExtra(EXTRA_TVSHOW);
            Picasso.with(this).load(Constants.START_IMAGE+ tvShows.get(position).getPoster_path()).into(imgPhoto);
            tvTitle.setText(tvShows.get(position).getName());
            tvDate.setText(tvShows.get(position).getFirst_air_date());
            tvDescription.setText(tvShows.get(position).getOverview());
            tvVoteAverageTvShow.setText(String.format("%s%%",tvShows.get(position).getVote_average()));
            tvVoteCountTvShow.setText(String.format("%s%%",tvShows.get(position).getVote_count()));
            tvPopularityTvShow.setText(String.format("%s%%",tvShows.get(position).getPopularity()));
            tvOriginalLangTvShow.setText(tvShows.get(position).getOriginal_language());


            imgPhoto.setContentDescription(tvShows.get(position).getName());
            setActionBarTitle(getString(R.string.detail_tvshow));

        }
        if(toggleIsChecked){
            toggleButton.setChecked(toggleIsChecked);
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(from.equals(EXTRA_FROM_MOVIE)) {
                        Movies moviesDb = new Movies();
                        moviesDb.setId(movies.get(position).getId());
                        moviesDb.setAdult(movies.get(position).isAdult());
                        moviesDb.setOriginal_language(movies.get(position).getOriginal_language());
                        moviesDb.setOverview(movies.get(position).getOverview());
                        moviesDb.setPopularity(movies.get(position).getPopularity());
                        moviesDb.setPoster_path(movies.get(position).getPoster_path());
                        moviesDb.setRelease_date(movies.get(position).getRelease_date());
                        moviesDb.setTitle(movies.get(position).getTitle());
                        moviesDb.setVote_average(movies.get(position).getVote_average());
                        moviesDb.setVote_count(movies.get(position).getVote_count());

                        MainActivity.myMoviesDataBase.myDao().addMovies(moviesDb);
                    }else if(from.equals(EXTRA_FAVORITE_MOVIE)){
                        Movies moviesDb = new Movies();
                        moviesDb.setId(moviesFavorite.get(position).getId());
                        moviesDb.setAdult(moviesFavorite.get(position).isAdult());
                        moviesDb.setOriginal_language(moviesFavorite.get(position).getOriginal_language());
                        moviesDb.setOverview(moviesFavorite.get(position).getOverview());
                        moviesDb.setPopularity(moviesFavorite.get(position).getPopularity());
                        moviesDb.setPoster_path(moviesFavorite.get(position).getPoster_path());
                        moviesDb.setRelease_date(moviesFavorite.get(position).getRelease_date());
                        moviesDb.setTitle(moviesFavorite.get(position).getTitle());
                        moviesDb.setVote_average(moviesFavorite.get(position).getVote_average());
                        moviesDb.setVote_count(moviesFavorite.get(position).getVote_count());

                        MainActivity.myMoviesDataBase.myDao().addMovies(moviesDb);

                    }else if(from.equals(EXTRA_FROM_TVSHOW)){
                        TvShows tvShowsDb = new TvShows();
                        tvShowsDb.setId(tvShows.get(position).getId());
                        tvShowsDb.setFirst_air_date(tvShows.get(position).getFirst_air_date());
                        tvShowsDb.setName(tvShows.get(position).getName());
                        tvShowsDb.setOriginal_language(tvShows.get(position).getOriginal_language());
                        tvShowsDb.setOverview(tvShows.get(position).getOverview());
                        tvShowsDb.setPopularity(tvShows.get(position).getPopularity());
                        tvShowsDb.setPoster_path(tvShows.get(position).getPoster_path());
                        tvShowsDb.setVote_average(tvShows.get(position).getVote_average());
                        tvShowsDb.setVote_count(tvShows.get(position).getVote_count());

                        MainActivity.myMoviesDataBase.tvShowDao().addTvShow(tvShowsDb);

                    }else if(from.equals(EXTRA_FAVORITE_TVSHOW)){
                        TvShows tvShowsDb = new TvShows();
                        tvShowsDb.setId(tvShowsFavorite.get(position).getId());
                        tvShowsDb.setFirst_air_date(tvShowsFavorite.get(position).getFirst_air_date());
                        tvShowsDb.setName(tvShowsFavorite.get(position).getName());
                        tvShowsDb.setOriginal_language(tvShowsFavorite.get(position).getOriginal_language());
                        tvShowsDb.setOverview(tvShowsFavorite.get(position).getOverview());
                        tvShowsDb.setPopularity(tvShowsFavorite.get(position).getPopularity());
                        tvShowsDb.setPoster_path(tvShowsFavorite.get(position).getPoster_path());
                        tvShowsDb.setVote_average(tvShowsFavorite.get(position).getVote_average());
                        tvShowsDb.setVote_count(tvShowsFavorite.get(position).getVote_count());

                        MainActivity.myMoviesDataBase.tvShowDao().addTvShow(tvShowsDb);
                    }
                }else{
                    if(from.equals(EXTRA_FROM_MOVIE)){
                        Movies moviesDb = new Movies();
                        moviesDb.setId(movies.get(position).getId());
                        MainActivity.myMoviesDataBase.myDao().deleteMovies(moviesDb);

                    }else if(from.equals(EXTRA_FAVORITE_MOVIE)){
                        Movies moviesDb = new Movies();
                        moviesDb.setId(moviesFavorite.get(position).getId());
                        MainActivity.myMoviesDataBase.myDao().deleteMovies(moviesDb);
                        MovieFavoriteFragment.movieListFavorite.remove(position);

                    }else if(from.equals(EXTRA_FROM_TVSHOW)){
                        TvShows tvShowsDb = new TvShows();
                        tvShowsDb.setId(tvShows.get(position).getId());
                        MainActivity.myMoviesDataBase.tvShowDao().deleteTvShows(tvShowsDb);

                    }else{
                        TvShows tvShowsDb = new TvShows();
                        tvShowsDb.setId(tvShowsFavorite.get(position).getId());
                        MainActivity.myMoviesDataBase.tvShowDao().deleteTvShows(tvShowsDb);
                        TvShowFavoriteFragment.tvShowListFavorite.remove(position);
                    }

                }
            }
        });


    }

    private void initId(){
        toggleButton = findViewById(R.id.toggleButton);
        imgPhoto = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tvTitleDetail);
        tvDate = findViewById(R.id.tvDateDetail);
        tvDescription = findViewById(R.id.tvDescriptionDetail);
        tvOriginalLang = findViewById(R.id.tv_ori_lang);
        tvOriginalLangTvShow = findViewById(R.id.tv_ori_lang_tvShow);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
