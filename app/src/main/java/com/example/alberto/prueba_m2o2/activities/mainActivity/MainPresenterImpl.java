package com.example.alberto.prueba_m2o2.activities.mainActivity;

import com.example.alberto.prueba_m2o2.model.Receipe;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetReceipeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetReceipeIntractor getReceipeIntractor;
    private String textToSearch;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetReceipeIntractor getReceipeIntractor, String textToSearch) {
        this.mainView = mainView;
        this.getReceipeIntractor = getReceipeIntractor;
        this.textToSearch = textToSearch;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void showLoading() {

        if(mainView != null){
            mainView.showProgress();
        }
    }

    @Override
    public void requestDataFromServer(String textToSearch) {
        getReceipeIntractor.getReceipeArrayList(this, textToSearch);
    }


    @Override
    public void onFinished(ArrayList<Receipe> receipes) {
        if(mainView != null && receipes.size()>0){
            mainView.setDataToRecyclerView(receipes);
            mainView.hideProgress();
        }
        else{
            mainView.noResults();
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
