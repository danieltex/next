package com.movile.next.seriestracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class EpisodeDetailsActivity extends Activity {
    private static String TAG = "EpisodeDetail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.episode_details_card_layout);
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    private void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    private void onResume {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    private void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    private void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    private void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    private void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
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
