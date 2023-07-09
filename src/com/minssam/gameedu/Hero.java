package com.minssam.gameedu;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.minssam.gameedu.util.ImageManager;

public class Hero extends GameObject{
	
	int state=0; 
	Map<Integer, List> sprites=new HashMap<Integer, List>();
	
	//정방향
	List<Image> idleList = new ArrayList<Image>(); //서있는 동작 
	List<Image> runList = new ArrayList<Image>(); //뛰는 동작
	List<Image> slideList = new ArrayList<Image>(); //슬라이딩 동작
	List<Image> attackList = new ArrayList<Image>(); //공격 동작
	
	//역방향
	List<Image> idleReverseList = new ArrayList<Image>(); //서있는 동작 
	List<Image> runReverseList = new ArrayList<Image>(); //뛰는 동작
	List<Image> slideReverseList = new ArrayList<Image>(); //슬라이딩 동작
	List<Image> attackReverseList = new ArrayList<Image>(); //공격 동작
	
	
	boolean isRight=true;
	public static final int IDLE=0;
	public static final int RUN=1;
	public static final int SLIDE=2;
	public static final int ATTACK=3;
	
	public static final int IDLE_LEFT=4;
	public static final int RUN_LEFT=5;
	public static final int SLIDE_LEFT=6;
	public static final int ATTACK_LEFT=7;
	
	
	
	boolean onAction=false; //액션중인지 판단하는 논리값
	int interval=0; //스프라이트 간 간격 조정
	int actionIndex=0; //몇번째 이미지를 접근할지를 결정하는 indx
	
	public Hero(GamePanel gamePanel, int x, int y, int width, int height, int velX, int velY) {
		super(gamePanel, x, y, width, height, velX, velY);
		
		createSprite();
	}

	/*-------------------------------------------------------------
 	스프라이트 이미지 생성하기
	 -------------------------------------------------------------*/
	public void createSprite() {
		String[] idlePath={"Idle__000.png","Idle__001.png","Idle__002.png","Idle__003.png","Idle__004.png","Idle__005.png","Idle__006.png","Idle__007.png","Idle__008.png","Idle__009.png"}; 
		String[] runPath={"Run__000.png","Run__001.png","Run__002.png","Run__003.png","Run__004.png","Run__005.png","Run__006.png","Run__007.png","Run__008.png","Run__009.png"}; 
		String[] slidePath={"Slide__000.png","Slide__001.png","Slide__002.png","Slide__003.png","Slide__004.png","Slide__005.png","Slide__006.png","Slide__007.png","Slide__008.png","Slide__009.png"}; 
		String[] attackPath={"Attack__000.png","Attack__001.png","Attack__002.png","Attack__003.png","Attack__004.png","Attack__005.png","Attack__006.png","Attack__007.png","Attack__008.png","Attack__009.png"}; 
		
		//서있기
		for(int i=0;i<idlePath.length;i++) {
			idleList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+idlePath[i], 35, 65, false));
		}
		//서있기 역방향
		for(int i=0;i<idlePath.length;i++) {
			idleReverseList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+idlePath[i], 35, 65, true));
		}
		
		//뛰기
		for(int i=0;i<runPath.length;i++) {
			runList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+runPath[i], 60, 65, false));
		}
		//뛰기 역방향
		for(int i=0;i<runPath.length;i++) {
			runReverseList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+runPath[i], 60, 65, true));
		}
		
		//슬라이드
		for(int i=0;i<slidePath.length;i++) {
			slideList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+slidePath[i], 60, 50, false));
		}
		//슬라이드 역박향
		for(int i=0;i<slidePath.length;i++) {
			slideReverseList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+slidePath[i], 60, 50, true));
		}
		
		//공격
		for(int i=0;i<attackPath.length;i++) {
			attackList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+attackPath[i], 80, 70,false));
		}
		//공격 역방향
		for(int i=0;i<attackPath.length;i++) {
			attackReverseList.add(ImageManager.getImage("com/minssam/gameedu/res/hero/"+attackPath[i], 80, 70,true));
		}
		
		sprites.put(IDLE, idleList);
		sprites.put(RUN, runList);
		sprites.put(SLIDE, slideList);
		sprites.put(ATTACK, attackList);
		
		sprites.put(IDLE_LEFT, idleReverseList);
		sprites.put(RUN_LEFT, runReverseList);
		sprites.put(SLIDE_LEFT, slideReverseList);
		sprites.put(ATTACK_LEFT, attackReverseList);
		
	}
	
	public void action(Graphics2D g, int state) {
		List<Image> actionList = sprites.get(state); // state 번째 요소 가져오기
		
		interval++;
		
		int per=0;
		int width=0;
		int height=0;
		
		switch(state) {
			case IDLE :per=4;width=35;height=65;break;
			case IDLE_LEFT :per=4;width=35;height=65;break;
			
			case RUN :per=2;width=60;height=65;break;
			case RUN_LEFT :per=2;width=60;height=65;break;
			
			case SLIDE :per=5;width=60;height=50;break;
			case SLIDE_LEFT :per=5;width=60;height=50;break;
			
			case ATTACK :per=4;width=80;height=70;break;
			case ATTACK_LEFT :per=4;width=80;height=70;break;
		}
		
		if((interval%per)==0){
			actionIndex++;
			
			if(actionIndex >= actionList.size()) {
				actionIndex=0;
				this.state=state; //현재 상태를, 넘겨받은 state 상태로 둔다 (예) 좌측을 쳐다보았다면 좌측유지)
			}
		}
		
		g.drawImage(actionList.get(actionIndex) , x, y, width, height, gamePanel);
		
	}
	
	@Override
	public void tick() {
		this.x+=this.velX;
		this.y+=this.velY;
	}

	@Override
	public void render(Graphics2D g) {
		action(g, state);
		
		g.drawString("hero.isRight="+isRight+"x="+x, 50, 20);
	}
	
}

