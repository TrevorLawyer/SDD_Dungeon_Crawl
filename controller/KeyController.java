package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Map;
import model.GameData;
import model.Merchant;

public class KeyController implements KeyListener {
	boolean hasLeftEntranceTile = false;
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
        	case KeyEvent.VK_UP:
        		if(GameData.hero.y>0 && !Main.gameData.player_dialogue_state){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			GameData.hero.y--;
        		}
        		else if (Main.gameData.player_dialogue_state) {
        			Main.gameData.merchant_dialogue_window.moveIndexUp();
        		}
        		break;
        	case KeyEvent.VK_DOWN:
        		if(GameData.hero.y<Map.row-1 && !Main.gameData.player_dialogue_state){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			GameData.hero.y++;
        		}
        		else if (Main.gameData.player_dialogue_state) {
        			Main.gameData.merchant_dialogue_window.moveIndexDown();
        		}
        		break;
        	case KeyEvent.VK_RIGHT:
        		if(GameData.hero.x<Map.col-1 && !Main.gameData.player_dialogue_state){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			GameData.hero.x++;
        		}
        		break;
        	case KeyEvent.VK_LEFT:
        		if(GameData.hero.x>0 && !Main.gameData.player_dialogue_state){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			GameData.hero.x--;
        		}
        		break;
        	case KeyEvent.VK_ESCAPE:
 //       		Main.animator.gameState = Main.animator.GAME_OVER;
        		break;
        	case KeyEvent.VK_ENTER:
        		if (Main.gameData.player_dialogue_state) {
        			if (Main.gameData.player_dialogue_type == Merchant.GREETING) {
        				
            			Main.gameData.merchant_dialogue_window.selectIndex();
            			Main.gameData.player_dialogue_type = Merchant.INQUIRY;
            			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.INQUIRY];
            			Main.gameData.merchant_dialogue_window.openWindow();
        			}
        			else if (Main.gameData.player_dialogue_type == Merchant.INQUIRY) {
        				//Main.gameData.merchant_dialogue_window.closeWindow();
        				//Main.gameData.player_dialogue_state = false;
        				int temp_menu_selection = Main.gameData.merchant_dialogue_window.selectIndex();
        				if (temp_menu_selection == 2) {
        					Main.gameData.player_dialogue_type = Merchant.FAREWELL;
                			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.FAREWELL];
                			Main.gameData.merchant_dialogue_window.openWindow();
        				}
        				else {
        					Main.gameData.player_dialogue_type = Merchant.GREETING;
                			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.GREETING];
            				Main.gameData.player_dialogue_state = false;
        				}
        			}
        			else if (Main.gameData.player_dialogue_type == Merchant.FAREWELL) {
        				Main.gameData.merchant_dialogue_window.selectIndex();
        				Main.gameData.player_dialogue_type = Merchant.GREETING;
            			Main.gameData.merchant_dialogue_window = Merchant.merchant_dialogue[Merchant.GREETING];
        				Main.gameData.player_dialogue_state = false;
        			}
        			
        		}
        		break;
        		
        	default:
        		break;
        }
    //move();
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

}
