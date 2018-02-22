package com.room.whatevetv.mymovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.room.whatevetv.mymovie.R;
import com.room.whatevetv.mymovie.db.MovieHelper;
import com.room.whatevetv.mymovie.entity.Favorite;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity {

    @BindView(R.id.titleDetail)
    TextView textViewTitle;
    @BindView(R.id.releaseDetail)
    TextView textViewRelease;
    @BindView(R.id.descDetail)
    TextView textViewDesc;
    @BindView(R.id.imageDetail)
    ImageView imageView;
    @BindView(R.id.favoriteDetail)
    ImageView imageViewFavorite;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    
    private MovieHelper helper;
    private Favorite param;

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

        param = getIntent().getParcelableExtra("param");
        if (param != null) {
            setTitle(param.getTitle());
            String image = param.getPosterPath().replace("\"", "");
            Picasso.with(this).load("http://image.tmdb.org/t/p/w500/" + image).into(imageView);
            textViewTitle.setText(param.getTitle());
            textViewDesc.setText(param.getOverview());
            textViewRelease.setText(param.getReleaseDate());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
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
