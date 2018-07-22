package com.example.poto.playmymusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mMedia;
    ArrayList<Integer> son = new ArrayList<>();
    ArrayList<Integer> stock = new ArrayList<>();
    ArrayList<Integer> anime = new ArrayList<>();
    ArrayList<Integer> epic = new ArrayList<>();
    Random random = new Random();
    int idSon,min,max,randomMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            listRaw();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


        Intent intent = getIntent();
        int positionCategory = intent.getIntExtra("name",0);
//        checkCategory(positionCategory);
    }

//    private void checkCategory(int positionCategory) {
//        if (positionCategory==0){
//            if (son.size()>0){
//                son.clear();
//            }
//            for (int i = 0; i < anime.size(); i++) {
//                son.add(anime.get(i));
//            }
//        }else{
//            if (son.size()>0){
//                son.clear();
//            }
//            for (int i = 0; i < epic.size(); i++) {
//                son.add(epic.get(i));
//            }
//        }
//    }

//    public void listRaw() throws IllegalAccessException {
//        Field[] fields = R.raw.class.getFields();
//        for (int count = 0; count < fields.length; count++) {
//            Log.i("Raw Asset: ", fields[count].getName());
//            if (fields[count].getName().contains("1")){
//                int resourceID=fields[count].getInt(fields[count]);
//                anime.add(resourceID);
//            }else if (fields[count].getName().contains("2")){
//                int resourceID=fields[count].getInt(fields[count]);
//                epic.add(resourceID);
//            }
//        }
//    }

    public void playMusic(final View v){

        if (son.size() != 0){
            min = 0;
            max = son.size();
            randomMusic = min + random.nextInt(max+min);
            idSon = son.get(randomMusic);

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
        }else{
            Toast.makeText(this, "tu es plus anime ou epic?", Toast.LENGTH_SHORT).show();
        }
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

