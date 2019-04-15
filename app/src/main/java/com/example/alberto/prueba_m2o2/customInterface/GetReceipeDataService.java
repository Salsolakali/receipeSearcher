package com.example.alberto.prueba_m2o2.customInterface;

import com.example.alberto.prueba_m2o2.model.ReceipeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetReceipeDataService {

    @GET("api/")
    Call<ReceipeList> getReceipeData(@Query("q") String textToSearch);

}