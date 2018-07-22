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
    private ArrayList<Integer> sonId;
    private ArrayList<String> sonTitle;



    public ListSongAdapter(Activity activity,ArrayList<Integer> sonId,ArrayList<String> sonTitle) {
        this.activity = activity;
        this.sonId = sonId;
        this.sonTitle=sonTitle;
    }

    @Override
    public int getCount() {
        return sonId.size();
    }

    @Override
    public Object getItem(int i) {
        return sonId.get(i);
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
        txtName.setText(sonTitle.get(i));

        return itemView;
    }
}
