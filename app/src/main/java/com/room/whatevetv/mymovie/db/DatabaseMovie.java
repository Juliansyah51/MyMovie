package com.room.whatevetv.mymovie.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Samsung PC on 2/20/2018.
 */

public class DatabaseMovie {

    public static String TABLE_MOVIE = "movie";

    public static final class MovieColumns implements BaseColumns {
        public static String VOTE_COUNT = "vote_count";
        public static String VOTE_AVERAGE = "vote_average";
        public static String TITLE = "title";
        public static String POPULARITY = "popularity";
        public static String POSTER_PATH = "poster_path";
        public static String ORIGINAL_LANGUAGE = "original_language";
        public static String ORIGINAL_TITLE = "original_title";
        public static String OVERVIEW = "overview";
        public static String RELEASE_DATE = "release_date";
    }

    public static final String AUTHORITY = "com.room.whatevetv.mymovie";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("movie")
            .authority(AUTHORITY)
            .appendPath(TABLE_MOVIE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
