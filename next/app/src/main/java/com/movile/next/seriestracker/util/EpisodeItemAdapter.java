package com.movile.next.seriestracker.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Episode;


/**
 * Created by danieltex on 21/06/15.
 */
public class EpisodeItemAdapter extends ArrayAdapter<Episode> {

    public EpisodeItemAdapter(Context context, int resource, Episode[] values) {
        super(context, resource, values);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resource = R.layout.episode_item;
//            if (type == TYPE_TBA) {
//                resource = R.layout.episode_item_tba;
//            }
            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position, type);

        return view;

    }

    private void populateViewFromHolder(ViewHolder holder, int position, int type)
    {
        Episode episode = getItem(position);
        holder.episodeItemNumber().setText(episode.number().toString());
        holder.episodeItemTitle().setText(episode.title());
    }

//    public int getViewTypeCount() {  }
//    public int getItemViewType(int position) {  }

    public static class ViewHolder {
        private View mView;
        private TextView mEpisodeItemNumber;
        private TextView mEpisodeItemTitle;

        public View view() { return mView; }
        public TextView episodeItemNumber() { return mEpisodeItemNumber; }
        public TextView episodeItemTitle() { return mEpisodeItemTitle; }

        public ViewHolder(View root) {
            mView = root;
            mEpisodeItemNumber = (TextView) root.findViewById(R.id.episode_item_number);
            mEpisodeItemTitle = (TextView) root.findViewById(R.id.episode_item_title);
        }


    }
}
