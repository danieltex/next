package com.movile.next.seriestracker.view;

import com.movile.next.seriestracker.business.SeasonRemoteService;
import com.movile.next.seriestracker.util.ApiConfiguration;

import retrofit.RestAdapter;

/**
 * Created by danieltex on 21/06/15.
 */
public class SeasonDetailsPresenter {
    private SeasonDetailsView mView;
    private RestAdapter mAdapter;
    private SeasonRemoteService mService;
    private SeasonDetailsCallback mCallback;

    public SeasonDetailsPresenter(SeasonDetailsView view) {
        mView = view;
        mAdapter = new RestAdapter.Builder().setEndpoint(ApiConfiguration.API_URL_BASE).build();
        mService = mAdapter.create(SeasonRemoteService.class);
        mCallback = new SeasonDetailsCallback(mView);
    }
    public void fetchSeasonDetails(String show){
        mService.getSeasonDetails(show, mCallback);
    }
}
