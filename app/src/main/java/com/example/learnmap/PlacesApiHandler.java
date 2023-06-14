package com.example.learnmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;
import okhttp3.ResponseBody;


public class PlacesApiHandler {
    private static final String TAG = "PlacesApiHandler";
    private static final String API_KEY = "AIzaSyDXwEXgdkbS2F0g6TT-eZ-iGfgIBIpT-AY"; // Replace with your Google Places API key

    public interface PlacesApiCallback {
        void onSuccess(List<Modelmap> places);

        void onFailure(String errorMessage);
    }

    public void getNearbyPlaces(String placeType, double latitude, double longitude, final PlacesApiCallback callback) {
        String url = buildUrl(placeType, latitude, longitude);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "Request failed: " + e.getMessage());
                callback.onFailure(e.getMessage());
            }



            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new GsonBuilder().create();
                    PlaceResponse placeResponse = gson.fromJson(responseBody, PlaceResponse.class);
                    if (placeResponse != null && placeResponse.getResults() != null) {
                        callback.onSuccess(placeResponse.getResults());
                    } else {
                        callback.onFailure("No places found");
                    }
                } else {
                    callback.onFailure("Request failed: " + response.code());
                }
            }
        });
    }

    private String buildUrl(String placeType, double latitude, double longitude) {
        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                "location=" + latitude + "," + longitude +
                "&radius=1000" +
                "&type=" + placeType +
                "&key=" + "AIzaSyDXwEXgdkbS2F0g6TT-eZ-iGfgIBIpT-AY";
    }
}
