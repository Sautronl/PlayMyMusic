package com.example.poto.playmymusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    private MediaPlayer mMedia;
    int id,saveId;
    ArrayList<Integer> sonPlay;
    private ArrayList<Integer> stockSon = new ArrayList<>();
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Bundle extra = getIntent().getBundleExtra("extra");
        sonPlay = (ArrayList<Integer>) extra.getSerializable("array");

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        saveId = intent.getIntExtra("category",0);

        playMusic(view);
    }

    public void playMusic(View v){

        if (mMedia == null){
            if (id == sonPlay.size()){
                Toast.makeText(this, "tu as fait le tour gros", Toast.LENGTH_SHORT).show();
                stockSon.clear();
                id = 0;
            }
                stockSon.add(id);
                mMedia = MediaPlayer.create(PlayActivity.this,sonPlay.get(id));
                mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        next(view);
                    }
                });
        }
        mMedia.start();
    }

    public void loopMusic(View v){
        if (mMedia != null){
            mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }

    public void nextMusic(View v){
        if (mMedia !=null){
            stopMyMusic();
            id = id+1;
            playMusic(v);
        }
    }

    public void next(View v){
        nextMusic(v);
    }

    public void pauseMusic(View v){
        if (mMedia != null){
            mMedia.pause();
        }
    }

    public void stopMusic(View v){
        stopMyMusic();
    }

    private void stopMyMusic(){
        if (mMedia != null){
            mMedia.release();
            mMedia = null;
        }
    }

    @Override
    public void onBackPressed() {
        stopMyMusic();
        Intent backIntent = new Intent(PlayActivity.this,ListSongActivity.class);
        backIntent.putExtra("name",saveId);
        startActivity(backIntent);
    }
}
