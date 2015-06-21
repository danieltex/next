package com.movile.next.seriestracker.view;

import android.util.Log;

import com.movile.next.seriestracker.SeasonDetailsActivity;
import com.movile.next.seriestracker.model.Show;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by danieltex on 21/06/15.
 */
public class SeasonDetailsCallback implements Callback<Show> {
    private SeasonDetailsView mSeasonDetails;

    public SeasonDetailsCallback(SeasonDetailsView seasonDetails) { mSeasonDetails = seasonDetails; }

    @Override
    public void success(Show show, Response response)
    {
        mSeasonDetails.displaySeason(show);
    }

    @Override
    public void failure(RetrofitError error)
    {
        Log.e(SeasonDetailsActivity.TAG, "Error fetching episode", error.getCause());
    }
}
