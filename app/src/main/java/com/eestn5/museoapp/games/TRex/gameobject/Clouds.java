package com.eestn5.museoapp.games.TRex.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.eestn5.museoapp.games.general.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Clouds {
	private List<ImageCloud> listCloud;
	private Bitmap cloud;
	private int w;
	private MainCharacter mainCharacter;
	
	public Clouds(int width, MainCharacter mainCharacter, Bitmap cloud, int w) {
		this.w = w;
		this.mainCharacter = mainCharacter;
		this.cloud = cloud;
		listCloud = new ArrayList<ImageCloud>();
		
		ImageCloud imageCloud = new ImageCloud();
		imageCloud.posX = 0;
		imageCloud.posY = 30;
		listCloud.add(imageCloud);
		
		imageCloud = new ImageCloud();
		imageCloud.posX = 150;
		imageCloud.posY = 40;
		listCloud.add(imageCloud);
		
		imageCloud = new ImageCloud();
		imageCloud.posX = 300;
		imageCloud.posY = 50;
		listCloud.add(imageCloud);
		
		imageCloud = new ImageCloud();
		imageCloud.posX = 450;
		imageCloud.posY = 20;
		listCloud.add(imageCloud);
		
		imageCloud = new ImageCloud();
		imageCloud.posX = 600;
		imageCloud.posY = 60;
		listCloud.add(imageCloud);
	}
	
	public void update(){
		Iterator<ImageCloud> itr = listCloud.iterator();
		ImageCloud firstElement = itr.next();
		firstElement.posX -= mainCharacter.getSpeedX()/8;
		while(itr.hasNext()) {
			ImageCloud element = itr.next();
			element.posX -= mainCharacter.getSpeedX()/8;
		}
		if(firstElement.posX < -cloud.getWidth()) {
			listCloud.remove(firstElement);
			firstElement.posX = w;
			listCloud.add(firstElement);
		}
	}
	
	public void draw(Canvas canvas) {
		for(ImageCloud imgLand : listCloud) {
			Game.drawBMP(cloud, (int) imgLand.posX, imgLand.posY, 1, canvas);
		}
	}
	
	private class ImageCloud {
		float posX;
		int posY;
	}
}
