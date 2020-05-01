package ru.dsteb.popmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int GRID_COLUMNS_COUNT = 2;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
    }
}