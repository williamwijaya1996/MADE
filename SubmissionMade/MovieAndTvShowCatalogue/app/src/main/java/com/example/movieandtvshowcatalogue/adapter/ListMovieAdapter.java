package com.example.movieandtvshowcatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.model.Movie;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.CategoryViewHolder> {


    //private Context context;
    public ArrayList<Movie> listMovie;

    /*public ListMovieAdapter(Context context) {
        this.context = context;
    }*/

    public ListMovieAdapter(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies,viewGroup,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.tvTitle.setText(getListMovie().get(i).getTitle());
        categoryViewHolder.tvDate.setText(getListMovie().get(i).getDate());
        categoryViewHolder.tvRating.setText(getListMovie().get(i).getRating()+"%");
        categoryViewHolder.tvGenre.setText(getListMovie().get(i).getGenre());
       /* Glide.with(context)
                .load(getListMovie().get(i).getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(categoryViewHolder.imgPhoto);*/
        categoryViewHolder.imgPhoto.setImageResource(getListMovie().get(i).getPhoto());

    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDate, tvRating, tvGenre;
        private ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_title);
            tvDate = itemView.findViewById(R.id.text_date);
            tvRating = itemView.findViewById(R.id.rating);
            tvGenre = itemView.findViewById(R.id.text_genre);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
