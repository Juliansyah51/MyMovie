package com.room.whatevetv.favorite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.room.whatevetv.favorite.DatabaseFavorite.MovieColumns.OVERVIEW;
import static com.room.whatevetv.favorite.DatabaseFavorite.MovieColumns.POSTER_PATH;
import static com.room.whatevetv.favorite.DatabaseFavorite.MovieColumns.RELEASE_DATE;
import static com.room.whatevetv.favorite.DatabaseFavorite.MovieColumns.TITLE;
import static com.room.whatevetv.favorite.DatabaseFavorite.getColumnString;

/**
 * Created by Samsung PC on 2/21/2018.
 */

public class FavoriteAdapter extends CursorAdapter {

    public FavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            ImageView imageView = view.findViewById(R.id.itemImage);
            TextView textViewTitle = view.findViewById(R.id.itemTitle);
            TextView textViewDesc = view.findViewById(R.id.itemDesc);
            TextView textViewDate = view.findViewById(R.id.itemDate);

            textViewTitle.setText(getColumnString(cursor, TITLE));
            textViewDesc.setText(getColumnString(cursor, OVERVIEW));
            textViewDate.setText(getColumnString(cursor, RELEASE_DATE));
            String image = getColumnString(cursor, POSTER_PATH).replace("\"", "");
            Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + image).into(imageView);
        }
    }
}