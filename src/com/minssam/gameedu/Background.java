package com.minssam.gameedu;

import java.awt.Graphics2D;
import java.awt.Image;
import com.minssam.gameedu.util.ImageManager;

public class Background extends GameObject{
	
	public Background(GamePanel gamePanel,int x, int y, int width, int height, int velX, int velY) {
		super(gamePanel,x, y, width, height, velX, velY);
		image = ImageManager.getImage("com/minssam/gameedu/res/bg.png", GamePanel.WIDTH, GamePanel.HEIGHT, false);
	}
	
	public void tick() {
		this.x+=this.velX;
		
		if(this.x<= -GamePanel.WIDTH) {
			this.x=GamePanel.WIDTH;
		}
		
	}

	public void render(Graphics2D g) {
		//부하를 줄이기 위해 translate()로 대체한다
		g.drawImage(image, x, y, GamePanel.WIDTH, GamePanel.HEIGHT, gamePanel);
		
	}	
}
