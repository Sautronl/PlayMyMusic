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

public class CategoryActivity extends AppCompatActivity {

    private ListView mList;
    private CategoryModel mModel;
    private ArrayList<CategoryModel> mArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                       intentCat(position);
                        break;
                    case 1:
                        intentCat(position);
                        break;
                    case 2:
                        intentCat(position);
                        break;
                    case 3:
                        intentCat(position);
                        break;
                }

            }
        });
    }

    private void intentCat(int pos){
        Intent intent = new Intent(CategoryActivity.this, ListSongActivity.class);
        intent.putExtra("name", pos);
        startActivity(intent);
    }
}