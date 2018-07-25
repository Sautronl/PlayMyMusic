package com.example.poto.playmymusic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private ListView mList;
    private CategoryModel mModel;
    private ArrayList<CategoryModel> mArray = new ArrayList<>();
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView titleCategory = (TextView)findViewById(R.id.titleCategory);
        Typeface mainfont = Typeface.createFromAsset(getAssets(), "GothamRounded-Bold.otf");
        titleCategory.setTypeface(mainfont);

        mList = (ListView) findViewById(R.id.list);
        mArray.add(new CategoryModel("Anime",R.drawable.animelogo,getResources().getColor(R.color.rouge)));
        mArray.add(new CategoryModel("Epic",R.drawable.epiclogo,getResources().getColor(R.color.jaune)));
        mArray.add(new CategoryModel("Rock",R.drawable.rocklogo,getResources().getColor(R.color.noir)));
        mArray.add(new CategoryModel("Rap",R.drawable.raplogo,getResources().getColor(R.color.colorPrimaryDark)));
        mArray.add(new CategoryModel("Aleatoire",R.drawable.allmusic,getResources().getColor(R.color.colorAccent)));

        MyCategoryAdapter adapter = new MyCategoryAdapter(CategoryActivity.this,mArray);
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mModel = (CategoryModel) parent.getItemAtPosition(position);
                switch (position) {
                    case 0:
                        pos = position;
                       intentCat(mArray.get(position));
                        break;
                    case 1:
                        pos = position;
                        intentCat(mArray.get(position));
                        break;
                    case 2:
                        pos = position;
                        intentCat(mArray.get(position));
                        break;
                    case 3:
                        pos = position;
                        intentCat(mArray.get(position));
                        break;
                    case 4:
                        pos = position;
                        intentCat(mArray.get(position));
                        break;
                }
            }
        });
    }

    public static void start(Context c,int pos) {
        c.startActivity(new Intent(c, ListSongActivity.class).putExtra("name",pos));
    }

    private void intentCat(CategoryModel pos){
        Intent intent = new Intent(CategoryActivity.this, ListSongActivity.class);
        intent.putExtra("name", pos);
        startActivity(intent);
    }
}