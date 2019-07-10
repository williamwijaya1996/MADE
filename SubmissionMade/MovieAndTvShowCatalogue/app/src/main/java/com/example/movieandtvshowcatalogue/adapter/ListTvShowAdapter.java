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
import com.example.movieandtvshowcatalogue.model.TvShow;

import java.util.ArrayList;

public class ListTvShowAdapter extends RecyclerView.Adapter<ListTvShowAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<TvShow> listTvShow;

    public ListTvShowAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<TvShow> getListTvShow() {
        return listTvShow;
    }

    public void setListTvShow(ArrayList<TvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies,viewGroup,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {

        categoryViewHolder.tvTitle.setText(getListTvShow().get(i).getTitle());
        categoryViewHolder.tvDate.setText(getListTvShow().get(i).getDate());
        categoryViewHolder.tvRating.setText(getListTvShow().get(i).getRating()+"%");
        categoryViewHolder.tvGenre.setText(getListTvShow().get(i).getGenre());
        categoryViewHolder.imgPhoto.setImageResource(getListTvShow().get(i).getPhoto());
    }

    @Override
    public int getItemCount() {
        return getListTvShow().size();
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
