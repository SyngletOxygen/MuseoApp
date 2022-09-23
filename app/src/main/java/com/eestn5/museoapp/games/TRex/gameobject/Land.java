package com.eestn5.museoapp.games.TRex.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.eestn5.museoapp.games.general.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Land {

	private int w=-1;
	private int h=300;
	private List<ImageLand> listLand;
	private Bitmap[] land = new Bitmap[3];
	
	private MainCharacter mainCharacter;
	
	private class ImageLand {
		float posX;
		Bitmap image;
	}
	
	public Land(int width, MainCharacter mainCharacter, Bitmap[] land, int h) {
		this.w = width;
		this.h = h;
		this.mainCharacter = mainCharacter;
		this.land = land;
		int numberOfImageLand = (width / land[0].getWidth()) + 2;
		listLand = new ArrayList<ImageLand>();
		for(int i = 0; i < numberOfImageLand; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = i * land[0].getWidth();
			setImageLand(imageLand);
			listLand.add(imageLand);
		}
	}
	
	public void update(){
		Iterator<ImageLand> itr = listLand.iterator();
		ImageLand firstElement = itr.next();
		firstElement.posX -= mainCharacter.getSpeedX();
		float previousPosX = firstElement.posX;
		while(itr.hasNext()) {
			ImageLand element = itr.next();
			element.posX -= mainCharacter.getSpeedX();
			previousPosX = element.posX;
		}
		if(firstElement.posX < -land[0].getWidth()) {
			listLand.remove(firstElement);
			firstElement.posX = previousPosX + land[0].getWidth();
			setImageLand(firstElement);
			listLand.add(firstElement);
		}
	}
	
	private void setImageLand(ImageLand imgLand) {
		int typeLand = getTypeOfLand();
		imgLand.image = land[typeLand];
	}
	
	public void draw(Canvas canvas) {
		for(ImageLand imgLand : listLand) {
			Game.drawBMP(imgLand.image, (int) imgLand.posX, (int)(h/2), 1, canvas);
		}
	}
	
	private int getTypeOfLand() {
		Random rand = new Random();
		int type = rand.nextInt(10);
		if(type == 1) {
			return 0;
		} else if(type == 9) {
			return 2;
		} else {
			return 1;
		}
	}



}
