package ru.dsteb.popmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.dsteb.popmovies.model.Movie;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EXTRA_MOVIE)) {
            Movie movie = intent.getParcelableExtra(MainActivity.EXTRA_MOVIE);

            TextView titleTextView = findViewById(R.id.tv_title);
            titleTextView.setText(movie.getTitle());

            ImageView posterImageView = findViewById(R.id.iv_detail_poster);
            Picasso.get()
                    .load(Uri.parse(movie.getPosterUri()))
                    .into(posterImageView);

            TextView releaseYearTextView = findViewById(R.id.tv_release_year);
            String releaseYear = movie.getReleaseDateOption()
                    .map(d -> String.valueOf(d.getYear()))
                    .orElse("-");
            releaseYearTextView.setText(releaseYear);

            TextView voteAvgTextView = findViewById(R.id.tv_vote_avg);
            voteAvgTextView.setText(movie.getVoteAverage() + " / 10");

            TextView overviewTextView = findViewById(R.id.tv_overview);
            overviewTextView.setText(movie.getOverview());
        }
    }
}