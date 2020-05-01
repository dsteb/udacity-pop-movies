package ru.dsteb.popmovies.model;

import java.time.LocalDate;

public class Movie {
    private String title;
    private LocalDate releaseDate;
    private String posterUri;
    private double voteAverage;
    private String overview;

    public Movie(String title, LocalDate releaseDate, String posterUri, double voteAverage, String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUri = posterUri;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUri() {
        return posterUri;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }
}
