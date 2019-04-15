package com.example.alberto.prueba_m2o2.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReceipeList {

    @SerializedName("results")
    private ArrayList<Receipe> receipeList;

    public ArrayList<Receipe> getReceipeList() {
        return receipeList;
    }

    public void setReceipeArrayList(ArrayList<Receipe> receipeArrayList) {
        this.receipeList = receipeArrayList;
    }
}
