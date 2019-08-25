package com.example.movieandtvshowcatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.config.Constants;
import com.example.movieandtvshowcatalogue.roomdb.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListMovieFavoriteAdapter extends RecyclerView.Adapter<ListMovieFavoriteAdapter.CategoryViewHolder>{

    private Context context;
    private ArrayList<Movies> listMovie = new ArrayList<>();


    public ListMovieFavoriteAdapter(ArrayList<Movies> listMovies,Context context) {
        listMovie.clear();
        listMovie.addAll(listMovies);
        this.context = context;
        this.notifyDataSetChanged();
    }


    public ArrayList<Movies> getListMovie() {
        return listMovie;
    }

    @NonNull
    @Override
    public ListMovieFavoriteAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies,viewGroup,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieFavoriteAdapter.CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.tvTitle.setText(getListMovie().get(i).getTitle());
        categoryViewHolder.tvDate.setText(getListMovie().get(i).getRelease_date());
        categoryViewHolder.tvPopularity.setText(String.format("%s%%",getListMovie().get(i).getPopularity()));
        Picasso.with(context).load(Constants.START_IMAGE +getListMovie().get(i).getPoster_path())
                .into(categoryViewHolder.imgPhoto);
        categoryViewHolder.imgPhoto.setContentDescription(getListMovie().get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDate, tvPopularity;
        private ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_title);
            tvDate = itemView.findViewById(R.id.text_date);
            tvPopularity = itemView.findViewById(R.id.text_popularity);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
