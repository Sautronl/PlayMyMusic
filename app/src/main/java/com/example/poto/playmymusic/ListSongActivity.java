package com.example.poto.playmymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.poto.playmymusic.Utils.TitleAndPicture;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ListSongActivity extends AppCompatActivity {

    ArrayList<MusicModel> anime = new ArrayList<>();
    ArrayList<MusicModel> epic = new ArrayList<>();
    ArrayList<MusicModel> rock = new ArrayList<>();
    ArrayList<MusicModel> rap = new ArrayList<>();
    ArrayList<MusicModel> alea = new ArrayList<>();
    ArrayList<String> titreAnime = new ArrayList<>();
    ArrayList<String> titreEpic = new ArrayList<>();
    ArrayList<String> titreRock = new ArrayList<>();
    ArrayList<String> titreRap = new ArrayList<>();
    ArrayList<Integer> imagePLaylistAnime = new ArrayList<>();
    ArrayList<Integer> imagePLaylistEpic = new ArrayList<>();
    ArrayList<Integer> imagePLaylistRock = new ArrayList<>();
    ArrayList<Integer> imagePLaylistRap = new ArrayList<>();
    ArrayList<Integer> imagePLaylistAll = new ArrayList<>();
    MusicModel musicModel;
    TitleAndPicture titleAndPictureAnime,titleAndPictureEpic,titleAndPictureRock,titleAndPictureRap,titleAndPictureAll;
    String finalTitle,realName,replace;
    int finalPic;

    private ListView mListSon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mListSon = (ListView) findViewById(R.id.listSonName);

        Intent intent = getIntent();
        final int positionCategory = intent.getIntExtra("name",0);

        try {
            listRaw();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        checkCategory(positionCategory);
    }

    public void listRaw() throws IllegalAccessException {
        Field[] fields = R.raw.class.getFields();
        int animeCount = 0;
        int epicCount = 0;
        int rockCount = 0;
        int rapCount = 0;
        for (int count = 0; count < fields.length; count++) {
            Log.i("Raw Asset: ", fields[count].getName());
            String nameID = fields[count].getName();
            int resID=fields[count].getInt(fields[count]);
            splitName(nameID);
            titleAndPictureAll = new TitleAndPicture();
            int pictureAll = (int) titleAndPictureAll.allpPicture(imagePLaylistAll).get(count);
            musicModel = new MusicModel("Aleatoire", realName,resID,pictureAll);
            alea.add(musicModel);
            if (fields[count].getName().contains("1")){
                int resourceID=fields[count].getInt(fields[count]);
                titleAndPictureAnime = new TitleAndPicture();
                titleAndPictureAnime.animeTitle(titreAnime);
                titleAndPictureAnime.animePicture(imagePLaylistAnime);
                finalTitle = (String) titleAndPictureAnime.animeTitle(titreAnime).get(animeCount);
                finalPic = (int) titleAndPictureAnime.animePicture(imagePLaylistAnime).get(animeCount);
                musicModel = new MusicModel("Anime", finalTitle,resourceID,finalPic);
                animeCount+=1;
                anime.add(musicModel);
            }
            else if (fields[count].getName().contains("2")){
                int resourceID=fields[count].getInt(fields[count]);
                titleAndPictureEpic = new TitleAndPicture();
                titleAndPictureEpic.epicTitle(titreEpic);
                titleAndPictureEpic.epicPicture(imagePLaylistEpic);
                finalTitle = (String) titleAndPictureEpic.epicTitle(titreEpic).get(epicCount);
                finalPic = (int) titleAndPictureEpic.epicPicture(imagePLaylistEpic).get(epicCount);
                musicModel = new MusicModel("Epic",finalTitle,resourceID,finalPic);
                epicCount+=1;
                epic.add(musicModel);
            }
            else if (fields[count].getName().contains("3")){
                int resourceID=fields[count].getInt(fields[count]);
                titleAndPictureRock = new TitleAndPicture();
                titleAndPictureRock.rockTitle(titreRock);
                titleAndPictureRock.rockPicture(imagePLaylistRock);
                finalTitle = (String) titleAndPictureRock.rockTitle(titreRock).get(rockCount);
                finalPic = (int) titleAndPictureRock.rockPicture(imagePLaylistRock).get(rockCount);
                musicModel = new MusicModel("Rock",finalTitle,resourceID,finalPic);
                rockCount+=1;
                rock.add(musicModel);
            }
            else if (fields[count].getName().contains("4")){
                int resourceID=fields[count].getInt(fields[count]);
                titleAndPictureRap = new TitleAndPicture();
                titleAndPictureRap.rapTitle(titreRap);
                titleAndPictureRap.rapPicture(imagePLaylistRap);
                finalTitle = (String) titleAndPictureRap.rapTitle(titreRap).get(rapCount);
                finalPic = (int) titleAndPictureRap.rapPicture(imagePLaylistRap).get(rapCount);
                musicModel = new MusicModel("Rap",finalTitle,resourceID,finalPic);
                rapCount+=1;
                rap.add(musicModel);
            }
        }
    }

    private void splitName(String name) {
        if(name.contains("1")) {
            replace = name.replaceAll("1", "");
        }else if (name.contains("2")){
            replace = name.replaceAll("2","");
        }else if (name.contains("3")){
            replace = name.replaceAll("3","");
        }else{
            replace = name.replaceAll("4","");
        }
        String split = replace.replaceAll("_"," ");
        realName = split;
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
            case 4:
                adapterMusic(alea);
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
