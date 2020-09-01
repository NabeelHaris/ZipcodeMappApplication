package com.akdeveloper.bootcamapplication.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akdeveloper.bootcamapplication.R;
import com.akdeveloper.bootcamapplication.models.Devlops;

public class LocationsViewHolder extends RecyclerView.ViewHolder {

    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;

    public LocationsViewHolder(@NonNull View itemView) {
        super(itemView);

        locationImage = (ImageView)itemView.findViewById(R.id.location_img);
        locationTitle = (TextView)itemView.findViewById(R.id.location_title);
        locationAddress = (TextView)itemView.findViewById(R.id.location_address);
    }

    public void updateUI(Devlops location) {
        String uri = location.getImgUrl();
        int resource = locationImage.getResources().getIdentifier(uri, null, locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationTitle.setText(location.getLocationTitle());
        locationAddress.setText(location.getLocationAddress());
    }
}
