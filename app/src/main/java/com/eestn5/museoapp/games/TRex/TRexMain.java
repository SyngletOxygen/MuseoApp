package com.eestn5.museoapp.games.TRex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.eestn5.museoapp.R;
import com.eestn5.museoapp.Static.Config;
import com.eestn5.museoapp.games.TRex.gameobject.Cactus;
import com.eestn5.museoapp.games.TRex.gameobject.Clouds;
import com.eestn5.museoapp.games.TRex.gameobject.EnemiesManager;
import com.eestn5.museoapp.games.TRex.gameobject.Land;
import com.eestn5.museoapp.games.TRex.gameobject.MainCharacter;
import com.eestn5.museoapp.games.general.Game;
import com.google.android.material.card.MaterialCardView;

public class TRexMain extends Game {
    public int delay=400;



    public static final int START_GAME_STATE = 0;
    public static final int GAME_PLAYING_STATE = 1;
    public static final int GAME_OVER_STATE = 2;

    private Land land;
    private MainCharacter mainCharacter;
    private EnemiesManager enemiesManager;
    private Clouds clouds;

    public boolean isKeyPressed;
    private boolean isReady = false;

    public int gameState = START_GAME_STATE;

    private Bitmap replayButtonImage;
    private Bitmap gameOverButtonImage;

    private MediaPlayer beepSfx;
    private AudioManager am;

    public TRexMain(Context context, AudioManager am) {
        super(context);
        this.am=am;
        this.beepSfx = getSfx(R.raw.longbeep);
        this.beepSfx.setLooping(true);
        this.beepSfx.setVolume(0f,0f);
        this.beepSfx.start();
        am.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);

        mainCharacter = new MainCharacter(
                getBitmaps(new int[] {R.drawable.main_character1, R.drawable.main_character2}),
                getBitmaps(new int[] {R.drawable.main_character5, R.drawable.main_character6}),
                getBitmap(R.drawable.main_character3),
                getBitmap(R.drawable.main_character4),
                getSfxs(new int[] {R.raw.jump, R.raw.dead, R.raw.scoreup, R.raw.beep}),
                (getHeight()/2) - (getBitmap(R.drawable.land1).getHeight()/4)*3);



        this.land = new Land(
                super.getWidth(),
                mainCharacter,
                getBitmaps(new int[] {R.drawable.land1, R.drawable.land2, R.drawable.land3}),
                getHeight());

        enemiesManager = new EnemiesManager(
                mainCharacter,
                new Bitmap[] {getBitmap(R.drawable.cactus1), getBitmap(R.drawable.cactus2)},
                (getHeight()/2) + (getBitmap(R.drawable.land1).getHeight()/2) + 4,getWidth());




        clouds = new Clouds(
                super.getWidth(),
                mainCharacter,
                getBitmap(R.drawable.cloud),
                getWidth());



        mainCharacter.setSpeedX(10);
        replayButtonImage = super.getBitmap(R.drawable.replay_button);
        gameOverButtonImage = super.getBitmap(R.drawable.gameover_text);
        isReady=true;
    }

    public void gameUpdate() {
        if (gameState == GAME_PLAYING_STATE) {
            clouds.update();
            land.update();
            mainCharacter.update();
            enemiesManager.update();
            if (enemiesManager.isCollision()) {
                mainCharacter.playDeadSound();
                gameState = GAME_OVER_STATE;
                mainCharacter.dead(true);
            }
            updateAlert();
            if((((Cactus)enemiesManager.enemies.get(0)).posX-delay<0 && !((Cactus)enemiesManager.enemies.get(0)).alertado) && Config.getConfig() == Config.BLIND){
                ((Cactus)enemiesManager.enemies.get(0)).alertado=true;
                getSfx(R.raw.jumpalert).start();
                delay*=1.05;
            }
        }
    }

    private void updateAlert(){
        System.out.println(Config.getConfig());
        if(Config.getConfig() == Config.BLIND) {


            float volumen;
            if (((Cactus) enemiesManager.enemies.get(0)).posX - 100 > 0 && gameState != GAME_OVER_STATE && ((Cactus) enemiesManager.enemies.get(0)).posX - 50 < (getWidth() / 2) - 50)
                volumen = (float) (Math.pow(1.5, (((double) ((getWidth() - 50) - ((Cactus) enemiesManager.enemies.get(0)).posX - 50) / (double) ((getWidth() / 2) - 50)) * 6) - 4) / 5); //esta formula es una genialidad

            else
                volumen = 0;
            beepSfx.setVolume(volumen, volumen);
        }
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(isReady){
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#f7f7f7"));
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

            switch (gameState) {
                case START_GAME_STATE:
                    mainCharacter.draw(canvas);
                    break;
                case GAME_PLAYING_STATE:
                case GAME_OVER_STATE:
                    clouds.draw(canvas);
                    land.draw(canvas);
                    enemiesManager.draw(canvas);
                    mainCharacter.draw(canvas);
                    paint.setColor(Color.BLACK);
                    paint.setTextSize(15);
                    canvas.drawText("HIGH :" + mainCharacter.highScore, getWidth() - 100,  40, paint);
                    canvas.drawText("SCORE:" + mainCharacter.score, getWidth() - 100,  58, paint);
                    if (gameState == GAME_OVER_STATE) {
                        drawBMP(getBitmap(R.drawable.gameover_text), getWidth(), getHeight(), 1, canvas);
                        drawBMP(getBitmap(R.drawable.replay_button), getWidth(), getHeight() - 100, 1, canvas);

                    }
                    break;
            }
        }
        drawUPS(canvas);
        drawFPS(canvas);
    }



    private void resetGame() {
        enemiesManager.reset();
        mainCharacter.dead(false);
        mainCharacter.reset();
        delay=400;
    }


    @Override
    public void update() {
        gameUpdate();
    }

    public boolean isReady(){
        return isReady;
    }
    public void inputJump(){
        mainCharacter.jump();
    }
    public void inputDown(boolean b){
        mainCharacter.down(b);
    }

    public boolean manageKeyEvent(KeyEvent e) {
        boolean res=false;
        if (!isKeyPressed) {
            isKeyPressed = true;
            switch (gameState) {
                case START_GAME_STATE: {
                    if (e.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
                        gameState = GAME_PLAYING_STATE;
                        res = true;
                    }
                    break;
                }
                case GAME_PLAYING_STATE: {
                    if (e.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
                        mainCharacter.jump();
                        res = true;
                    } else if (e.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
                        mainCharacter.down(true);
                        res = true;
                    }
                    break;
                }
                case GAME_OVER_STATE: {
                    if (e.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
                        mainCharacter.score = 0;
                        mainCharacter.setSpeedX(10);
                        gameState = GAME_PLAYING_STATE;
                        resetGame();
                        res = true;
                    }
                    break;
                }
            }
        }
        return res;
    }



/*
    @Override
    public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {

        return false;
    }
*/



}
