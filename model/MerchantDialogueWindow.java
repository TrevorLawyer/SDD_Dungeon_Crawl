package model;

import java.util.*;
import java.awt.*;
import java.io.*;
public class MerchantDialogueWindow {
	private boolean is_open;
	private int menu_size;
	private int menu_index;
	private String menu_header;
	private String[] menu_options;
	
	
	public MerchantDialogueWindow(String a) {
		is_open = false;
		menu_size = 0;
		menu_index = 0;
		menu_header = a;
		menu_options = new String[0];
	}
	public MerchantDialogueWindow(String a, String[] b) {
		is_open = false;
		menu_size = b.length;
		menu_index = 0;
		menu_header = a;
		menu_options = b;
	}
	public void moveIndexUp() {
		if (menu_size > 0) {
			if(menu_index < menu_size-1) menu_index++;
			//renderWindow();
		}
	}
	public void moveIndexDown() {
		if (menu_size > 0) {
			if(menu_index > 0) menu_index--;
			//renderWindow();
		}
	}
	public int selectIndex() {
		closeWindow();
		return menu_index;
	}
	public void openWindow() {
		is_open = true;
		System.out.println("Opening Window");
	}
	public void closeWindow() {
		is_open = false;
		System.out.println("Closing Window");
	}
	public boolean isOpen() {
		return is_open;
	}
	public boolean isClosed() {
		return !is_open;
	}
	public void render(Graphics2D g) {
		if (isOpen()) {
			g.setColor(Color.BLACK);
			g.draw3DRect(50, 50, 200, 200, true);
			g.setColor(Color.GRAY);
			g.fill3DRect(50, 50, 200, 200, true);
			g.setColor(Color.WHITE);
			if(menu_header.length()>28){
				g.drawString(menu_header.substring(0,28), 60, 75);
				g.drawString(menu_header.substring(28,menu_header.length()-1), 60, 85);
			} else{
				g.drawString(menu_header, 60, 75);
			}
		}
	}
	public void update() {
		
	}
	
	public void update(String[] newStuff){
		menu_options = newStuff;
	}
}
