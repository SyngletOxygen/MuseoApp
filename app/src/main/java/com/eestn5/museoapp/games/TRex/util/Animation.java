package com.eestn5.museoapp.games.TRex.util;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Animation {

	private ArrayList<Bitmap> list;
	private long deltaTime;
	private int currentFrame = 0;
	private long previousTime;

	public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		list = new ArrayList<Bitmap>();
		previousTime = 0;
	}

	public void updateFrame() {
		if (System.currentTimeMillis() - previousTime >= deltaTime) {
			currentFrame++;
			if (currentFrame >= list.size()) {
				currentFrame = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}

	public void addFrame(Bitmap image) {
		list.add(image);
	}

	public Bitmap getFrame() {
		return list.get(currentFrame);
	}

}
