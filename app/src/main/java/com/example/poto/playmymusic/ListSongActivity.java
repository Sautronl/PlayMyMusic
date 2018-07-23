package com.example.poto.playmymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class ListSongActivity extends AppCompatActivity {

    ArrayList<MusicModel> anime = new ArrayList<>();
    ArrayList<MusicModel> epic = new ArrayList<>();
    ArrayList<MusicModel> rock = new ArrayList<>();
    ArrayList<MusicModel> rap = new ArrayList<>();
    ArrayList<String> titreAnime = new ArrayList<>();
    ArrayList<String> titreEpic = new ArrayList<>();
    ArrayList<String> titreRock = new ArrayList<>();
    ArrayList<String> titreRap = new ArrayList<>();
    ArrayList<Integer> imagePLaylistAnime = new ArrayList<>();
    ArrayList<Integer> imagePLaylistEpic = new ArrayList<>();
    ArrayList<Integer> imagePLaylistRock = new ArrayList<>();
    ArrayList<Integer> imagePLaylistRap = new ArrayList<>();
    MusicModel musicModel;

    private ListView mListSon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mListSon = (ListView) findViewById(R.id.listSonName);

        initTitle();

        Intent intent = getIntent();
        final int positionCategory = intent.getIntExtra("name",0);

        try {
            listRaw();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        checkCategory(positionCategory);
    }

    private void initTitle() {

        //IMAGE ANIME
        imagePLaylistAnime.add(R.drawable.evangelion11);
        imagePLaylistAnime.add(R.drawable.hxh11);

        //IMAGE EPIC
        imagePLaylistEpic.add(R.drawable.twostepfromhell);
        imagePLaylistEpic.add(R.drawable.twostepfromhell);

        //IMAGE ROCK
        imagePLaylistRock.add(R.drawable.breakingbenjamin);
        imagePLaylistRock.add(R.drawable.sickpuppies);

        //IMAGE RAP
        imagePLaylistRap.add(R.drawable.sniper);
        imagePLaylistRap.add(R.drawable.lafouine);


        //ANIME
        titreAnime.add("Evangelion OP - Cruel Angels Thesis");
        titreAnime.add("HUNTING FOR YOUR DREAM");

        //EPIC
        titreEpic.add("Two Steps from Hell - Heart of Courage");
        titreEpic.add("Two Steps From Hell - Immortal");

        //ROCK
        titreRock.add("Breaking Benjamin - Diary Of Jane");
        titreRock.add("Sick Puppies - Youre Going Down");

        //RAP
        titreRap.add("Sniper - Fait divers");
        titreRap.add("La Fouine - LUnité");
    }

    public void listRaw() throws IllegalAccessException {
        Field[] fields = R.raw.class.getFields();
        int animeCount = 0;
        int epicCount = 0;
        int rockCount = 0;
        int rapCount = 0;
        for (int count = 0; count < fields.length; count++) {
            Log.i("Raw Asset: ", fields[count].getName());
            if (fields[count].getName().contains("1")){
                int resourceID=fields[count].getInt(fields[count]);
                musicModel = new MusicModel("Anime",titreAnime.get(animeCount),resourceID,imagePLaylistAnime.get(animeCount));
                animeCount+=1;
                anime.add(musicModel);
            }else if (fields[count].getName().contains("2")){
                int resourceID=fields[count].getInt(fields[count]);
                musicModel = new MusicModel("Epic",titreEpic.get(epicCount),resourceID,imagePLaylistEpic.get(epicCount));
                epicCount+=1;
                epic.add(musicModel);
            }else if (fields[count].getName().contains("3")){
                int resourceID=fields[count].getInt(fields[count]);
                musicModel = new MusicModel("Rock",titreRock.get(rockCount),resourceID,imagePLaylistRock.get(rockCount));
                rockCount+=1;
                rock.add(musicModel);
            }else if (fields[count].getName().contains("4")){
                int resourceID=fields[count].getInt(fields[count]);
                musicModel = new MusicModel("Rap",titreRap.get(rapCount),resourceID,imagePLaylistRap.get(rapCount));
                rapCount+=1;
                rap.add(musicModel);
            }
        }
    }

    private void checkCategory(int positionCategory) {

        switch (positionCategory){
            case 0:
                adapterMusic(anime);
                break;
            case 1:
               adapterMusic(epic);
                break;
            case 2:
              adapterMusic(rock);
                break;
            case 3:
               adapterMusic(rap);
                break;
        }
    }

    private void adapterMusic(final ArrayList<MusicModel> musique) {
        ListSongAdapter adapterRap = new ListSongAdapter(this,musique);
        mListSon.setAdapter(adapterRap);
        mListSon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playIntent = new Intent(ListSongActivity.this,PlayActivity.class);
                playIntent.putExtra("id",position);
                playIntent.putExtra("category",musique.get(position).getCategory());
                playIntent.putExtra("musicM", musique);
                startActivity(playIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListSongActivity.this,CategoryActivity.class));
    }
}



//    ArrayList<ArrayList> whichCategory = new ArrayList<>();
//    ArrayList<ArrayList> whichTitle = new ArrayList<>();
// whichCategory.add(anime);
//        whichCategory.add(epic);
//        whichCategory.add(rock);
//        whichCategory.add(rap);
//
//        whichTitle.add(titreAnime);
//        whichTitle.add(titreEpic);
//        whichTitle.add(titreRock);
//        whichTitle.add(titreRap);
//        for (int i = whichCategory.size()-1; i >=positionCategory; i--) {
//            if (i == positionCategory){
//                ArrayList tr = whichCategory.get(i);
//                titleList = whichTitle.get(i);
//                for (int j = 0; j < tr.size(); j++) {
//                    son.add((Integer) tr.get(j));
//                }