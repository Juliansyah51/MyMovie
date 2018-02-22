package com.room.whatevetv.mymovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.room.whatevetv.mymovie.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostersActivity extends AppCompatActivity {

    @BindView(R.id.imagePosters)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_posters);
        ButterKnife.bind(this);

        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+getIntent().getStringExtra("poster")).into(imageView);
    }
}
