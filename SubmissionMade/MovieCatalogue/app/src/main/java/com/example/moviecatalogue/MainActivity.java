package com.example.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.moviecatalogue.Adapter.MovieAdapter;
import com.example.moviecatalogue.Model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] dataJudul, dataDescription, dataRuntime, dataBudget, dataRevenue, dataTanggal,dataGenre;
    private int[] dataRating;
    private TypedArray dataPhoto;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent moveWithMovieObject  = new Intent(MainActivity.this,MovieDetailActivity.class);
                moveWithMovieObject.putExtra(MovieDetailActivity.EXTRA_MOVIE,movies);
                moveWithMovieObject.putExtra(MovieDetailActivity.EXTRA_POSITION,position);
                startActivity(moveWithMovieObject);
            }
        });
    }

    private void init() {
        listView = findViewById(R.id.lv_list);
        adapter = new MovieAdapter(this);
        listView.setAdapter(adapter);

        dataJudul = getResources().getStringArray(R.array.movie_name);
        dataDescription = getResources().getStringArray(R.array.description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        dataRating = getResources().getIntArray(R.array.rating);
        dataRuntime = getResources().getStringArray(R.array.runtime);
        dataBudget = getResources().getStringArray(R.array.budget);
        dataRevenue = getResources().getStringArray(R.array.revenue);
        dataTanggal = getResources().getStringArray(R.array.tanggal);
        dataGenre = getResources().getStringArray(R.array.genre);
    }

    private void addItem() {

        movies = new ArrayList<>();

        for (int i = 0; i < dataJudul.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setBudget(dataBudget[i]);
            movie.setDescription(dataDescription[i]);
            movie.setJudul(dataJudul[i]);
            movie.setRating(dataRating[i]);
            movie.setRevenue(dataRevenue[i]);
            movie.setRuntime(dataRuntime[i]);
            movie.setTanggal(dataTanggal[i]);
            movie.setGenre(dataGenre[i]);

            movies.add(movie);
        }
        adapter.setMovies(movies);
    }

}
