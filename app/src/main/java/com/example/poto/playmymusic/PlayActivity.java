package com.example.poto.playmymusic;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.florent37.arclayout.ArcLayout;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    private MediaPlayer mMedia;
    int id;
    CategoryModel saveCategory;
    private ArrayList<MusicModel> sonPlay = new ArrayList<>();
    private ArrayList<Integer> stockSon = new ArrayList<>();
    View view;
    private static String TAG = "PlayActivity";
    ArcLayout arcLayout;
    TextView titleMyMusicPlay;
    int loopCounter =0;
    ImageView play1;
    ImageView pause1;
    ImageView loop1,imagePlay,stop1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        arcLayout= (ArcLayout) findViewById(R.id.diagonalLayout);
        titleMyMusicPlay = (TextView) findViewById(R.id.titleMyMusicPlay);
        play1 = (ImageView)findViewById(R.id.play1);
        pause1 = (ImageView)findViewById(R.id.pause1);
        loop1 = (ImageView)findViewById(R.id.loop1);
        imagePlay = (ImageView) findViewById(R.id.imagePlay);
        stop1 = (ImageView) findViewById(R.id.stop1);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Typeface mainfont = Typeface.createFromAsset(getAssets(), "GothamRounded-Bold.otf");
        titleMyMusicPlay.setTypeface(mainfont);

        YoYo.with(Techniques.Shake)
                .duration(10000)
                .repeat(40)
                .playOn(findViewById(R.id.titleMyMusicPlay));

        MediaSession mSession =  new MediaSession(this,this.getPackageName());
        if (mSession == null) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            return;
        }

        mSession.setCallback(new MediaSession.Callback() {
            public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
                final KeyEvent event = (KeyEvent) mediaButtonIntent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return false;

                switch (event.getKeyCode()) {
                    case KeyEvent.KEYCODE_MEDIA_NEXT:
                        // next track
                        next(view);
                        break;
                    case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                        // previous track
                        previousMusic(view);
                        break;
                }
                return false;
            }

            public void onPause() {
                Log.d(TAG, "onPause called (media button pressed)");
                super.onPause();
            }

            public void onPlay() {
                Log.d(TAG, "onPlay called (media button pressed)");
                super.onPlay();
            }

            public void onStop() {
                Log.d(TAG, "onStop called (media button pressed)");
                super.onStop();
            }
        });

        mSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS | MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);

        PlaybackState state = new PlaybackState.Builder()
                .setActions(PlaybackState.ACTION_PAUSE | PlaybackState.ACTION_PLAY | PlaybackState.ACTION_SKIP_TO_NEXT | PlaybackState.ACTION_STOP)
                .setState(PlaybackState.STATE_PLAYING, 0, 1, 0)
                .build();
        mSession.setPlaybackState(state);

        Intent intenta = getIntent();
        id = intenta.getIntExtra("id",0);
        saveCategory = intenta.getParcelableExtra("category");
        sonPlay = getIntent().getParcelableArrayListExtra("musicM");
        playMusic(view);
    }

    public void playMusic(View v){

        play1.setVisibility(View.GONE);
        pause1.setVisibility(View.VISIBLE);
        stop1.setBackgroundColor(getResources().getColor(R.color.blanc));
        pause1.setBackgroundColor(getResources().getColor(R.color.jaune));
        if (id<0){
            id = sonPlay.size()-1;
            Toast.makeText(this, "On est reparti", Toast.LENGTH_SHORT).show();
        }
        if (mMedia == null){
            if (id == sonPlay.size()){
                Toast.makeText(this, "tu as fait le tour gros", Toast.LENGTH_SHORT).show();
                stockSon.clear();
                id = 0;
            }
                stockSon.add(id);
                imagePlay.setBackgroundResource(sonPlay.get(id).getImageId());
                mMedia = MediaPlayer.create(PlayActivity.this,sonPlay.get(id).getId());
                titleMyMusicPlay.setText(sonPlay.get(id).getTitle());
                mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        YoYo.with(Techniques.Shake)
                                .duration(10000)
                                .repeat(40)
                                .playOn(findViewById(R.id.titleMyMusicPlay));
                        next(view);
                    }
                });
        }
        mMedia.start();
    }

    public void loopMusic(View v){
        loopCounter+=1;
        if (!((loopCounter%2) ==0)){
            loop1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            loop1.setBackgroundColor(getResources().getColor(R.color.blanc));
        }
        if (mMedia != null){
            mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }

    public void nextMusic(View v){
        stopMyMusic();
        id = id+1;
        loopCounter = 0;
        loop1.setBackgroundColor(getResources().getColor(R.color.blanc));
        playMusic(v);
    }

    public void next(View v){
        nextMusic(v);
    }

    public void previousMusic(View v){
        stopMyMusic();
        id = id-1;
        loopCounter = 0;
        loop1.setBackgroundColor(getResources().getColor(R.color.blanc));
        playMusic(v);
    }

    public void pauseMusic(View v){
        play1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        play1.setVisibility(View.VISIBLE);
        pause1.setVisibility(View.GONE);
        if (mMedia != null){
            mMedia.pause();
        }
    }

    public void stopMusic(View v){
        play1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        stop1.setBackgroundColor(getResources().getColor(R.color.rouge));
        play1.setVisibility(View.VISIBLE);
        pause1.setVisibility(View.GONE);
        stopMyMusic();
    }

    private void stopMyMusic(){
        if (mMedia != null){
            loopCounter=0;
            mMedia.release();
            mMedia = null;
        }
    }

    @Override
    public void onBackPressed() {
        stopMyMusic();
        Intent backIntent = new Intent(PlayActivity.this,ListSongActivity.class);
        backIntent.putExtra("name",saveCategory);
        startActivity(backIntent);
    }
}
