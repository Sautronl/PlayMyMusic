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

    ArrayList<Integer> anime = new ArrayList<>();
    ArrayList<Integer> epic = new ArrayList<>();
    ArrayList<Integer> rock = new ArrayList<>();
    ArrayList<Integer> rap = new ArrayList<>();
    ArrayList<Integer> son = new ArrayList<>();
    ArrayList<String> titreAnime = new ArrayList<>();
    ArrayList<String> titreEpic = new ArrayList<>();
    ArrayList<String> titreRock = new ArrayList<>();
    ArrayList<String> titreRap = new ArrayList<>();
    ArrayList<ArrayList> whichCategory = new ArrayList<>();
    ArrayList<ArrayList> whichTitle = new ArrayList<>();

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

        mListSon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extra = new Bundle();
                extra.putSerializable("array", son);
                Intent playIntent = new Intent(ListSongActivity.this,PlayActivity.class);
                playIntent.putExtra("id",position);
                playIntent.putExtra("category",positionCategory);
                playIntent.putExtra("extra", extra);
                startActivity(playIntent);
            }
        });
    }

    private void initTitle() {

        whichCategory.add(anime);
        whichCategory.add(epic);
        whichCategory.add(rock);
        whichCategory.add(rap);

        whichTitle.add(titreAnime);
        whichTitle.add(titreEpic);
        whichTitle.add(titreRock);
        whichTitle.add(titreRap);

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
        for (int count = 0; count < fields.length; count++) {
            Log.i("Raw Asset: ", fields[count].getName());
            if (fields[count].getName().contains("1")){
                int resourceID=fields[count].getInt(fields[count]);
                anime.add(resourceID);
            }else if (fields[count].getName().contains("2")){
                int resourceID=fields[count].getInt(fields[count]);
                epic.add(resourceID);
            }else if (fields[count].getName().contains("3")){
                int resourceID=fields[count].getInt(fields[count]);
                rock.add(resourceID);
            }else if (fields[count].getName().contains("4")){
                int resourceID=fields[count].getInt(fields[count]);
                rap.add(resourceID);
            }
        }
    }

    private void checkCategory(int positionCategory) {

        if (son.size()>0){
            son.clear();
        }
        for (int i = whichCategory.size()-1; i >=positionCategory; i--) {
            if (i == positionCategory){
                ArrayList tr = whichCategory.get(i);
                ArrayList<String> titleList = whichTitle.get(i);
                for (int j = 0; j < tr.size(); j++) {
                    son.add((Integer) tr.get(j));
                }
                ListSongAdapter adapter = new ListSongAdapter(this,son,titleList);
                mListSon.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListSongActivity.this,CategoryActivity.class));
    }
}
