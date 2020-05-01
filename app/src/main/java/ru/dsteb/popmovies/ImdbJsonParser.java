package ru.dsteb.popmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ru.dsteb.popmovies.model.Movie;
import ru.dsteb.popmovies.model.Page;

public class ImdbJsonParser {

    private static final String TAG = ImdbJsonParser.class.getCanonicalName();

    private static final String TOTAL_PAGES = "total_pages";
    private static final String RESULTS = "results";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "release_date";
    private static final String POSTER_PATH = "poster_path";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String OVERVIEW = "overview";

    private static final String POSTER_URI = "http://image.tmdb.org/t/p/w185";

    public Page parsePage(String jsonString) {
        try {
            JSONObject pageJson = new JSONObject(jsonString);
            int totalPages = pageJson.getInt(TOTAL_PAGES);
            JSONArray results = pageJson.getJSONArray(RESULTS);
            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < results.length(); ++i) {
                JSONObject movieJson = results.getJSONObject(i);
                String title = movieJson.getString(TITLE);
                LocalDate releaseDate = LocalDate.parse(movieJson.getString(RELEASE_DATE));
                String posterPath = movieJson.getString(POSTER_PATH);
                String posterUri = POSTER_URI + posterPath;
                Double voteAverage = movieJson.getDouble(VOTE_AVERAGE);
                String overview = movieJson.getString(OVERVIEW);
                Movie movie = new Movie(title, releaseDate, posterUri, voteAverage, overview);
                movies.add(movie);
            }
            return new Page(totalPages, movies);
        } catch (JSONException e) {
            Log.w(TAG, "onPostExecute: Error parsing IMDB response JSON", e);
        }
        return new Page(0, new ArrayList<>());
    }
}
