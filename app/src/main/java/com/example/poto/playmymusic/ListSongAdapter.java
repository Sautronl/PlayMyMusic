package com.example.poto.playmymusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.Viewholder>{

    private Activity activity;
    private ArrayList<MusicModel> musicModels;

    public ListSongAdapter(Activity activity,ArrayList<MusicModel> musicModels) {
        this.activity = activity;
        this.musicModels=musicModels;
    }

    @NonNull
    @Override
    public ListSongAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_son_item, parent, false);

        return new Viewholder(view);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    @Override
    public void onBindViewHolder(ListSongAdapter.Viewholder viewholder, final int position) {
        viewholder.name.setText(musicModels.get(position).getTitle());
        viewholder.image.setImageResource(musicModels.get(position).getImageId());
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(activity,PlayActivity.class);
                playIntent.putExtra("id",position);
                playIntent.putExtra("category",musicModels.get(position).getCategory());
                playIntent.putExtra("musicM", musicModels);
                activity.startActivity(playIntent);
            }
        });
    }


    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public Viewholder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameSon);
            image = itemView.findViewById(R.id.imageSon);
        }
    }
}
