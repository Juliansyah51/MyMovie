package com.room.whatevetv.mymovie.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.room.whatevetv.mymovie.R;
import com.room.whatevetv.mymovie.db.MovieHelper;
import com.room.whatevetv.mymovie.entity.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.ORIGINAL_LANGUAGE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.ORIGINAL_TITLE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.OVERVIEW;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.POPULARITY;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.POSTER_PATH;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.RELEASE_DATE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.TITLE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.VOTE_AVERAGE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.VOTE_COUNT;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.titleDetail)
    TextView textViewTitle;
    @BindView(R.id.releaseDetail)
    TextView textViewRelease;
    @BindView(R.id.descDetail) 
    TextView textViewDesc;
    @BindView(R.id.imageDetail)
    ImageView imageView;
    @BindView(R.id.favoriteDetail) 
    ImageView imageViewFavourite;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MovieHelper helper;
    private Cursor currentCursor;
    private Movie param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        helper = new MovieHelper(this);
        helper.open();

        param = getIntent().getParcelableExtra("movie");
        if (param != null) {
            setTitle(param.getTitle());
            String image = param.getPosterPath().replace("\"", "");
            Picasso.with(this).load("http://image.tmdb.org/t/p/w500/" + image).into(imageView);
            textViewTitle.setText(param.getTitle());
            textViewDesc.setText(param.getOverview());
            textViewRelease.setText(param.getReleaseDate());

            currentCursor = helper.queryByTitleProvider(param.getTitle());
            if (currentCursor.getCount() != 0) {
                imageViewFavourite.setBackgroundResource(R.drawable.ic_star);
            } else {
                imageViewFavourite.setBackgroundResource(R.drawable.ic_star_border);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
        }
    }

    @OnClick({R.id.favoriteDetail})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.favoriteDetail:
                currentCursor = helper.queryByTitleProvider(param.getTitle());
                if (currentCursor.getCount() != 0) {
                    int delete = helper.deleteProviderByTitle(param.getTitle());
                    if (delete != 0) {
                        Toast.makeText(this, "Deleted Success", Toast.LENGTH_SHORT).show();
                        imageViewFavourite.setBackgroundResource(R.drawable.ic_star_border);
                    } else {
                        Toast.makeText(this, "Deleted Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ContentValues values = new ContentValues();
                    values.put(VOTE_COUNT, param.getVoteCount());
                    values.put(VOTE_AVERAGE, param.getVoteAverage());
                    values.put(POPULARITY, param.getPopularity());
                    values.put(TITLE, param.getTitle());
                    values.put(POSTER_PATH, param.getPosterPath());
                    values.put(ORIGINAL_TITLE, param.getOriginalTitle());
                    values.put(ORIGINAL_LANGUAGE, param.getOriginalLanguage());
                    values.put(OVERVIEW, param.getOverview());
                    values.put(RELEASE_DATE, param.getReleaseDate());

                    long save = helper.insertProvider(values);
                    if (save != 0L) {
                        Toast.makeText(this, "Added Success", Toast.LENGTH_SHORT).show();
                        imageViewFavourite.setBackgroundResource(R.drawable.ic_star);
                    } else {
                        Toast.makeText(this, "Added Error", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
