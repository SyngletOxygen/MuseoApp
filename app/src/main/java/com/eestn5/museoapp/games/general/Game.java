package com.eestn5.museoapp.games.general;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.eestn5.museoapp.R;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    public static double unitAPixel;
    private GameLoop gameloop;

    public Game(Context context) {
        super(context);
        this.unitAPixel = 1;


        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameloop = new GameLoop(this, surfaceHolder);

        setFocusable(true);
    }


    public MediaPlayer getSfx(int id){
        return MediaPlayer.create(getContext(), id);
    }

    public MediaPlayer[] getSfxs(int[] ids){
        MediaPlayer[] res = new MediaPlayer[ids.length];
        for(int x=0; x<ids.length; x++)
            res[x] = getSfx(ids[x]);
        return res;
    }

    public Bitmap getBitmap(int id){
        return BitmapFactory.decodeResource(getResources(), id);
    }

    public Bitmap[] getBitmaps(int[] ids){
        Bitmap[] res = new Bitmap[ids.length];
        for(int x=0; x<ids.length; x++)
            res[x] = getBitmap(ids[x]);
        return res;
    }















    public static void drawBMP(Bitmap bmp, int xU, int yU, double tam, @NonNull Canvas canvas) {
        Bitmap aUsar = bmp;

        if(tam!=1)//antes siempre se hacia esto, pero por optimizacion, solamente cuando es necesario
            aUsar = Bitmap.createScaledBitmap(
                    bmp,
                    (int)(bmp.getWidth()*tam),
                    (int)(bmp.getHeight()*tam),
                    false
            );

        canvas.drawBitmap(
                aUsar,
                (int)((double)(xU)*unitAPixel),
                (int)((double)(yU)*unitAPixel),
                null
        );
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameloop.getAverageUPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.purple_500));
        paint.setTextSize(10);
        canvas.drawText("UPS: "+averageUPS, 5, 15, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameloop.getAverageFPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));
        paint.setTextSize(10);
        canvas.drawText("FPS: "+averageFPS, 5, 30, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        /*
        Paint pintar = new Paint();
        pintar.setColor(Color.BLACK);
        pintar.setStrokeWidth(5);
        pintar.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0,0,100,100, pintar);
        */
        //dibujarBMP(R.drawable.main_character1, 0, 0, 1, canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if(gameloop.getState().equals(Thread.State.TERMINATED)){
            gameloop = new GameLoop(this, surfaceHolder);
        }
        gameloop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void update() {

    }

    public void pause() {
        gameloop.pauseLoop();
    }
}