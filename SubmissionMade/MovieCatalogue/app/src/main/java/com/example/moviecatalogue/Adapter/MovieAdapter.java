package com.example.moviecatalogue.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.Model.Movie;
import com.example.moviecatalogue.R;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movies, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);

        return convertView;
    }

    private class ViewHolder {
        private TextView txtJudul, txtTanggal, txtRating, txtGenre;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            txtJudul = view.findViewById(R.id.text_judul);
            txtTanggal = view.findViewById(R.id.text_tanggal);
            txtRating = view.findViewById(R.id.rating);
            imgPhoto = view.findViewById(R.id.img_photo);
            txtGenre = view.findViewById(R.id.text_genre);
        }

        void bind(Movie movie) {
            txtJudul.setText(movie.getJudul());
            txtRating.setText(movie.getRating() + "%");
            txtTanggal.setText(movie.getTanggal());
            imgPhoto.setImageResource(movie.getPhoto());
            txtGenre.setText(movie.getGenre());
        }
    }
}
