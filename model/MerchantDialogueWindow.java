package model;

import java.util.*;
import java.awt.*;
import java.io.*;
public class MerchantDialogueWindow {
	private final String MENU_HEADER_START;
	private boolean is_open;
	private int menu_size;
	private int menu_index;
	private String menu_header;
	private String[] menu_options;
	public MerchantDialogueWindow(String a) {
		is_open = false;
		menu_size = 0;
		menu_index = 0;
		setMenu_header(a);
		MENU_HEADER_START = a;
		menu_options = new String[0];
	}
	public MerchantDialogueWindow(String a, String[] b) {
		is_open = false;
		menu_size = b.length;
		menu_index = 0;
		setMenu_header(a);
		MENU_HEADER_START = a;
		menu_options = b;
	}
	public void concatinateStringToMenuHeader(String x) {
			setMenu_header("" + getMenu_header() + x);
		}
	public void resetMenuHeader() {
			setMenu_header(MENU_HEADER_START);
		}
	public void moveIndexUp() {
		if (menu_size > 0) {
			if(menu_index > 0) menu_index--;
			//renderWindow();
		}
	}
	public void moveIndexDown() {
		if (menu_size > 0) {
			
			if(menu_index < menu_size-1) menu_index++;
			//renderWindow();
		}
	}
	public int selectIndex() {
		closeWindow();
		return menu_index;
	}
	public int getIndex(){
		return menu_index;
	}
	public void openWindow() {
		menu_index = 0;
		is_open = true;
	}
	public void closeWindow() {
		is_open = false;
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
			if (getMenu_header().length() >= 27 && getMenu_header().length() < 54) {
				g.drawString(getMenu_header().substring(0,27), 60, 75);
				g.drawString(getMenu_header().substring(27,getMenu_header().length()), 60, 90);
			}
			else if (getMenu_header().length() >= 54) {
				g.drawString(getMenu_header().substring(0,27), 60, 75);
				g.drawString(getMenu_header().substring(27,54), 60, 90);
				g.drawString(getMenu_header().substring(54,getMenu_header().length()), 60, 105);
			}
			else {
				g.drawString(getMenu_header(), 60, 75);
			}
			for (int cntr = 0; cntr < menu_size; cntr++) {
				if (cntr == menu_index) {
					g.drawString("=>"/* + menu_options[cntr]*/, 60, 120 + (cntr+1)*15);
				}
				//else {
					g.drawString(menu_options[cntr], 60 + 20, 120 + (cntr+1)*15);
				//}
			}
		}
	}
	public void update() {
		
	}
	
	public void update(String[] inventoryNames) {
		menu_options = inventoryNames;
		menu_size = inventoryNames.length;
	}
	public String getMenu_header() {
		return menu_header;
	}
	public void setMenu_header(String menu_header) {
		this.menu_header = menu_header;
	}
	
}
