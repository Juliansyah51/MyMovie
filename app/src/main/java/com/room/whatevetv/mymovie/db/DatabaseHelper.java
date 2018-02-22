package com.room.whatevetv.mymovie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.room.whatevetv.mymovie.db.DatabaseMovie.TABLE_MOVIE;

/**
 * Created by Samsung PC on 2/20/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "database_movie";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s INTEGER NOT NULL," +
                    " %s DOUBLE NOT NULL," +
                    " %s DOUBLE NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_MOVIE,
            DatabaseMovie.MovieColumns._ID,
            DatabaseMovie.MovieColumns.VOTE_COUNT,
            DatabaseMovie.MovieColumns.VOTE_AVERAGE,
            DatabaseMovie.MovieColumns.POPULARITY,
            DatabaseMovie.MovieColumns.TITLE,
            DatabaseMovie.MovieColumns.POSTER_PATH,
            DatabaseMovie.MovieColumns.ORIGINAL_TITLE,
            DatabaseMovie.MovieColumns.ORIGINAL_LANGUAGE,
            DatabaseMovie.MovieColumns.OVERVIEW,
            DatabaseMovie.MovieColumns.RELEASE_DATE
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
}
