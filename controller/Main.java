/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.Map;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

import model.GameData;
import model.MapManager;

/**
 *
 * @author trevo
 */
public class Main {
	public static GameData gameData;
	public static Animator animator;
	public static Map frame;
	public static MapManager gameMap;
	

	public static void main(String[] args) {

		gameMap = new MapManager();
		frame = new Map();
		frame.addKeyListener(new KeyController());
		animator = new Animator();

		// splash Screen
		posterPic();

		// icon of the game
		try {

			java.net.URL resource = frame.getClass().getResource("logo.jpg");
			BufferedImage image;
			image = ImageIO.read(resource);
			frame.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.setTitle("DEMO MAP");
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		gameData = new GameData();


        
		new Thread(animator).start();	   
	}

	// method splash Screen
	private static void posterPic() {
		JWindow window = new JWindow();

		ImageIcon posterPic = new ImageIcon("controller/poster.jpg");
		JLabel posterTittle = new JLabel("SDD CAPSTONE PROJECT", posterPic, JLabel.CENTER);
		posterTittle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 28));
		posterTittle.setVerticalTextPosition(JLabel.BOTTOM);
		posterTittle.setHorizontalTextPosition(JLabel.CENTER);

		window.getContentPane().add(posterTittle);

		window.setBounds(100, 100, 600, 400);
		window.setVisible(true);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.setVisible(false);
		window.dispose();
	}

}
