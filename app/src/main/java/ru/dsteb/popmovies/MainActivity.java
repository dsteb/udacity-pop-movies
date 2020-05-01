package ru.dsteb.popmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

import ru.dsteb.popmovies.model.Movie;
import ru.dsteb.popmovies.model.Page;
import ru.dsteb.popmovies.model.SortEnum;

public class MainActivity extends AppCompatActivity implements ImdbAdapter.ImdbAdapterOnclickHandler {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    private static final String TAG = MainActivity.class.getCanonicalName();

    private static final int GRID_COLUMNS_COUNT = 2;


    private RecyclerView mRecyclerView;
    private ImdbAdapter mAdapter;
    private ImdbJsonParser jsonParser = new ImdbJsonParser();

    private int lastPageLoaded = 1;
    private int totalPages = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_movies);
        // All grid items are the same size, so we optimize our RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a grid layout manager with 2 columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, GRID_COLUMNS_COUNT);
        mRecyclerView.setLayoutManager(layoutManager);

        // IMDB Adapter
        mAdapter = new ImdbAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (totalPages > 0 && lastPageLoaded  >= totalPages) {
                    Log.d(TAG, "onLoadMore: We loaded all the pages " + lastPageLoaded + " / " + totalPages);
                } else {
                    ++lastPageLoaded;
                    Log.d(TAG, "onLoadMore: Loading the next page " + lastPageLoaded + " / " + totalPages);
                    new RetreiveMoviesTask().execute(lastPageLoaded);
                }
            }
        });

        new RetreiveMoviesTask().execute(lastPageLoaded);
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    class RetreiveMoviesTask extends AsyncTask<Integer, Void, String> {

        private ImdbApiClient apiClient = new ImdbApiClient();

        @Override
        protected String doInBackground(Integer... args) {
            // Query IMDB
            lastPageLoaded = args[0];
            String s = apiClient.getMostPopular(lastPageLoaded);
            Log.d(TAG, "loaded page: " + lastPageLoaded);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            Page page = jsonParser.parsePage(s);
            totalPages = page.getTotalResults();
            mAdapter.addData(page.getMovies());
            mAdapter.notifyDataSetChanged();
            super.onPostExecute(s);
        }
    }
}
