package com.tanayagrawal.popularmoviesstageone.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tanayagrawal on 27/03/16.
 */
public class MovieInformation implements Serializable {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("id")
    private int id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float voteAverage;

    public String getPosterPath() {
        if (posterPath != null) {
            return posterPath;
        }
        return "";
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        if (overview != null) {
            return overview;
        }
        return "";
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        if (releaseDate != null) {
            return releaseDate;
        }
        return "";
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getMovieId() {
        return id;
    }

    public void setMovieId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        if (originalTitle != null) {
            return originalTitle;
        }
        return "";
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        if (originalLanguage != null) {
            return originalLanguage;
        }
        return "";
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        if (title != null) {
            return title;
        }
        return "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        if (backdropPath != null) {
            return backdropPath;
        }
        return "";
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }
}
