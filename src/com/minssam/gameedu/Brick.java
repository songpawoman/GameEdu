package com.minssam.gameedu;

import java.awt.Graphics2D;

import com.minssam.gameedu.util.ImageManager;

public class Brick extends GameObject{
	
	public Brick(GamePanel gamePanel, int x, int y, int width, int height, int velX, int velY) {
		super(gamePanel, x, y, width, height, velX, velY);
		image = ImageManager.getImage("com/minssam/gameedu/res/StoneBlock.png", 50, 50,false);
	}

	@Override
	public void tick() {
		this.x+=this.velX;
		this.y+=this.velY;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, x, y, 50, 50, gamePanel);	
	}
	
}
