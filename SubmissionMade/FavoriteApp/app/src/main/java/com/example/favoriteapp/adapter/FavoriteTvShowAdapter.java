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
import com.example.favoriteapp.model.TvShows;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FavoriteTvShowAdapter extends RecyclerView.Adapter<FavoriteTvShowAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<TvShows> listTvShow = new ArrayList<>();

    public FavoriteTvShowAdapter( Context context){
        this.context = context;
        this.notifyDataSetChanged();
    }

    public void setListTvShow(ArrayList<TvShows> listTvShows) {
        listTvShow.clear();
        listTvShow.addAll(listTvShows);
        this.listTvShow = listTvShows;
    }

    @Override
    public FavoriteTvShowAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies,viewGroup,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(FavoriteTvShowAdapter.CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.tvTitle.setText(listTvShow.get(i).getName());
        categoryViewHolder.tvDate.setText(listTvShow.get(i).getRelease_air_date());
        categoryViewHolder.tvPopularity.setText(String.format("%s%%",listTvShow.get(i).getPopularity()));
        Picasso.with(context).load(Constants.START_IMAGE+listTvShow.get(i).getPoster_path())
                .into(categoryViewHolder.imgPhoto);

        categoryViewHolder.imgPhoto.setContentDescription(listTvShow.get(i).getName());
    }

    @Override
    public int getItemCount() {
       return listTvShow.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDate,  tvPopularity;
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
