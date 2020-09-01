package com.akdeveloper.bootcamapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akdeveloper.bootcamapplication.R;
import com.akdeveloper.bootcamapplication.adapters.LocationsAdapter;
import com.akdeveloper.bootcamapplication.services.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationsListFragment extends Fragment {


    public LocationsListFragment() {
        // Required empty public constructor
    }

    public static LocationsListFragment newInstance() {
        LocationsListFragment fragment = new LocationsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_locations__list, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_locations);
        recyclerView.setHasFixedSize(true);

        LocationsAdapter adapter = new LocationsAdapter(DataService.getInstance().getBootCampLocationWithin10MilesZip(92284));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}