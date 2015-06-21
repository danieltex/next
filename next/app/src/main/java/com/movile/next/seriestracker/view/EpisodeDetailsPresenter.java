package com.movile.next.seriestracker.view;

import android.util.Log;

import com.movile.next.seriestracker.business.EpisodeRemoteService;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.util.ApiConfiguration;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by danieltex on 21/06/15.
 */
public class EpisodeDetailsPresenter {
    private EpisodeDetailsView mView;
    private RestAdapter mAdapter;
    private EpisodeRemoteService mService;
    private EpisodeDetailsCallback mCallback;

    public EpisodeDetailsPresenter(EpisodeDetailsView view) {
        mView = view;
        mAdapter = new RestAdapter.Builder().setEndpoint(ApiConfiguration.API_URL_BASE).build();
        mService = mAdapter.create(EpisodeRemoteService.class);
        mCallback = new EpisodeDetailsCallback(mView);
    }
    public void fetchEpisodeDetails(String show, Long season, Long episode){
        mService.getEpisodeDetails(show, season, episode, mCallback);
    }
}