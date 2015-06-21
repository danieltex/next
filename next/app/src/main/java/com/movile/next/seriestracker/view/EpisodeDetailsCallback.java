package com.movile.next.seriestracker.view;

import android.util.Log;

import com.movile.next.seriestracker.EpisodeDetailsActivity;
import com.movile.next.seriestracker.model.Episode;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by danieltex on 21/06/15.
 */
public class EpisodeDetailsCallback implements Callback<Episode>{
    EpisodeDetailsView mView;

    public EpisodeDetailsCallback (EpisodeDetailsView view) {
        mView = view;
    }

    @Override
    public void success(Episode episode, Response response) {
        mView.displayEpisode(episode);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(EpisodeDetailsActivity.TAG, "Error fetching episode", error.getCause());
    }
}
