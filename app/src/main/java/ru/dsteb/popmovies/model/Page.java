package ru.dsteb.popmovies.model;

import java.util.List;

public class Page {
    private int totalResults;
    private List<Movie> movies;
    public Page(int totalResults, List<Movie> movies) {
        this.totalResults = totalResults;
        this.movies = movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
