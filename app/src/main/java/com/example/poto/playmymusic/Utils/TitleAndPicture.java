package com.example.poto.playmymusic.Utils;

import com.example.poto.playmymusic.R;

import java.util.ArrayList;

public class TitleAndPicture {

    //ANIME
    public ArrayList animeTitle(ArrayList<String> title){
//        title.add("AttackOnTitan - Call Your Name");
        title.add("Evangelion OP - Cruel Angels Thesis");
        title.add("HUNTING FOR YOUR DREAM");
        return title;
    }

    public ArrayList animePicture(ArrayList<Integer> picture){
//        picture.add(R.drawable.attackcallyourname);
        picture.add(R.drawable.evangelion11);
        picture.add(R.drawable.hxh11);
        return picture;
    }


    //EPIC
    public ArrayList epicTitle(ArrayList<String> title){
        title.add("Two Steps from Hell - Heart of Courage");
        title.add("Two Steps From Hell - Immortal");
        return title;
    }

    public ArrayList epicPicture(ArrayList<Integer> picture){
        picture.add(R.drawable.twostepfromhell);
        picture.add(R.drawable.twostepfromhell);
        return picture;
    }


    //ROCK
    public ArrayList rockTitle(ArrayList<String> title){
        title.add("Breaking Benjamin - Diary Of Jane");
        title.add("Sick Puppies - Youre Going Down");
        return title;
    }

    public ArrayList rockPicture(ArrayList<Integer> picture){
        picture.add(R.drawable.breakingbenjamin);
        picture.add(R.drawable.sickpuppies);
        return picture;
    }


    //RAP
    public ArrayList rapTitle(ArrayList<String> title){
        title.add("Sniper - Fait divers");
        title.add("La Fouine - L'Unité");
        return title;
    }

    public ArrayList rapPicture(ArrayList<Integer> picture){
        picture.add(R.drawable.sniper);
        picture.add(R.drawable.lafouine);
        return picture;
    }

    public ArrayList allpPicture(ArrayList<Integer> picture){
        picture.add(R.drawable.breakingbenjamin);
        picture.add(R.drawable.evangelion11);
        picture.add(R.drawable.sniper);
        picture.add(R.drawable.twostepfromhell);
        picture.add(R.drawable.hxh11);
        picture.add(R.drawable.twostepfromhell);
        picture.add(R.drawable.lafouine);
        picture.add(R.drawable.sickpuppies);
        return picture;
    }
}
