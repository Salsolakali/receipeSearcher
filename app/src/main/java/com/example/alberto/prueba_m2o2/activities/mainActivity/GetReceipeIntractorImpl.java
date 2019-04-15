package com.example.alberto.prueba_m2o2.activities.mainActivity;

import android.util.Log;

import com.example.alberto.prueba_m2o2.model.ReceipeList;
import com.example.alberto.prueba_m2o2.customInterface.GetReceipeDataService;
import com.example.alberto.prueba_m2o2.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bpn on 12/7/17.
 */

public class GetReceipeIntractorImpl implements MainContract.GetReceipeIntractor {

    @Override
    public void getReceipeArrayList(final OnFinishedListener onFinishedListener, String textToSearch) {

        GetReceipeDataService service = RetrofitInstance.getRetrofitInstance().create(GetReceipeDataService.class);

        Call<ReceipeList> call = service.getReceipeData(textToSearch);


        Log.e("URL ALBERTO", call.request().url() + "");

        call.enqueue(new Callback<ReceipeList>() {
            @Override
            public void onResponse(Call<ReceipeList> call, Response<ReceipeList> response) {
                if(response.body()!=null)
               onFinishedListener.onFinished(response.body().getReceipeList());
                else{

                }
            }

            @Override
            public void onFailure(Call<ReceipeList> call, Throwable t) {
            }

        });
    }
}
