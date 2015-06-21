package com.movile.next.seriestracker.view;

import android.util.Log;

import com.movile.next.seriestracker.SeasonDetailsActivity;
import com.movile.next.seriestracker.model.Season;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by danieltex on 21/06/15.
 */
public class SeasonDetailsCallback implements Callback<Season> {
    private SeasonDetailsView mSeasonDetails;

    public SeasonDetailsCallback(SeasonDetailsView seasonDetails) { mSeasonDetails = seasonDetails; }

    @Override
    public void success(Season season, Response response)
    {
        mSeasonDetails.displaySeason(season);
    }

    @Override
    public void failure(RetrofitError error)
    {
        Log.e(SeasonDetailsActivity.TAG, "Error fetching episode", error.getCause());
    }
}
