package com.akdeveloper.bootcamapplication.fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.akdeveloper.bootcamapplication.R;
import com.akdeveloper.bootcamapplication.models.Devlops;
import com.akdeveloper.bootcamapplication.services.DataService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions userMarker;
    private LocationsListFragment mListFragment;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mListFragment = (LocationsListFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.container_location_list);

        if (mListFragment == null) {
            mListFragment = LocationsListFragment.newInstance();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_location_list, mListFragment)
                    .commit();
        }

        final EditText zipText = (EditText)view.findViewById(R.id.zip_text);
        zipText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getDeviceId() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER){
                    String text = zipText.getText().toString();
                    int zip = Integer.parseInt(text);

                    InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(zipText.getWindowToken(), 0);

                    showList();
                    updateMapForZip(zip);
                    return true;
                }
                return false;
            }
        });

        hideList();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void setUserMarker(LatLng latLng) {
        if (userMarker == null) {
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            mMap.addMarker(userMarker);
            Log.v("MAPS", "Current Location: " + latLng.latitude + "Long: " + latLng.longitude);
        }

        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            int zip = Integer.parseInt(addresses.get(0).getPostalCode());
            updateMapForZip(zip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateMapForZip(92284);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

    }

    private void updateMapForZip(int zipCode) {

        ArrayList<Devlops> locations = DataService.getInstance().getBootCampLocationWithin10MilesZip(zipCode);

        for (int j =0; j < locations.size(); j++) {
            Devlops loc = locations.get(j);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude()));
            marker.title(loc.getLocationTitle());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            mMap.addMarker(marker);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void hideList() {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().hide(mListFragment).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showList() {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().show(mListFragment).commit();
    }
}