package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Map;
import model.GameFigure;
import model.GameData;
import model.Merchant;

public class KeyController implements KeyListener {
	Audio a = new Audio();
	boolean hasLeftEntranceTile = false;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
        	case KeyEvent.VK_UP:
        		if(GameData.hero.y>0 && Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			if(Main.gameMap.isPassable(GameData.hero.x, GameData.hero.y-1)) {
        				GameData.hero.y--;
        			}
        			a.playAudio("up.wav");
        		}
        		else if (Main.gameData.game_state == GameData.MERCHANT_DIALOG) {
        			Main.gameData.merchant_dialogue_window.moveIndexUp();
        		}
        		else if(Main.gameData.game_state == GameData.GAME_MENU){
        			Main.gameData.inventory_window.moveIndexUp();
        			System.out.println(Main.gameData.inventory_window.getIndex());
        		}
        		break;
        	case KeyEvent.VK_DOWN:
        		if(GameData.hero.y<Map.row-1 && Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;        			
        			if(Main.gameMap.isPassable(GameData.hero.x, GameData.hero.y+1)) {
        				GameData.hero.y++;
        			}
        			a.playAudio("down.mid");
        		}
        		else if (Main.gameData.game_state == GameData.MERCHANT_DIALOG) {
        			Main.gameData.merchant_dialogue_window.moveIndexDown();
        			System.out.println(Main.gameData.inventory_window.getIndex());
        		} 
        		else if(Main.gameData.game_state == GameData.GAME_MENU){
        			Main.gameData.inventory_window.moveIndexDown();
        		}
        		break;
        	case KeyEvent.VK_RIGHT:
        		if(GameData.hero.x<Map.col-1 && Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			if(Main.gameMap.isPassable(GameData.hero.x+1, GameData.hero.y)) {
        				GameData.hero.x++;
        			}
        			a.playAudio("right.wav");
        		}
        		else if(Main.gameData.game_state == GameData.MENU_EQUIPMENT){
        			Main.gameData.inventory_window.update(Main.gameData.hero.getInventoryNames());
        			Main.gameData.game_state = GameData.GAME_MENU;
        			Main.gameData.inventory_window.setMenu_header("Inventory");
        		}
        		break;
        	case KeyEvent.VK_LEFT:
        		if(GameData.hero.x>0 && Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			if(Main.gameMap.isPassable(GameData.hero.x-1, GameData.hero.y)) {
        				GameData.hero.x--;
        			}
        			a.playAudio("left.wav");
        		}
        		else if(Main.gameData.game_state == GameData.GAME_MENU){
        			Main.gameData.inventory_window.update(Main.gameData.hero.getEquipmentNames());
        			Main.gameData.game_state = GameData.MENU_EQUIPMENT;
        			Main.gameData.inventory_window.setMenu_header("Equipment");
        		}
        		break;
        	case KeyEvent.VK_ESCAPE:
 //       		Main.animator.gameState = Main.animator.GAME_OVER;
        		break;
        	case KeyEvent.VK_ENTER:
        		if (Main.gameData.game_state == GameData.MERCHANT_DIALOG) {
        			merchantActionProcedures();
        		}
        		else if(Main.gameData.game_state == GameData.GAME_MENU){
        			Main.gameData.hero.useItem(Main.gameData.inventory_window.getIndex());
        			Main.gameData.inventory_window.update(Main.gameData.hero.getInventoryNames());
        		}
        		break;
        		
    		case KeyEvent.VK_SPACE:
    			synchronized(Main.gameData.enemyFigures){
    				for(GameFigure g: Main.gameData.enemyFigures){
	    			if(2 > Math.abs(g.x-GameData.hero.x) && 2 > Math.abs(g.y-GameData.hero.y) ){
		    			Main.gameData.location_memory_min_1_x = g.x;
		    			Main.gameData.location_memory_min_1_y = g.y;
		    			g.health-=Main.gameData.hero.attack;
	    			//	Main.frame.swoosh();
		    			g.pain = 1;
		    		//	g.wrath = 1;
	    			}
    				}
    			}
    		break;
    		
    		case KeyEvent.VK_A:
				Main.gameData.game_state = GameData.GAME_RUNNING;
				for(GameFigure g: Main.gameData.enemyFigures){
	    			if((g.x == GameData.hero.x) || (g.y==GameData.hero.y)){
	    				Main.gameData.location_memory_min_1_x = g.x;
	    				Main.gameData.location_memory_min_1_y = g.y;
	    				g.health-=Main.gameData.hero.attack;
	    				g.pain = 1;
	    				//Main.frame.swoosh();
	    			//	g.wrath = 1;
	    		// 		Main.gameData.game_state = GameData.GAME_IDLE;
	    			}
				}
    		break;
    	
        	case KeyEvent.VK_MINUS:
        		Main.gameData.hero.setHealth(-1);
        		break;
        	
        	case KeyEvent.VK_EQUALS:
        		Main.gameData.hero.setHealth(1);
        		break;
      
        	case KeyEvent.VK_I:
        		System.out.println("Game State: "+Main.gameData.game_state);
        		//opens inventory window, switches game state to game_menu
        		if(Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.game_state = GameData.GAME_MENU;
        			Main.gameData.inventory_window.openWindow();
        			Main.gameData.inventory_window.update(Main.gameData.hero.getInventoryNames());
        		}
        		else if(Main.gameData.game_state == GameData.GAME_MENU || Main.gameData.game_state == GameData.MENU_EQUIPMENT){
        			Main.gameData.game_state = GameData.GAME_RUNNING;
        			//close inventory window
        			Main.gameData.inventory_window.closeWindow();
        		}
        		
        		break;
        		case KeyEvent.VK_L:
        		if(Main.gameData.game_state == GameData.GAME_RUNNING){
        		Main.gameData.hero.gainEXP(25);
        		}		
        	default:
        		break;
        }
		
		hasLeftEntranceTile = true;
		if(GameData.hero.x == Main.gameMap.getExit().x && GameData.hero.y == Main.gameMap.getExit().y) {
			Main.gameMap.goNext();
			GameData.hero.x = Main.gameMap.getEntrance().x;
			GameData.hero.y = Main.gameMap.getEntrance().y;
			hasLeftEntranceTile = false;
		}
		if(GameData.hero.x == Main.gameMap.getEntrance().x && GameData.hero.y == Main.gameMap.getEntrance().y && hasLeftEntranceTile) {
			Main.gameMap.goBack();
			GameData.hero.x = Main.gameMap.getExit().x;
			GameData.hero.y = Main.gameMap.getExit().y;
		}
		System.out.println("Hero coords\nx: "+GameData.hero.x+"  y: "+GameData.hero.y);
		Main.animator.switchTurns();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	private model.Item temp_item;
	private int temp_index;
	private static String[] temp_menu_options;
	
private void merchantActionProcedures() {
		
		// GREETING
		
		if (Main.gameData.player_dialogue_type == Merchant.GREETING) {
			Main.gameData.merchant_dialogue_window.selectIndex();
			Main.gameData.player_dialogue_type = Merchant.INQUIRY;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
			Main.gameData.merchant_dialogue_window.openWindow();
		} 
		
		// INQUIRY
		
		else if (Main.gameData.player_dialogue_type == Merchant.INQUIRY) {
			int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
			if (temp_menu_selection == 2) {
				Main.gameData.player_dialogue_type = Merchant.FAREWELL;
				Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.FAREWELL];
				Main.gameData.merchant_dialogue_window.openWindow();
			}
			else if (temp_menu_selection == 1) {
				//System.out.println("Inventory Size: " + Main.gameData.hero.inventory.size());
				if (Main.gameData.hero.inventory.size() > 0) {
					temp_menu_options = new String[Main.gameData.hero.inventory.size()];
					for (int cntr = 0; cntr < Main.gameData.hero.inventory.size(); cntr++) {
						temp_menu_options[cntr] = "" + Main.gameData.hero.inventory.get(cntr).name + " for " + Main.gameData.hero.inventory.get(cntr).worth_in_gold + " gold";
					}
					Main.gameData.player_dialogue_type = Merchant.BUY_FROM_PLAYER;
			 		Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.BUY_FROM_PLAYER];
			 		Main.gameData.merchant_dialogue_window.setNoMenuOptions();
			 		Main.gameData.merchant_dialogue_window.setMenuOptions(temp_menu_options);
			 		Main.gameData.merchant_dialogue_window.openWindow();
				}
				else {
					Main.gameData.player_dialogue_type = Merchant.EMPTY_INVENTORY;
			 		Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.EMPTY_INVENTORY];
			 		Main.gameData.merchant_dialogue_window.openWindow();
				}
			}
			else if (temp_menu_selection == 0) {
				Main.gameData.player_dialogue_type = Merchant.SELL_TO_PLAYER;
		 		Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.SELL_TO_PLAYER];
		 		Main.gameData.merchant_dialogue_window.resetMenuHeader();
		 		Main.gameData.merchant_dialogue_window.concatinateStringToMenuHeader(" | You have " + model.GameData.hero.gold + " gold.");
		 		Main.gameData.merchant_dialogue_window.openWindow();
			}
			else {
				Main.gameData.player_dialogue_type = Merchant.GREETING;
				Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.GREETING];
				Main.gameData.game_state = GameData.GAME_RUNNING;
			}
		}
		
		// FARWELL
		
		else if (Main.gameData.player_dialogue_type == Merchant.FAREWELL) {
			Main.gameData.merchant_dialogue_window.selectIndex();
			Main.gameData.player_dialogue_type = Merchant.GREETING;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.GREETING];
			Main.gameData.game_state = GameData.GAME_RUNNING;
		}
		
		// SELL TO PLAYER
		
		else if (Main.gameData.player_dialogue_type == Merchant.SELL_TO_PLAYER) {
			int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
			temp_item = model.Merchant.stock[temp_menu_selection];
			Main.gameData.player_dialogue_type = Merchant.SELL_CONFIRMATION;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.SELL_CONFIRMATION];
			Main.gameData.merchant_dialogue_window.resetMenuHeader();
			Main.gameData.merchant_dialogue_window.concatinateStringToMenuHeader("" + Merchant.menu_options_1[temp_menu_selection] + "?");
			Main.gameData.merchant_dialogue_window.openWindow();
		}
		
		// SELL CONFIRMATION
		
		else if (Main.gameData.player_dialogue_type == Merchant.SELL_CONFIRMATION) {
			int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
			if (temp_menu_selection == 0) {
				switch (transactionActionProcedures(temp_item)) {
					case 0:											// NOT ENOUGH GOLD
						Main.gameData.player_dialogue_type = Merchant.NOT_ENOUGH_GOLD;
			 			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.NOT_ENOUGH_GOLD];
			 			Main.gameData.merchant_dialogue_window.openWindow();	
						break;
					case -1:										// INVENTORY FULL
						Main.gameData.player_dialogue_type = Merchant.FULL_INVENTORY;
			 			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.FULL_INVENTORY];
			 			Main.gameData.merchant_dialogue_window.openWindow();
						break;
					case 1:
						Main.gameData.player_dialogue_type = Merchant.THANK_YOU_FOR_PURCHASE;
			 			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.THANK_YOU_FOR_PURCHASE];
			 			Main.gameData.merchant_dialogue_window.openWindow();
			 			break;	
				}
			}
			else if (temp_menu_selection == 1) {
				Main.gameData.merchant_dialogue_window.selectIndex();
				Main.gameData.player_dialogue_type = Merchant.INQUIRY;
				Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
				Main.gameData.merchant_dialogue_window.openWindow();
			}
		}
		
		// THANK YOU FOR PURCHASE
		
		else if (Main.gameData.player_dialogue_type == Merchant.THANK_YOU_FOR_PURCHASE) {
			Main.gameData.merchant_dialogue_window.selectIndex();
			Main.gameData.player_dialogue_type = Merchant.INQUIRY;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
			Main.gameData.merchant_dialogue_window.openWindow();
		}
		
		// NOT_ENOUGH_GOLD
		
		else if (Main.gameData.player_dialogue_type == Merchant.NOT_ENOUGH_GOLD) {
			Main.gameData.merchant_dialogue_window.selectIndex();
			Main.gameData.player_dialogue_type = Merchant.INQUIRY;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
			Main.gameData.merchant_dialogue_window.openWindow();
		}
		
		// FULL_INVENTORY
		
		else if (Main.gameData.player_dialogue_type == Merchant.FULL_INVENTORY) {
			Main.gameData.merchant_dialogue_window.selectIndex();
			Main.gameData.player_dialogue_type = Merchant.INQUIRY;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
			Main.gameData.merchant_dialogue_window.openWindow();
		}
		
		// BUY_FROM_PLAYER
		
		else if (Main.gameData.player_dialogue_type == Merchant.BUY_FROM_PLAYER) {
			int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
			temp_index = temp_menu_selection;
			Main.gameData.player_dialogue_type = Merchant.BUY_CONFIRMATION;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.BUY_CONFIRMATION];
			Main.gameData.merchant_dialogue_window.resetMenuHeader();
			Main.gameData.merchant_dialogue_window.concatinateStringToMenuHeader("" + temp_menu_options[temp_menu_selection] + "?");
			Main.gameData.merchant_dialogue_window.openWindow();
		}
		
		// BUY CONFIRMATION
		
		else if (Main.gameData.player_dialogue_type == Merchant.BUY_CONFIRMATION) {
			int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
			if (temp_menu_selection == 0) {
				transactionActionProcedures(temp_index);
				Main.gameData.player_dialogue_type = Merchant.THANK_YOU_FOR_SALE;
	 			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.THANK_YOU_FOR_SALE];
	 			Main.gameData.merchant_dialogue_window.openWindow();
			}
			else if (temp_menu_selection == 1) {
				Main.gameData.merchant_dialogue_window.selectIndex();
				Main.gameData.player_dialogue_type = Merchant.INQUIRY;
				Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
				Main.gameData.merchant_dialogue_window.openWindow();
			}
		}
		
		// EMPTY_INVENTORY
		
		else if (Main.gameData.player_dialogue_type == Merchant.EMPTY_INVENTORY) {
			Main.gameData.merchant_dialogue_window.selectIndex();
			Main.gameData.player_dialogue_type = Merchant.INQUIRY;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
			Main.gameData.merchant_dialogue_window.openWindow();
		}
		
		// THANK YOU FOR SALE
		
		else if (Main.gameData.player_dialogue_type == Merchant.THANK_YOU_FOR_SALE) {
			Main.gameData.merchant_dialogue_window.selectIndex();
			Main.gameData.player_dialogue_type = Merchant.INQUIRY;
			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
			Main.gameData.merchant_dialogue_window.openWindow();
		}
		
	}
								
private int transactionActionProcedures(model.Item i) {			// player buys
	if (Main.gameData.hero.gold >= i.worth_in_gold) {
		if (Main.gameData.hero.inventory.size() < 8) {
			Main.gameData.hero.gold -= i.worth_in_gold;
			Main.gameData.hero.AddItemToInventory(i);
		}
		else {
			return -1;
		}
	}
	else {
		return 0;
	}
	return 1;
}
private boolean transactionActionProcedures(int i) {			// player sells
	if (Main.gameData.hero.inventory.size() > 0) {
		Main.gameData.hero.gold += Main.gameData.hero.inventory.get(i).worth_in_gold;
		Main.gameData.hero.removeItemFromInventory(i);
	}
	else {
		return false;
	}
	return true;
}
}