package com.eestn5.museoapp.games.TRex.gameobject;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.eestn5.museoapp.games.general.Game;

public class Cactus extends Enemy {
	
	public int yLand;
	public boolean alertado=false;
	public int posX;
	private int width;
	private int height;
	
	private Bitmap image;
	private MainCharacter mainCharacter;
	
	private Rect rectBound;
	
	public Cactus(MainCharacter mainCharacter, int posX, int width, int height, Bitmap image, int yLand) {
		this.yLand=yLand;
		this.posX = posX;
		this.width = width;
		this.height = height;
		this.image = image;
		this.mainCharacter = mainCharacter;
		rectBound = new Rect();
	}
	
	public void update() {
		posX -= mainCharacter.getSpeedX();
	}
	
	public void draw(Canvas canvas) {
		Game.drawBMP(image, posX, yLand - image.getHeight(), 1, canvas);
	}
	
	public Rect getBound() {
		rectBound = new Rect();
		rectBound.left = (int) posX + (image.getWidth() - width)/2;
		rectBound.top = yLand - image.getHeight() + (image.getHeight() - height)/2;
		rectBound.right = rectBound.left + width;
		rectBound.bottom = rectBound.top + height;
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		if(posX < -image.getWidth()) {
			return true;
		}
		return false;
	}
	
}
