package com.example.alberto.prueba_m2o2.activities.mainActivity;

import com.example.alberto.prueba_m2o2.model.Receipe;

import java.util.ArrayList;

public interface MainContract {

    interface presenter{

        void onDestroy();

        void showLoading();

        void requestDataFromServer(String textToSearch);

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Receipe> receipeArrayList);

        void onResponseFailure(Throwable throwable);

        void noResults();
    }


    interface GetReceipeIntractor {

        void getReceipeArrayList(OnFinishedListener onFinishedListener, String textToSearch);

        interface OnFinishedListener {

            void onFinished(ArrayList<Receipe> receipes);

            void onFailure(Throwable t);
        }
    }
}
