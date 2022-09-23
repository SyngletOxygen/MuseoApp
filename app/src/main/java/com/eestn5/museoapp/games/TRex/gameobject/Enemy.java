package com.eestn5.museoapp.games.TRex.gameobject;

import android.graphics.Canvas;
import android.graphics.Rect;


public abstract class Enemy {
	public abstract void update();
	public abstract void draw(Canvas canvas);
	public abstract Rect getBound();
	public abstract boolean isOutOfScreen();
}
