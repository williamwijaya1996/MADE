package com.example.movieandtvshowcatalogueClosing.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieandtvshowcatalogueClosing.R;
import com.example.movieandtvshowcatalogueClosing.config.Constants;
import com.example.movieandtvshowcatalogueClosing.roomdb.TvShows;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTvShowFavoriteAdapter extends RecyclerView.Adapter<ListTvShowFavoriteAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<TvShows> listTvShow = new ArrayList<>();

    public ListTvShowFavoriteAdapter(ArrayList<TvShows> listTvShows, Context context){
        listTvShow.clear();
        listTvShow.addAll(listTvShows);
        this.context = context;
        this.notifyDataSetChanged();
    }

    public ArrayList<TvShows> getListTvShow() {
        return listTvShow;
    }

    @NonNull
    @Override
    public ListTvShowFavoriteAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies,viewGroup,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTvShowFavoriteAdapter.CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.tvTitle.setText(getListTvShow().get(i).getName());
        categoryViewHolder.tvDate.setText(getListTvShow().get(i).getFirst_air_date());
        categoryViewHolder.tvPopularity.setText(String.format("%s%%",getListTvShow().get(i).getPopularity()));
        Picasso.with(context).load(Constants.START_IMAGE+getListTvShow().get(i).getPoster_path())
                .into(categoryViewHolder.imgPhoto);

        categoryViewHolder.imgPhoto.setContentDescription(getListTvShow().get(i).getName());
    }

    @Override
    public int getItemCount() {
       return getListTvShow().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDate,  tvPopularity;
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
