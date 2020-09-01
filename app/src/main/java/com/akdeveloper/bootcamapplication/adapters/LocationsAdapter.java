package com.akdeveloper.bootcamapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akdeveloper.bootcamapplication.R;
import com.akdeveloper.bootcamapplication.holders.LocationsViewHolder;
import com.akdeveloper.bootcamapplication.models.Devlops;

import java.util.ArrayList;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsViewHolder> {

    private ArrayList<Devlops> locations;

    public LocationsAdapter(ArrayList<Devlops> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public LocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_locations, parent, false);
        return new LocationsViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsViewHolder holder, int position) {
        final Devlops location = locations.get(position);
        holder.updateUI(location);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load details page
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
