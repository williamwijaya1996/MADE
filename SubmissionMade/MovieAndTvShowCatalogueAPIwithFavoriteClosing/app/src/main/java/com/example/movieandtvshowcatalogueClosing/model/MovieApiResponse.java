package com.example.movieandtvshowcatalogueClosing.model;

import java.util.List;

public class MovieApiResponse {
    private List<MovieApi> results;

    public MovieApiResponse(List<MovieApi> results) {
        this.results = results;
    }

    public List<MovieApi> getResults() {
        return results;
    }

    public void setResults(List<MovieApi> results) {
        this.results = results;
    }
}
