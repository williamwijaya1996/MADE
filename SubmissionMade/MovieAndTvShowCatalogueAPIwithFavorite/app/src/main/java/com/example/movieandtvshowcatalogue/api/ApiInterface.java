package com.example.movieandtvshowcatalogue.api;

import com.example.movieandtvshowcatalogue.model.MovieApiResponse;
import com.example.movieandtvshowcatalogue.model.TvShowApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("movie/upcoming")
    Call<MovieApiResponse> apiMovie(@Query("api_key") String apiKey,
                                    @Query("language") String language,
                                    @Query("page") int page,
                                    @Query("region") String region);

    @GET("tv/top_rated")
    Call<TvShowApiResponse> apiTvShow(@Query("api_key") String apiKey,
                                      @Query("language") String language,
                                      @Query("page") int page);
}
