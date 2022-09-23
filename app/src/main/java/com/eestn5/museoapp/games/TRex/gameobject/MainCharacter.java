package com.eestn5.museoapp.games.TRex.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;

import com.eestn5.museoapp.games.TRex.util.Animation;
import com.eestn5.museoapp.games.general.Game;


public class MainCharacter {

	public static final float GRAVITY = 0.4f;

	public int landPosY = 80;
	private static final int NORMAL_RUN = 0;
	private static final int JUMPING = 1;
	private static final int DOWN_RUN = 2;
	private static final int DEATH = 3;
	
	private float posY;
	public float posX;
	private float speedX;
	private float speedY;
	private Rect rectBound;
	
	public int score = 0;
	public int highScore = 0;
	
	private int state = NORMAL_RUN;
	
	private Animation normalRunAnim;
	private Bitmap jumping;
	private Animation downRunAnim;
	private Bitmap deathImage;
	
	private MediaPlayer jumpSound;
	private MediaPlayer deadSound;
	private MediaPlayer scoreUpSound;
	private MediaPlayer beep;
	
	public MainCharacter(Bitmap[] normalRun, Bitmap[] downRun, Bitmap jump, Bitmap death, MediaPlayer[] sfx, int landPosY) {
		this.landPosY = landPosY;
		posX = 50;
		posY = landPosY;
		rectBound = new Rect();
		normalRunAnim = new Animation(90);
		normalRunAnim.addFrame(normalRun[0]);//1
		normalRunAnim.addFrame(normalRun[1]);//2
		downRunAnim = new Animation(90);
		downRunAnim.addFrame(downRun[0]);//5
		downRunAnim.addFrame(downRun[1]);//6

		jumping = jump;//3
		deathImage = death;//4

		jumpSound =  	sfx[0];
		deadSound =  	sfx[1];
		scoreUpSound =  sfx[2];
		beep = 			sfx[3];
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void draw(Canvas canvas) {
		switch(state) {
			case NORMAL_RUN:
				Game.drawBMP(normalRunAnim.getFrame(), (int) posX, (int) posY, 1, canvas);
				break;
			case JUMPING:
				Game.drawBMP(jumping, (int) posX, (int) posY, 1, canvas);
				break;
			case DOWN_RUN:
				Game.drawBMP(downRunAnim.getFrame(), (int) posX, (int) (posY + 20), 1, canvas);
				break;
			case DEATH:
				Game.drawBMP(deathImage, (int) posX, (int) posY, 1, canvas);
				break;
		}
//		Rectangle bound = getBound();
//		g.setColor(Color.RED);
//		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
	public void update() {
		normalRunAnim.updateFrame();
		downRunAnim.updateFrame();
		if(posY >= landPosY) {
			posY = landPosY;
			if(state != DOWN_RUN) {
				state = NORMAL_RUN;
			}
		} else {
			speedY += GRAVITY;
			posY += speedY;
		}
	}
	
	public void jump() {
		if(posY >= landPosY) {
			if(jumpSound != null) {
				jumpSound.start();
			}
			speedY = -(landPosY/30);//kawaboonga
			System.out.println("landpos "+ landPosY);
			posY += speedY;
			state = JUMPING;
		}
		playBeepSound();
	}
	
	public void down(boolean isDown) {
		if(state == JUMPING) {
			return;
		}
		if(isDown) {
			state = DOWN_RUN;
		} else {
			state = NORMAL_RUN;
		}
	}
	
	public Rect getBound() {
		rectBound = new Rect();
		if(state == DOWN_RUN) {
			rectBound.left = (int) posX + 5;
			rectBound.top = (int) posY + 20;
			rectBound.right = rectBound.left + downRunAnim.getFrame().getWidth() - 10;
			rectBound.bottom = rectBound.top + downRunAnim.getFrame().getHeight();
		} else {
			rectBound.left = (int) posX + 5;
			rectBound.top = (int) posY + 20;
			rectBound.right = rectBound.left + normalRunAnim.getFrame().getWidth() - 10;
			rectBound.bottom = rectBound.top + normalRunAnim.getFrame().getHeight();
		}
		return rectBound;
	}
	
	public void dead(boolean isDeath) {
		if(isDeath) {
			state = DEATH;

		} else {
			state = NORMAL_RUN;
		}
	}
	
	public void reset() {
		posY = landPosY;
	}
	
	public void playDeadSound() {
		deadSound.start();
	}
	public void playBeepSound() {
		beep.start();
	}
	
	public void upScore() {
		score += 20;
		if(score % 20 == 0) {
			scoreUpSound.start();
		}
		speedX*=1.05;
		if(score > highScore)
			highScore=score;
	}
	
}
