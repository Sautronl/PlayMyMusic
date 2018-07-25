package com.example.poto.playmymusic;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.WindowManager;
import android.widget.ImageView;


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
    ImageView mainPicture;
    private RecyclerView mListSon;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CategoryModel positionCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mListSon = (RecyclerView) findViewById(R.id.listSonName);
        mainPicture = (ImageView) findViewById(R.id.mainPicture);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.maincollapsing);

        Intent intent = getIntent();
        if (intent!= null){
            positionCategory = intent.getParcelableExtra("name");
        }

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

    private void checkCategory(CategoryModel positionCategory) {

        switch (positionCategory.getName()){
            case "Anime":
                adapterMusic(anime,positionCategory.getIdimg(),positionCategory.getName());
                break;
            case "Epic":
                adapterMusic(epic,positionCategory.getIdimg(),positionCategory.getName());
                break;
            case "Rock":
                adapterMusic(rock,positionCategory.getIdimg(),positionCategory.getName());
                break;
            case "Rap":
                adapterMusic(rap,positionCategory.getIdimg(),positionCategory.getName());
                break;
            case "Aleatoire":
                adapterMusic(alea,positionCategory.getIdimg(),positionCategory.getName());
                break;
        }
    }

    private void adapterMusic(final ArrayList<MusicModel> musique,int drawable,String catName) {
        collapsingToolbarLayout.setTitle(catName);
        collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.blanc));
        mainPicture.setImageDrawable(getResources().getDrawable(drawable));
        mListSon.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
        ListSongAdapter adapter = new ListSongAdapter(this,musique,positionCategory);
        mListSon.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListSongActivity.this,CategoryActivity.class));
    }
}
