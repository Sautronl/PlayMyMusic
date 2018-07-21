package com.example.poto.playmymusic;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mMedia;
    ArrayList<Integer> son = new ArrayList<>();
    ArrayList<Integer> stock = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listRaw();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    public void listRaw() throws IllegalAccessException {
        Field[] fields = R.raw.class.getFields();
        for (int count = 0; count < fields.length; count++) {
            Log.i("Raw Asset: ", fields[count].getName());
            int resourceID=fields[count].getInt(fields[count]);
            son.add(resourceID);

        }
    }

    public void playMusic(final View v){

        int min = 0;
        int max = son.size();
        int randomMusic = min + random.nextInt(max+min);
        int idSon = son.get(randomMusic);

        if (mMedia == null){
            if (!stock.contains(idSon)){
                mMedia = MediaPlayer.create(MainActivity.this,idSon);
                mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        next(v);
                    }
                });
                stock.add(idSon);
                if (stock.size() == max) {
                    stock.clear();
                    Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
                }
            }else{
                playMusic(v);
            }
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

//    @Override
//    protected void onStop(){
//        super.onStop();
//        stopMyMusic();
//    }
}

