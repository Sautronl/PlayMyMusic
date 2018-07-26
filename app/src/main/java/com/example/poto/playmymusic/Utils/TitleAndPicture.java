package com.example.poto.playmymusic.Utils;

import com.example.poto.playmymusic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TitleAndPicture {
    private String titre = "";
    private int idImg =0;


    public String titleSong(String genre,int count){

        HashMap<Integer,String> musicTitle = new HashMap<Integer,String>();

        if (genre.equals("Anime")){
            musicTitle.put(0,"Evangelion OP - Cruel Angels Thesis");
            musicTitle.put(1,"HUNTING FOR YOUR DREAM");
           check(musicTitle,count);
        }else if (genre.equals("Epic")){
            musicTitle.put(0,"Two Steps from Hell - Heart of Courage");
            musicTitle.put(1,"Two Steps From Hell - Immortal");
            check(musicTitle,count);
        }else if (genre.equals("Rock")){
            musicTitle.put(0,"Breaking Benjamin - Diary Of Jane");
            musicTitle.put(1,"Sick Puppies - You'Re Going Down");
            check(musicTitle,count);
        }else{
            musicTitle.put(0,"Sniper - Fait divers");
            musicTitle.put(1,"La Fouine - L'Unité");
            check(musicTitle,count);
        }
        return titre;
    }

    private void check(HashMap<Integer,String > musicTitle,int count) {
        Iterator iterator = musicTitle.keySet().iterator();
        while (iterator.hasNext()) {
            Integer number = (Integer) iterator.next();
            if (number == count) {
                titre = (String) musicTitle.get(number);
            }
        }
    }

    public int imageSong(String genre,int count){

        HashMap<Integer,Integer> musicImage = new HashMap<>();

        if (genre.equals("Anime")){
            musicImage.put(0,R.drawable.evangelion11);
            musicImage.put(1,R.drawable.hxh11);
            otherCheck(musicImage,count);
        }else if (genre.equals("Epic")){
            musicImage.put(0,R.drawable.twostepfromhell);
            musicImage.put(1,R.drawable.twostepfromhell);
            otherCheck(musicImage,count);
        }else if (genre.equals("Rock")){
            musicImage.put(0,R.drawable.breakingbenjamin);
            musicImage.put(1,R.drawable.sickpuppies);
            otherCheck(musicImage,count);
        }else if (genre.equals("Rap")){
            musicImage.put(0,R.drawable.sniper);
            musicImage.put(1,R.drawable.lafouine);
            otherCheck(musicImage,count);
        }else{
            musicImage.put(0,R.drawable.breakingbenjamin);
            musicImage.put(1,R.drawable.evangelion11);
            musicImage.put(2,R.drawable.sniper);
            musicImage.put(3,R.drawable.twostepfromhell);
            musicImage.put(4,R.drawable.hxh11);
            musicImage.put(5,R.drawable.twostepfromhell);
            musicImage.put(6,R.drawable.lafouine);
            musicImage.put(7,R.drawable.sickpuppies);
            otherCheck(musicImage,count);
        }
        return idImg;
    }

    private void otherCheck(HashMap<Integer,Integer > musicTitle,int count) {
        Iterator iterator = musicTitle.keySet().iterator();
        while (iterator.hasNext()) {
            Integer number = (Integer) iterator.next();
            if (number == count) {
                idImg =  musicTitle.get(number);
            }
        }
    }
}
