package ru.dsteb.popmovies;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ImdbApiClient {

    private static final String TAG = ImdbApiClient.class.getCanonicalName();

    private static final String API_KEY_PARAM = "api_key";
    private static final String PAGE_PARAM = "page";

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private static final String POP_URL = BASE_URL + "popular";
    private static final String TOP_RATED_URL = BASE_URL + "top_rated";

    private OkHttpClient client = new OkHttpClient();


//    private static final String API_URL = "https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1";
//    private static final String POP_URL = "https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}&page=${PAGE}";

    private final String apiKey;

    public ImdbApiClient() {
        apiKey = BuildConfig.imdbAPIKey;
    }

    public String getMostPopular(int page) {
        return getResponse(POP_URL, page);
    }

    public String getTopRated(int page) {
        return getResponse(TOP_RATED_URL, page);
    }

    private String getResponse(String baseUrl, int page) {
        HttpUrl url = HttpUrl.parse(baseUrl)
                .newBuilder()
                .addQueryParameter(API_KEY_PARAM, apiKey)
                .addQueryParameter(PAGE_PARAM, String.valueOf(page))
                .build();
        Request req = new Request.Builder()
                .url(url)
                .build();

        try {
            return client.newCall(req).execute().body().string();
        } catch (IOException e) {
            Log.w(TAG, "getResponse: Failed to get movies from IMDB API", e);
            return null;
        }
    }
}
