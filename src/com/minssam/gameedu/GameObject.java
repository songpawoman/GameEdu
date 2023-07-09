package com.minssam.gameedu;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class GameObject {
	GamePanel gamePanel;
	Image image;

	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	
	public GameObject(GamePanel gamePanel,int x, int y, int width, int height, int velX, int velY) {
		this.gamePanel=gamePanel;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.velX=velX;
		this.velY=velY;
	}
	
	
	public abstract void tick();
	public abstract void render(Graphics2D g);

}
