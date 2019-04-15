package com.example.alberto.prueba_m2o2.activities.mainActivity;


import com.example.alberto.prueba_m2o2.model.Receipe;

public interface RecyclerItemClickListener {
    void onItemClick(Receipe receipe);

    void onLinkClick(String url);
}