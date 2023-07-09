package com.minssam.gameedu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GamePanel gamePanel;
	
	public GameFrame() {
		gamePanel = new GamePanel();
		
		add(gamePanel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
		gamePanel.requestFocus();
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	
	public static void main(String[] args) {
		new GameFrame();
	}
}
