package com.example.myviewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.myviewmodel.adapter.WeatherAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {
    private WeatherAdapter adapter;
    private EditText edtCity;
    private ProgressBar progressBar;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getWeathers().observe(this, new android.arch.lifecycle.Observer<ArrayList<WeatherItems>>() {
            @Override
            public void onChanged(@Nullable ArrayList<WeatherItems> weatherItems) {
                if (weatherItems != null) {
                    adapter.setData(weatherItems);
                }
            }
        });

        adapter = new WeatherAdapter();
        adapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        edtCity = findViewById(R.id.editCity);
        progressBar = findViewById(R.id.progressBar);

        findViewById(R.id.btnCity).setOnClickListener(myListener);
    }



    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String city = edtCity.getText().toString();
            if (TextUtils.isEmpty(city)) return;
            mainViewModel.setWeather(city);
            showLoading(true);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
