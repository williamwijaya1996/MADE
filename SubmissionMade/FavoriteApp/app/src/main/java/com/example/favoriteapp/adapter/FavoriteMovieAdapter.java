package com.example.favoriteapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.favoriteapp.R;
import com.example.favoriteapp.config.Constants;
import com.example.favoriteapp.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.CategoryViewHolder>{

    private Context context;
    private ArrayList<Movies> listMovie = new ArrayList<>();


    public FavoriteMovieAdapter(Context context) {

        this.context = context;
        this.notifyDataSetChanged();
    }

    public void setListData(ArrayList<Movies> listMovies) {
        listMovie.clear();
        listMovie.addAll(listMovies);
        this.listMovie = listMovies;
    }

    public ArrayList<Movies> getListMovie() {
        return listMovie;
    }

    @Override
    public FavoriteMovieAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies,viewGroup,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(FavoriteMovieAdapter.CategoryViewHolder categoryViewHolder, int i) {
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

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_title);
            tvDate = itemView.findViewById(R.id.text_date);
            tvPopularity = itemView.findViewById(R.id.text_popularity);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
