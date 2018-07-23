package com.example.poto.playmymusic;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.arclayout.ArcLayout;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    private MediaPlayer mMedia;
    int id,saveId;
    ArrayList<Integer> sonPlay;
    private ArrayList<Integer> stockSon = new ArrayList<>();
    View view;
    private static String TAG = "PlayActivity";
    ArcLayout arcLayout;
    TextView titleMyMusicPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        arcLayout= (ArcLayout) findViewById(R.id.diagonalLayout);
        titleMyMusicPlay = (TextView) findViewById(R.id.titleMyMusicPlay);

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

        Bundle extra = getIntent().getBundleExtra("extra");
        sonPlay = (ArrayList<Integer>) extra.getSerializable("array");

        Intent intenta = getIntent();
        id = intenta.getIntExtra("id",0);
        saveId = intenta.getIntExtra("category",0);
        String titleEx = intenta.getStringExtra("title");

        titleMyMusicPlay.setText(titleEx);

        playMusic(view);
    }

    public void playMusic(View v){

        if (mMedia == null){
            if (id == sonPlay.size()){
                Toast.makeText(this, "tu as fait le tour gros", Toast.LENGTH_SHORT).show();
                stockSon.clear();
                id = 0;
            }
                stockSon.add(id);
                mMedia = MediaPlayer.create(PlayActivity.this,sonPlay.get(id));
                mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        next(view);
                    }
                });
        }
        mMedia.start();
    }

    public void loopMusic(View v){
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
        if (mMedia !=null){
            stopMyMusic();
            id = id+1;
            playMusic(v);
        }
    }

    public void next(View v){
        nextMusic(v);
    }

    public void pauseMusic(View v){
        if (mMedia != null){
            mMedia.pause();
        }
    }

    public void stopMusic(View v){
        stopMyMusic();
    }

    private void stopMyMusic(){
        if (mMedia != null){
            mMedia.release();
            mMedia = null;
        }
    }

    @Override
    public void onBackPressed() {
        stopMyMusic();
        Intent backIntent = new Intent(PlayActivity.this,ListSongActivity.class);
        backIntent.putExtra("name",saveId);
        startActivity(backIntent);
    }
}
