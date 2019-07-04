package com.example.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.Model.Movie;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tRuntime, tBudget, tRevenue, tGenre, tDescription, tRating, tJudul, tTanggal;
    private ImageView imgFoto;

    public static final String EXTRA_MOVIE = "movie";
    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        init();

        setActionBarTitle("Movie Detail");
        ArrayList<Movie> movies = getIntent().getParcelableArrayListExtra(EXTRA_MOVIE);
        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);

        tBudget.setText(movies.get(position).getBudget());
        tDescription.setText(movies.get(position).getDescription());
        tGenre.setText(movies.get(position).getGenre());
        tJudul.setText(movies.get(position).getJudul());
        tRating.setText(movies.get(position).getRating()+"%");
        tRevenue.setText(movies.get(position).getRevenue());
        tRuntime.setText(movies.get(position).getRuntime());
        tTanggal.setText(movies.get(position).getTanggal());
        imgFoto.setImageResource(movies.get(position).getPhoto());

    }

    private void init(){
        tBudget = findViewById(R.id.tvBudget);
        tDescription = findViewById(R.id.tvDescription);
        tGenre = findViewById(R.id.tvGenre);
        tJudul = findViewById(R.id.tvJudul);
        tRating = findViewById(R.id.tvRating);
        tRevenue = findViewById(R.id.tvRevenue);
        tRuntime = findViewById(R.id.tvRuntime);
        tTanggal = findViewById(R.id.tvTanggal);
        imgFoto = findViewById(R.id.imgPhoto);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
