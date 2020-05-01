package ru.dsteb.popmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.dsteb.popmovies.model.Page;
import ru.dsteb.popmovies.model.SortEnum;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private static final int GRID_COLUMNS_COUNT = 2;

    private RecyclerView mRecyclerView;
    private ImdbAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImdbJsonParser jsonParser = new ImdbJsonParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_movies);
        // All grid items are the same size, so we optimize our RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a grid layout manager with 2 columns
        mLayoutManager = new GridLayoutManager(this, GRID_COLUMNS_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // IMDB Adapter
        mAdapter = new ImdbAdapter();
        mRecyclerView.setAdapter(mAdapter);

        new RetreiveMoviesTask().execute(SortEnum.POPULAR);
    }

    class RetreiveMoviesTask extends AsyncTask<SortEnum, Void, String> {

        private ImdbApiClient apiClient = new ImdbApiClient();

        @Override
        protected String doInBackground(SortEnum... sortEnums) {
            // Query IMDB
            String x = apiClient.getMostPopular(1);
            Log.d(TAG, "onCreate: " + x);
            return x;
        }

        @Override
        protected void onPostExecute(String s) {
            Page page = jsonParser.parsePage(s);
            mAdapter.setItemCount(page.getTotalResults());
            mAdapter.addData(page.getMovies());
            mAdapter.notifyDataSetChanged();
            super.onPostExecute(s);
        }
    }
}
