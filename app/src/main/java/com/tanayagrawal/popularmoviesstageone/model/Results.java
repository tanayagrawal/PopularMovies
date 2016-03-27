package com.tanayagrawal.popularmoviesstageone.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tanayagrawal on 27/03/16.
 */
public class Results {

    @SerializedName("results")
    private ArrayList<MovieInformation> results;

    public ArrayList<MovieInformation> getResults() {
        if (results != null) {
            return results;
        }
        return new ArrayList<MovieInformation>();
    }

    public void setResults(ArrayList<MovieInformation> results) {
        this.results = results;
    }
}
