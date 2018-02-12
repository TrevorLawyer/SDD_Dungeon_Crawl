package model;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
public class Merchant extends GameFigure {
	
	/*public static final Item[] stock {
		new Item("Sword", 300);
		new Item("Shield", 200);
		new Item("Bow"), 100;
		new Item("Arrow", 50);
		new Item("Potion", 100);
	};*/
	
	public static final int GRID_CELL_SIZE = 240;
	public static final int SPRITE_SIZE = 240;
	
	// === DIALOGUE TYPES ===================================================== //
	
	public static final int GREETING = 0;
	public static final int INQUIRY = 1;
	public static final int FAREWELL = 3;
	public static final int SELL_TO_PLAYER = 4;
	public static final int SELL_CONFIRMATION = 5;
	public static final int NOT_ENOUGH_GOLD = 6;
	public static final int FULL_INVENTORY = 7;
	public static final int EMPTY_INVENTORY = 8;
	public static final int BUY_FROM_PLAYER = 9;
	public static final int BUY_CONFIRMATION = 10;
	
	
	public static final String[] menu_options_0 = {"Buy Items", "Sell Items", "Leave"};
	
	
	public static final MerchantDialogueWindow[] merchant_dialogue = {
		new MerchantDialogueWindow("Merchant: Hello! Welcome to my shop."),
		new MerchantDialogueWindow("Merchant: How can I help you today?", menu_options_0),
		new MerchantDialogueWindow("Merchant: Thank you for comming to my shop. Please come again!")
	};
	
	public Merchant(int x, int y) {
		super(x, y);
		try {
            super.currentPic = ImageIO.read(getClass().getResource("merchant.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open merchant.png");
            System.exit(-1);
        }
	}
	public void talkToPlayer(int dialogue_type) {
		merchant_dialogue[dialogue_type].openWindow();
	}
	public int getXGridLocation() {
		return x;
	}
	public int getYGridLocation() {
		return y;
	}
	public int getXPixelLocation() {
		return x*GRID_CELL_SIZE;
	}
	public int getYPixelLocation() {
		return y*GRID_CELL_SIZE;
	}
	@Override
	public void render(Graphics2D g) {
		g.drawImage(currentPic, x, y, 30, 30, null);
	}
	public void update() {
		
	}
	public Rectangle2D getCollisionBox() {
		return new Rectangle2D.Double(x*GRID_CELL_SIZE, y*GRID_CELL_SIZE, SPRITE_SIZE, SPRITE_SIZE);
	}
	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}
}