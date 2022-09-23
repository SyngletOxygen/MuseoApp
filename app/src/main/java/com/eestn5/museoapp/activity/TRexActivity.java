package com.eestn5.museoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.eestn5.museoapp.games.TRex.TRexMain;

public class TRexActivity extends AppCompatActivity {
    TRexMain juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juego = new TRexMain(this, (AudioManager)(getSystemService(Context.AUDIO_SERVICE)));
        setContentView(juego);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean result = juego.manageKeyEvent(event);
        if(!result)
            result = super.dispatchKeyEvent(event);
        return result;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        juego.isKeyPressed=false;
        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
            juego.inputDown(false);


        return super.onKeyUp(keyCode, event);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean res = false;
        if(keyCode == KeyEvent.KEYCODE_BACK){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
            res=true;
        else
            res=super.onKeyLongPress(keyCode, event);
        return res;
    }


    @Override
    protected void onPause() {
        juego.pause();
        super.onPause();
    }





}