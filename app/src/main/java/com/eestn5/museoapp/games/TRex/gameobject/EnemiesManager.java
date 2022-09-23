package com.eestn5.museoapp.games.TRex.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EnemiesManager {
	
	private Bitmap[] cactus;
	private Random rand;
	private int yLand;
	private int xLand;
	
	public List<Enemy> enemies;
	private MainCharacter mainCharacter;
	
	public EnemiesManager(MainCharacter mainCharacter, Bitmap[] cactuses, int yLand,int xLand) {
		this.yLand=yLand;
		rand = new Random();
		this.xLand = xLand;
		this.cactus = cactuses;
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
	}
	
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			mainCharacter.upScore();
			enemies.clear();
			mainCharacter.playBeepSound();
			enemies.add(createEnemy());
		}
	}
	
	public void draw(Canvas canvas) {
		for(Enemy e : enemies) {
			e.draw(canvas);
		}
	}
	
	private Enemy createEnemy() {
		// if (enemyType = getRandom)
		int type = rand.nextInt(2);
		return new Cactus(mainCharacter, this.xLand, cactus[type].getWidth() - 10, cactus[type].getHeight() - 10, cactus[type], yLand);
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersect(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
	}
	
}
