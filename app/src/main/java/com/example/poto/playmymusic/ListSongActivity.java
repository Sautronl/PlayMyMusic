package com.example.poto.playmymusic;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;


import com.example.poto.playmymusic.Model.CategoryModel;
import com.example.poto.playmymusic.Model.MusicModel;
import com.example.poto.playmymusic.Utils.TitleAndPicture;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class ListSongActivity extends AppCompatActivity {

    ArrayList<MusicModel> anime = new ArrayList<>();
    ArrayList<MusicModel> epic = new ArrayList<>();
    ArrayList<MusicModel> rock = new ArrayList<>();
    ArrayList<MusicModel> rap = new ArrayList<>();
    ArrayList<MusicModel> alea = new ArrayList<>();
    ArrayList<CategoryModel> allCategory = new ArrayList<>();
    MusicModel musicModel;
    String realName,replace;
    ImageView mainPicture;
    private RecyclerView mListSon;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CategoryModel positionCategory;
    int animeCount;
    int epicCount;
    int rockCount;
    int rapCount;

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
            allCategory = intent.getParcelableArrayListExtra("packCategory");
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
         animeCount = 0;
         epicCount = 0;
         rockCount = 0;
         rapCount = 0;
        for (int count = 0; count < fields.length; count++) {
            Log.i("Raw Asset: ", fields[count].getName());
            String nameID = fields[count].getName();
            int resID=fields[count].getInt(fields[count]);
            splitName(nameID);
            TitleAndPicture titleAndPicture = new TitleAndPicture();
            int imageAll =titleAndPicture.imageSong("Aleatoire",count);
            musicModel = new MusicModel(realName,resID,imageAll,allCategory.get(4));
            alea.add(musicModel);
            if (fields[count].getName().contains("1")){
                int resourceID=fields[count].getInt(fields[count]);
               getTheMusic("Anime",animeCount,resourceID,anime,allCategory.get(0));
                animeCount+=1;
            }
            else if (fields[count].getName().contains("2")){
                int resourceID=fields[count].getInt(fields[count]);
                getTheMusic("Epic",epicCount,resourceID,epic,allCategory.get(1));
                epicCount+=1;
            }
            else if (fields[count].getName().contains("3")){
                int resourceID=fields[count].getInt(fields[count]);
               getTheMusic("Rock",rockCount,resourceID,rock,allCategory.get(2));
                rockCount+=1;
            }
            else if (fields[count].getName().contains("4")){
                int resourceID=fields[count].getInt(fields[count]);
               getTheMusic("Rap",rapCount,resourceID,rap,allCategory.get(3));
               rapCount+=1;
            }
        }
    }

    private void getTheMusic(String genre,int count,int resourceID,ArrayList<MusicModel> category,CategoryModel allCategory) {
        TitleAndPicture titleAndPicture2 = new TitleAndPicture();
        String title = titleAndPicture2.titleSong(genre,count);
        int image =titleAndPicture2.imageSong(genre,count);
        musicModel = new MusicModel(title,resourceID,image,allCategory);
        category.add(musicModel);
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
                adapterMusic(anime,allCategory.get(0).getIdimg(),allCategory.get(0).getName());
                break;
            case "Epic":
                adapterMusic(epic,allCategory.get(1).getIdimg(),allCategory.get(1).getName());
                break;
            case "Rock":
                adapterMusic(rock,allCategory.get(2).getIdimg(),allCategory.get(2).getName());
                break;
            case "Rap":
                adapterMusic(rap,allCategory.get(3).getIdimg(),allCategory.get(3).getName());
                break;
            case "Aleatoire":
                adapterMusic(alea,allCategory.get(4).getIdimg(),allCategory.get(4).getName());
                break;
        }
    }

    private void adapterMusic(final ArrayList<MusicModel> musique,int drawable,String catName) {
        collapsingToolbarLayout.setTitle(catName);
        collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.blanc));
        mainPicture.setImageDrawable(getResources().getDrawable(drawable));
        mListSon.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
        ListSongAdapter adapter = new ListSongAdapter(this,musique);
        mListSon.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListSongActivity.this,CategoryActivity.class));
    }
}
