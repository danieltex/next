package com.movile.next.seriestracker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.util.FormatUtil;
import com.movile.next.seriestracker.business.FetchLocalEpisodeDetails;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

interface OnOperationListener {
    public void onOperationSuccess(Episode episode);
    public void onOperationSuccess(Bitmap bitmap);
}

public class EpisodeDetailsActivity extends Activity implements OnOperationListener{

    public class OperationAsyncTaskEpisode extends AsyncTask<Void, Void, Episode> {
        private EpisodeDetailsActivity mListener;

        public OperationAsyncTaskEpisode(EpisodeDetailsActivity listener) { mListener = listener; }

        protected Episode doInBackground(Void ... params) { return new FetchLocalEpisodeDetails().get(mListener); }
        protected void onPostExecute(Episode episode) { mListener.onOperationSuccess(episode); }
    }

    public class RemoteImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private EpisodeDetailsActivity mListener;

        public RemoteImageAsyncTask(EpisodeDetailsActivity listener) { mListener = listener; }

        protected Bitmap doInBackground(String ... params) {
            String url = params[0];
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
            } catch (FileNotFoundException e) {
                Log.e(TAG, "Image not available on " + url, e);
            } catch (IOException e) {
                Log.e(TAG, "Error fetching image from " + url, e);
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mListener.onOperationSuccess(bitmap);
        }
    }

    @Override
    public void onOperationSuccess(Episode episode)
    {
        ((TextView) findViewById(R.id.episode_details_title)).setText(episode.title());
        ((TextView) findViewById(R.id.episode_details_overview)).setText(episode.overview());

        Date date_aired = FormatUtil.formatDate(episode.firstAired());
        ((TextView) findViewById(R.id.episode_details_first_aired)).setText(FormatUtil.formatDate(date_aired));

        String screenshotURI = episode.images().screenshot().get(Images.ImageSize.MEDIUM);
        new RemoteImageAsyncTask(this).execute(screenshotURI);
    }

    @Override
    public void onOperationSuccess(Bitmap bitmap)
    {
        if (bitmap != null) {
            Log.i(TAG, "setting bitmap");
            ((ImageView) findViewById(R.id.episode_details_screenshot)).setImageBitmap(bitmap);
        }
    }

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.episode_details_card_layout);
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        new OperationAsyncTaskEpisode(this).execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onSaveInstanceState");
//        savedInstanceState.putString("episode_details_title", "BLABLABLABLA");
//        savedInstanceState.putInt("blablabla", 0);
        super.onSaveInstanceState(savedInstanceState);
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
//        TextView episode_title = (TextView) findViewById(R.id.episode_details_title);
//        episode_title.setText(savedInstanceState.getString("episode_details_title"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.episode_details_menu, menu);
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
