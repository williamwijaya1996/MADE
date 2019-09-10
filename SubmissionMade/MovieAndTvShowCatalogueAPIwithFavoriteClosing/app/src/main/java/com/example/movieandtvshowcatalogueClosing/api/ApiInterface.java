package com.example.movieandtvshowcatalogueClosing.api;

import com.example.movieandtvshowcatalogueClosing.model.MovieApiResponse;
import com.example.movieandtvshowcatalogueClosing.model.TvShowApiResponse;

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

    @GET("search/movie")
    Call<MovieApiResponse> apiSearchMovie(@Query("api_key") String api_key,
                                          @Query("language") String language,
                                          @Query("query") String movie_name);

    @GET("search/tv")
    Call<TvShowApiResponse> apiSearchTvShow(@Query("api_key") String api_key,
                                            @Query("language") String language,
                                            @Query("query") String movie_name);

    @GET("discover/movie")
    Call<MovieApiResponse> apiReleaseMovie(@Query("api_key") String apiKey,
                                           @Query("primary_release_date.gte") String dategte,
                                           @Query("primary_release_date.lte") String datelte);
}
