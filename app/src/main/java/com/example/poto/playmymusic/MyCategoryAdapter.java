package com.example.poto.playmymusic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyCategoryAdapter extends BaseAdapter{
    private Activity activity;
    private List<CategoryModel> catModel;
    private LayoutInflater inflater;


    public MyCategoryAdapter(Activity activity, List<CategoryModel> catModel) {
        this.activity = activity;
        this.catModel = catModel;
    }

    @Override
    public int getCount() {
        return catModel.size();
    }

    @Override
    public Object getItem(int i) {
        return catModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_event_item,null);

        TextView txtName = (TextView)itemView.findViewById(R.id.nameCategory);
        ImageView img = (ImageView) itemView.findViewById(R.id.imageCategory);

        Typeface mainfont = Typeface.createFromAsset(activity.getAssets(), "GothamRounded-Bold.otf");
        txtName.setTypeface(mainfont);
        txtName.setText(catModel.get(i).getName());
        img.setImageResource(catModel.get(i).getIdimg());
        return itemView;
    }
}
