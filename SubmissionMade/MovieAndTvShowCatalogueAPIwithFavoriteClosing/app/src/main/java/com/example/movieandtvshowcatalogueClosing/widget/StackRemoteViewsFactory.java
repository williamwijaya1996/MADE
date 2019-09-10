package com.example.movieandtvshowcatalogueClosing.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.movieandtvshowcatalogueClosing.ImagesFavoriteWidget;
import com.example.movieandtvshowcatalogueClosing.MainActivity;
import com.example.movieandtvshowcatalogueClosing.R;
import com.example.movieandtvshowcatalogueClosing.config.Constants;
import com.example.movieandtvshowcatalogueClosing.roomdb.Movies;
import com.example.movieandtvshowcatalogueClosing.roomdb.TvShows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<Bitmap> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    private List<TvShows> tvShowsListFavorite = MainActivity.myMoviesDataBase.tvShowDao().getTvshows();
    private List<Movies> moviesListFavorite = MainActivity.myMoviesDataBase.myDao().getMovies();
    private List<TvShows> favoriteImages = new ArrayList<>();
    private  List<Movies> moviesFavorite = new ArrayList<>();
    public StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        favoriteImages.clear();
        moviesFavorite.clear();
        favoriteImages.addAll(tvShowsListFavorite);
        moviesFavorite.addAll(moviesListFavorite);
        for(int i =0; i<favoriteImages.size();i++){
            try{
                URL url = new URL(Constants.START_IMAGE+tvShowsListFavorite.get(i).getPoster_path());
                mWidgetItems.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
            }catch (MalformedURLException e){

            }catch (IOException e){

            }

        }
        for (int i =0;i<moviesFavorite.size();i++){
            try{
                URL url = new URL(Constants.START_IMAGE+moviesListFavorite.get(i).getPoster_path());
                mWidgetItems.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
            }catch (MalformedURLException e){

            }catch (IOException e){

            }
        }


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
       return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems.get(position));
        Bundle extras = new Bundle();
        extras.putInt(ImagesFavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        //rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
