package com.movile.next.seriestracker;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.util.EpisodeItemAdapter;
import com.movile.next.seriestracker.view.SeasonDetailsPresenter;
import com.movile.next.seriestracker.view.SeasonDetailsView;


public class SeasonDetailsActivity extends Activity implements SeasonDetailsView {
    public static final String TAG = SeasonDetailsActivity.class.getSimpleName();

    private SeasonDetailsPresenter mPresenter;

    @Override
    public void displaySeason(Show show) {
//        ListView view = (ListView) findViewById(R.id.episode_list_view);
//        EpisodeItemAdapter adapter = new EpisodeItemAdapter(this, R.id.episode_item, null);
//        view.setAdapter(adapter);

        ((TextView) findViewById(R.id.season_first_aired_year)).setText(show.year().toString());
        ((TextView) findViewById(R.id.season_detail_rating))
                .setText(String.format("%.1f", show.rating()));

        ImageView image_show = (ImageView) findViewById(R.id.season_details_show);
        Glide
                .with(this)
                .load(show.images().poster().get(Images.ImageSize.MEDIUM))
                .placeholder(R.drawable.season_details_show_placeholder)
                .centerCrop()
                .into(image_show);

        ImageView screenshot = (ImageView) findViewById(R.id.season_details_screenshot);
        Glide
            .with(this)
            .load(show.images().fanart().get(Images.ImageSize.MEDIUM))
            .placeholder(R.drawable.highlight_placeholder)
            .centerCrop()
            .into(screenshot);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        mPresenter = new SeasonDetailsPresenter(this);
        mPresenter.fetchSeasonDetails("game-of-thrones");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.season_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
