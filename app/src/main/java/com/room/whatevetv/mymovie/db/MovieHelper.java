package com.room.whatevetv.mymovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.room.whatevetv.mymovie.entity.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.ORIGINAL_LANGUAGE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.ORIGINAL_TITLE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.OVERVIEW;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.POPULARITY;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.POSTER_PATH;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.RELEASE_DATE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.TITLE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.VOTE_AVERAGE;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.MovieColumns.VOTE_COUNT;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.TABLE_MOVIE;

/**
 * Created by Samsung PC on 2/20/2018.
 */

public class MovieHelper {

    private static String DATABASE_TABLE = TABLE_MOVIE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<Movie> query() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, _ID + " DESC"
                , null);
        cursor.moveToFirst();
        Movie Movie;
        if (cursor.getCount() > 0) {
            do {

                Movie = new Movie();
                Movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                Movie.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(VOTE_COUNT)));
                Movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                Movie.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(POPULARITY)));
                Movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                Movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                Movie.setOriginalTitle(cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_TITLE)));
                Movie.setOriginalLanguage(cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_LANGUAGE)));
                Movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                Movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));

                arrayList.add(Movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insert(Movie Movie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(VOTE_COUNT, Movie.getVoteCount());
        initialValues.put(VOTE_AVERAGE, Movie.getVoteAverage());
        initialValues.put(POPULARITY, Movie.getPopularity());
        initialValues.put(TITLE, Movie.getTitle());
        initialValues.put(POSTER_PATH, Movie.getPosterPath());
        initialValues.put(ORIGINAL_TITLE, Movie.getOriginalTitle());
        initialValues.put(ORIGINAL_LANGUAGE, Movie.getOriginalLanguage());
        initialValues.put(OVERVIEW, Movie.getOverview());
        initialValues.put(RELEASE_DATE, Movie.getReleaseDate());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Movie Movie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(VOTE_COUNT, Movie.getVoteCount());
        initialValues.put(VOTE_AVERAGE, Movie.getVoteAverage());
        initialValues.put(POPULARITY, Movie.getPopularity());
        initialValues.put(TITLE, Movie.getTitle());
        initialValues.put(POSTER_PATH, Movie.getPosterPath());
        initialValues.put(ORIGINAL_TITLE, Movie.getOriginalTitle());
        initialValues.put(ORIGINAL_LANGUAGE, Movie.getOriginalLanguage());
        initialValues.put(OVERVIEW, Movie.getOverview());
        initialValues.put(RELEASE_DATE, Movie.getReleaseDate());
        return database.update(DATABASE_TABLE, initialValues, _ID + "= '" + Movie.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_MOVIE, _ID + " = '" + id + "'", null);
    }

    public Cursor queryByTitleProvider(String title) {
        return database.query(DATABASE_TABLE, null
                , TITLE + " = ?"
                , new String[]{title}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }

    public int deleteProviderByTitle(String title) {
        return database.delete(DATABASE_TABLE, TITLE + " = ?", new String[]{title});
    }
}


