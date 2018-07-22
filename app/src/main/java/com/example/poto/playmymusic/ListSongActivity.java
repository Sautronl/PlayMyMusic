package com.example.poto.playmymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ListSongActivity extends AppCompatActivity {

    ArrayList<Integer> anime = new ArrayList<>();
    ArrayList<Integer> epic = new ArrayList<>();
    ArrayList<Integer> rock = new ArrayList<>();
    ArrayList<Integer> rap = new ArrayList<>();
    ArrayList<Integer> son = new ArrayList<>();

    private ListView mListSon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        mListSon = (ListView) findViewById(R.id.listSonName);


        Intent intent = getIntent();
        int positionCategory = intent.getIntExtra("name",0);

        try {
            listRaw();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (positionCategory == 0){

        }
        checkCategory(positionCategory);

        ListSongAdapter adapter = new ListSongAdapter(this,son);
        mListSon.setAdapter(adapter);

        mListSon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extra = new Bundle();
                extra.putSerializable("array", son);
                Intent playIntent = new Intent(ListSongActivity.this,PlayActivity.class);
                playIntent.putExtra("id",position);
                playIntent.putExtra("extra", extra);
                startActivity(playIntent);
            }
        });
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
        if (positionCategory==0){
            if (son.size()>0){
                son.clear();
            }
            for (int i = 0; i < anime.size(); i++) {
                son.add(anime.get(i));
            }
        }else if (positionCategory==1){
            if (son.size()>0){
                son.clear();
            }
            for (int i = 0; i < epic.size(); i++) {
                son.add(epic.get(i));
            }
        }else if (positionCategory==2){
            if (son.size()>0){
                son.clear();
            }
            for (int i = 0; i < rock.size(); i++) {
                son.add(rock.get(i));
            }
        }else{
            if (son.size()>0){
                son.clear();
            }
            for (int i = 0; i < rap.size(); i++) {
                son.add(rap.get(i));
            }
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListSongActivity.this,CategoryActivity.class));
    }
}
