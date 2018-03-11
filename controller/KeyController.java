package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Map;
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
          		
    			if(2 > Math.abs(GameData.enemy.x-GameData.hero.x) && 2 > Math.abs(GameData.enemy.y-GameData.hero.y) && (Main.gameData.game_state == GameData.GAME_RUNNING)){
    			Main.gameData.location_memory_min_1_x = GameData.enemy.x;
    			Main.gameData.location_memory_min_1_y = GameData.enemy.y;
    			Main.gameData.enemy.health-=Main.gameData.hero.attack;
    			Main.gameData.enemy.pain = 1;
    			Main.gameData.hero.wrath = 1;
    		}
    		break;
    		
    		case KeyEvent.VK_A:
          		
    			if((GameData.enemy.x == GameData.hero.x) || (GameData.enemy.y==GameData.hero.y) && (Main.gameData.game_state == GameData.GAME_RUNNING)){
    			Main.gameData.location_memory_min_1_x = GameData.enemy.x;
    			Main.gameData.location_memory_min_1_y = GameData.enemy.y;
    			Main.gameData.enemy.health-=Main.gameData.hero.attack;
    			Main.gameData.enemy.pain = 1;
    			Main.gameData.hero.wrath = 1;
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
	
	private void merchantActionProcedures() {
				
		//			Main.gameData.player_dialogue_state = false;
		//			Main.gameData.merchant_dialogue_window.closeWindow();
					
					if (Main.gameData.player_dialogue_type == Merchant.GREETING) {
						         				
						Main.gameData.merchant_dialogue_window.selectIndex();
					Main.gameData.player_dialogue_type = Merchant.INQUIRY;
						Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
						Main.gameData.merchant_dialogue_window.openWindow();
					} else if (Main.gameData.player_dialogue_type == Merchant.INQUIRY) {
						//Main.gameData.merchant_dialogue_window.closeWindow();
						//Main.gameData.player_dialogue_state = false;
						int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
						if (temp_menu_selection == 2) {
						   Main.gameData.player_dialogue_type = Merchant.FAREWELL;
						   Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.FAREWELL];
						   Main.gameData.merchant_dialogue_window.openWindow();
						}
						else if (temp_menu_selection == 1) {
							Main.gameData.player_dialogue_type = Merchant.SELL_TO_PLAYER;
		 				   	Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.SELL_TO_PLAYER];
		 				   	Main.gameData.merchant_dialogue_window.openWindow();
						}
						else if (temp_menu_selection == 0) {
							
						}
						else {
						  Main.gameData.player_dialogue_type = Merchant.GREETING;
						  Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.GREETING];
					  //Main.gameData.player_dialogue_state = false;
						  Main.gameData.game_state = GameData.GAME_RUNNING;
						}
					}else if (Main.gameData.player_dialogue_type == Merchant.FAREWELL) {
						  Main.gameData.merchant_dialogue_window.selectIndex();
						  Main.gameData.player_dialogue_type = Merchant.GREETING;
						  Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.GREETING];
						  Main.gameData.game_state = GameData.GAME_RUNNING;
					}
					else if (Main.gameData.player_dialogue_type == Merchant.SELL_TO_PLAYER) {
						int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
						Main.gameData.player_dialogue_type = Merchant.SELL_CONFIRMATION;
						Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.SELL_CONFIRMATION];
						Main.gameData.merchant_dialogue_window.resetMenuHeader();
						Main.gameData.merchant_dialogue_window.concatinateStringToMenuHeader("" + Merchant.menu_options_1[temp_menu_selection] + "?");
						Main.gameData.merchant_dialogue_window.openWindow();
						
						/*else {
							Main.gameData.merchant_dialogue_window.selectIndex();
		  				  	Main.gameData.player_dialogue_type = Merchant.GREETING;
		  				  	Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.GREETING];
		 				  	Main.gameData.game_state = GameData.GAME_RUNNING;
					}*/
				}
				else if (Main.gameData.player_dialogue_type == Merchant.SELL_CONFIRMATION) {
					int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
						if (temp_menu_selection == 0) {
							if (false) { // if not enough gold
								
							}
							else if (false) { // if not enough inventory space
								
							}
							else {
								Main.gameData.player_dialogue_type = Merchant.THANK_YOU_FOR_PURCHASE;
			 				   	Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.THANK_YOU_FOR_PURCHASE];
			 				   	Main.gameData.merchant_dialogue_window.openWindow();
							}
						}
						else if (temp_menu_selection == 1) {
							Main.gameData.merchant_dialogue_window.selectIndex();
							Main.gameData.player_dialogue_type = Merchant.INQUIRY;
							Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
							Main.gameData.merchant_dialogue_window.openWindow();
						}
					}
					else if (Main.gameData.player_dialogue_type == Merchant.THANK_YOU_FOR_PURCHASE) {
						Main.gameData.merchant_dialogue_window.selectIndex();
						Main.gameData.player_dialogue_type = Merchant.INQUIRY;
						Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
						Main.gameData.merchant_dialogue_window.openWindow();
					}
					//Main.gameData.game_state = GameData.GAME_RUNNING;
				
			}

}