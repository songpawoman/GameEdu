package com.minssam.gameedu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	public static final int WIDTH=1024;
	public static final int HEIGHT=768;
	
	Thread loopThread;
	boolean loopFlag=true;
	Background[] bgArray = new Background[2];
	MapManager mapManager = new MapManager();
	List<Brick> brickList=new ArrayList<Brick>();
	
	Hero hero;
	
	Graphics2D g2;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		createBackground();
		createBrick();
		createHero();
		
		loopThread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
					loop();
				}
			}
		};
		loopThread.start();
		
		
		//키보드 연결 
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:moveX(-5);break;
					case KeyEvent.VK_RIGHT:moveX(5);break;
					case KeyEvent.VK_UP:moveY(-5);hero.state=1;break;
					case KeyEvent.VK_DOWN:moveY(5);hero.state=1;break;
					case KeyEvent.VK_SPACE:slide();break;
					case KeyEvent.VK_A:attack();break;
					case KeyEvent.VK_ESCAPE:pause();break;
				}
			}
			
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:moveX(0);break;
					case KeyEvent.VK_RIGHT:moveX(0);break;
					case KeyEvent.VK_UP:moveY(0);break;
					case KeyEvent.VK_DOWN:moveY(0);break;
					case KeyEvent.VK_A:stop();break;
					case KeyEvent.VK_SPACE:slide();break;
					
				}
			}
			
		});
	}
	
	//배경이미지 생성 
	public void createBackground() {
		bgArray[0]= new Background(this, 0, 0, WIDTH, HEIGHT, 0, 0);
		bgArray[1]= new Background(this, WIDTH, 0, WIDTH, HEIGHT, 0, 0);
	}
	
	//벽돌 생성 
	public void createBrick() {
		for(int i=0;i<mapManager.mapArray.length;i++) {
			for(int a=0;a<mapManager.mapArray[i].length;a++) {
				for(int k=0;k<mapManager.mapArray[i][a].length;k++) {
					if(mapManager.mapArray[i][a][k]==1) {
						brickList.add(new Brick(this, k*50, a*50, 50, 50, 0, 0));
					}
				}
			}
		}
	}
	
	public void createHero() {
		hero = new Hero(this, 100, 200, 50, 65, 0, 0);
	}
	
	@Override
	public void paint(Graphics g) {
		g2=(Graphics2D)g;
		
		/*--------------------------------------
		 배경이미지에 대한 tick(), render()
		--------------------------------------*/
		for(int i=0;i<bgArray.length;i++) {
			bgArray[i].tick();
			bgArray[i].render(g2);
			
		}

		/*--------------------------------------
		 벽돌에 대한 tick() , render()
		--------------------------------------*/
		for(int i=0;i<brickList.size();i++) {
			Brick brick=brickList.get(i);
			brick.tick();
			brick.render(g2);
		}

		/*--------------------------------------
		 주인공에 대한 tick() , render()
		--------------------------------------*/
		hero.tick();
		hero.render(g2);
		
		
	}
	
	public void moveX(int velX) {
		
		hero.velX=velX;
		
		//좌, 우 키보드에서 손을 떼면
		if(velX<0) {
			hero.isRight=false;
			hero.state=Hero.RUN_LEFT;
		}if(velX==0) {
			stop();
		}else if(velX>0){
			hero.isRight=true;
			hero.state=Hero.RUN;
		}
	}
	
	public void moveY(int velY) {
		hero.velY=velY;
	}
	
	public void attack() {
		
		if(hero.isRight) {
			hero.state=Hero.ATTACK;
		}else{
			hero.state=Hero.ATTACK_LEFT;
		}
	}
	
	public void slide() {
		if(hero.velX<0) {
			hero.state=Hero.SLIDE_LEFT;
		}else {
			hero.state=Hero.SLIDE;
		}
	}
	
	public void stop() {
		if(hero.isRight) {
			hero.state=Hero.IDLE;
		}else {
			hero.state=Hero.IDLE_LEFT;
		}	
	}
	
	public void jump(int velY) {
		
	}
	
	public void pause() {
		this.loopFlag=!this.loopFlag;
	}
	
	public void loop() {
		if(loopFlag) {
			repaint();
		}
	}
	
}
