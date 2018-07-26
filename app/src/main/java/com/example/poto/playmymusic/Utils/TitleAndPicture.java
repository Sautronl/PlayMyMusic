package com.example.poto.playmymusic.Utils;

import com.example.poto.playmymusic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TitleAndPicture {
    private String titre = "";

    public String titleSong(String fakeName) {

        HashMap<String, String> realMusicTitle = new HashMap<String, String>();


        realMusicTitle.put("attack_on_titan_ost_reiner1", "Attack On Titan - Reiner OST");
        realMusicTitle.put("berserk_blood_and_guts1", "Berserk - Blood And Guts");
        realMusicTitle.put("eurielle_eurielle2", "Eurielle - Eurielle");
        realMusicTitle.put("heart_of_courage2", "Two Steps from Hell - Heart of Courage");
        realMusicTitle.put("courtesy_call_thousand_foot_krutch3", "Courtesy Call - Thousand Foot Krutch");
        realMusicTitle.put("diary_of_jane3", "Breaking Benjamin - Diary Of Jane");
        realMusicTitle.put("bigflo_and_oli_monsieur_tout_le_monde4", "BigFlo & Oli - Monsieur Tout Le Monde");
        realMusicTitle.put("bigflo_and_oli_nous_aussi4", "BigFlo & Oli - Nous Aussi");

        Iterator iterator = realMusicTitle.keySet().iterator();
        while (iterator.hasNext()) {
            String fakeTitle = (String) iterator.next();
            if (fakeTitle == fakeName) {
                titre = (String) realMusicTitle.get(fakeTitle);
            }
        }
        return titre;
    }
}

