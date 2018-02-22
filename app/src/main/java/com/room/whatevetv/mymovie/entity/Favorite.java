package com.room.whatevetv.mymovie.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.room.whatevetv.mymovie.db.DatabaseMovie;

import static android.provider.BaseColumns._ID;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.getColumnDouble;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.getColumnInt;
import static com.room.whatevetv.mymovie.db.DatabaseMovie.getColumnString;

/**
 * Created by Samsung PC on 2/21/2018.
 */

public class Favorite implements Parcelable {

    private int id;
    private int voteCount;
    private double voteAverage;
    private double popularity;
    private String title;
    private String posterPath;
    private String originalTitle;
    private String originalLanguage;
    private String overview;
    private String releaseDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Favorite() {

    }

    public Favorite(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.voteCount = getColumnInt(cursor, DatabaseMovie.MovieColumns.VOTE_COUNT);
        this.voteAverage = getColumnDouble(cursor, DatabaseMovie.MovieColumns.VOTE_AVERAGE);
        this.popularity = getColumnDouble(cursor, DatabaseMovie.MovieColumns.POPULARITY);
        this.title = getColumnString(cursor, DatabaseMovie.MovieColumns.TITLE);
        this.posterPath = getColumnString(cursor, DatabaseMovie.MovieColumns.POSTER_PATH);
        this.originalTitle = getColumnString(cursor, DatabaseMovie.MovieColumns.ORIGINAL_TITLE);
        this.originalLanguage = getColumnString(cursor, DatabaseMovie.MovieColumns.ORIGINAL_LANGUAGE);
        this.overview = getColumnString(cursor, DatabaseMovie.MovieColumns.OVERVIEW);
        this.releaseDate = getColumnString(cursor, DatabaseMovie.MovieColumns.RELEASE_DATE);
    }

    protected Favorite(Parcel in) {
        id = in.readInt();
        voteCount = in.readInt();
        voteAverage = in.readDouble();
        popularity = in.readDouble();
        title = in.readString();
        posterPath = in.readString();
        originalTitle = in.readString();
        originalLanguage = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(voteCount);
        parcel.writeDouble(voteAverage);
        parcel.writeDouble(popularity);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(originalTitle);
        parcel.writeString(originalLanguage);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
    }
}
