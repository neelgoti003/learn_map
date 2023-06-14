package com.example.learnmap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResponse {
    @SerializedName("results")
    private List<Modelmap> results;

    public PlaceResponse(List<Modelmap> results) {
        this.results = results;
    }

    public List<Modelmap> getResults() {
        return results;
    }
}
