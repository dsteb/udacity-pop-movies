package ru.dsteb.popmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.util.Optional;

public class Movie implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(releaseDateOption.map(d -> d.toString()).orElse(""));
        dest.writeString(posterUri);
        dest.writeDouble(voteAverage);
        dest.writeString(overview);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Movie(Parcel in) {
        title = in.readString();
        String releaseDate = in.readString();
        if (releaseDate.isEmpty()) {
            releaseDateOption = Optional.empty();
        } else {
            releaseDateOption = Optional.of(LocalDate.parse(releaseDate));
        }
        posterUri = in.readString();
        voteAverage = in.readDouble();
        overview = in.readString();
    }
}
