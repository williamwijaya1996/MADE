package com.example.movieandtvshowcatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieandtvshowcatalogue.model.Movie;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView tvRuntime,tvBudget,tRevenue,tvGenre,tvDescription,tvRating,tvTitle,tvDate;
    private ImageView imgPhoto;
    private int position;

    public static final String EXTRA_MOVIE ="movie";
    public static final String EXTRA_POSITION ="position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initId();
        init();
    }

    private void init(){
        ArrayList<Movie> movies = getIntent().getParcelableArrayListExtra(EXTRA_MOVIE);
        position = getIntent().getIntExtra(EXTRA_POSITION,0);

        imgPhoto.setImageResource(movies.get(position).getPhoto());
        tvTitle.setText(movies.get(position).getTitle());
        tvDate.setText(movies.get(position).getDate());
        tvDescription.setText(movies.get(position).getDescription());
        tvRating.setText(movies.get(position).getRating()+"%");

    }

    private void initId(){
        imgPhoto = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tvTitleDetail);
        tvDate = findViewById(R.id.tvDateDetail);
        tvDescription = findViewById(R.id.tvDescriptionDetail);
        tvRating = findViewById(R.id.tvRatingDetail);

    }
}
