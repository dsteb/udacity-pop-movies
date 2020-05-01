package ru.dsteb.popmovies.model;

import java.time.LocalDate;
import java.util.Optional;

public class Movie {
    private String title;
    private Optional<LocalDate> releaseDateOption;
    private String posterUri;
    private double voteAverage;
    private String overview;

    public Movie(String title, Optional<LocalDate> releaseDateOption, String posterUri, double voteAverage, String overview) {
        this.title = title;
        this.releaseDateOption = releaseDateOption;
        this.posterUri = posterUri;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public Optional<LocalDate> getReleaseDateOption() {
        return releaseDateOption;
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
