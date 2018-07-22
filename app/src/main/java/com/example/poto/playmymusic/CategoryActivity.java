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

public class CategoryActivity extends AppCompatActivity {

    private ListView mList;
    private CategoryModel mModel;
    private ArrayList<CategoryModel> mArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mList = (ListView) findViewById(R.id.list);
        mArray.add(new CategoryModel("Anime",R.drawable.animelogo));
        mArray.add(new CategoryModel("Epic",R.drawable.epiclogo));
        mArray.add(new CategoryModel("Rock",R.drawable.rocklogo));
        mArray.add(new CategoryModel("Rap",R.drawable.raplogo));

        MyCategoryAdapter adapter = new MyCategoryAdapter(CategoryActivity.this,mArray);
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mModel = (CategoryModel) parent.getItemAtPosition(position);
                switch (position) {
                    case 0:
                        Intent animeIntent = new Intent(CategoryActivity.this, ListSongActivity.class);
                        animeIntent.putExtra("name", 0);
                        startActivity(animeIntent);
                        break;
                    case 1:
                        Intent epicIntent = new Intent(CategoryActivity.this, ListSongActivity.class);
                        epicIntent.putExtra("name", 1);
                        startActivity(epicIntent);
                        break;
                    case 2:
                        Intent rockIntent = new Intent(CategoryActivity.this, ListSongActivity.class);
                        rockIntent.putExtra("name", 2);
                        startActivity(rockIntent);
                        break;
                    case 3:
                        Intent rapIntent = new Intent(CategoryActivity.this, ListSongActivity.class);
                        rapIntent.putExtra("name", 3);
                        startActivity(rapIntent);
                        break;
                }

            }
        });
    }
}