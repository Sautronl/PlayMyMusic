package com.example.poto.playmymusic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListSongAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<MusicModel> musicModels;

    public ListSongAdapter(Activity activity,ArrayList<MusicModel> musicModels) {
        this.activity = activity;
        this.musicModels=musicModels;
    }

    @Override
    public int getCount() {
        return musicModels.size();
    }

    @Override
    public Object getItem(int i) {
        return musicModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_son_item,null);

        TextView txtName = (TextView)itemView.findViewById(R.id.nameSon);
        txtName.setText(musicModels.get(i).getTitle());

        return itemView;
    }
}
