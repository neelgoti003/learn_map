package com.example.learnmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.libraries.places.api.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    public PlaceAdapter (List<Modelmap> places, Context context){
        this.places = places;
        this.context = context;
    }

    private List<Modelmap> places ;
    private Context context;

  /*  public void setPlaces(List<Modelmap> places) {
        this.places=places;
    }*/

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Modelmap modelmap = places.get(position);
        holder.nameTextView.setText(modelmap.getName());
        holder.addressTextView.setText(modelmap.getAddress());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView addressTextView;

        PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewPlaceName);
            addressTextView = itemView.findViewById(R.id.textViewAddress);
        }
    }
}