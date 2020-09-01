package com.akdeveloper.bootcamapplication.services;

import com.akdeveloper.bootcamapplication.models.Devlops;

import java.util.ArrayList;

public class DataService {

    private static DataService instance = new DataService();

    public static DataService getInstance() {
        return instance;
    }

    private DataService() {

    }

    public ArrayList<Devlops> getBootCampLocationWithin10MilesZip(int zipcode) {
        ArrayList<Devlops> list = new ArrayList<>();
        list.add(new Devlops(35.279f,-120.663f,"Downtoen", "762 Higuera street, San Luis Obispo, CA 93401", "slo"));
        list.add(new Devlops(35.302f,-120.658f,"On The Campus", "Grand Ave, San Luis Obispo, CA 93401", "slo"));
        list.add(new Devlops(35.267f,-120.652f,"East Side Tower", "2494 Victoria Ave, San Luis Obispo, CA 93401", "slo"));
        return list;
    }

}
