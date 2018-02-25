package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Map;
import model.GameData;

public class KeyController implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
        	case KeyEvent.VK_UP:
        		if(GameData.hero.y>0 && Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			GameData.hero.y--;
        		}
        		else if (Main.gameData.game_state == GameData.MERCHANT_DIALOG) {
        			Main.gameData.merchant_dialogue_window.moveIndexUp();
        		}
        		else if(Main.gameData.game_state == GameData.GAME_MENU){
        			Main.gameData.inventory_window.moveIndexUp();
        		}
        		break;
        	case KeyEvent.VK_DOWN:
        		if(GameData.hero.y<Map.row-1 && Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			GameData.hero.y++;
        		}
        		else if (Main.gameData.player_dialogue_state) {
        			Main.gameData.merchant_dialogue_window.moveIndexDown();
        		} 
        		else if(Main.gameData.game_state == GameData.GAME_MENU){
        			Main.gameData.inventory_window.moveIndexDown();
        		}
        		break;
        	case KeyEvent.VK_RIGHT:
        		if(GameData.hero.x<Map.col-1 && Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.location_memory_min_1_x = GameData.hero.x;
        			Main.gameData.location_memory_min_1_y = GameData.hero.y;
        			GameData.hero.x++;
        		}
        		break;
        	case KeyEvent.VK_LEFT:
        		if(GameData.hero.x>0 && Main.gameData.game_state == GameData.GAME_RUNNING){
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
        			Main.gameData.player_dialogue_state = false;
        			Main.gameData.merchant_dialogue_window.closeWindow();
        			Main.gameData.game_state = GameData.GAME_RUNNING;
        		}
        		break;
        		
        	case KeyEvent.VK_I:
        		//opens inventory window, switches game state to game_menu
        		if(Main.gameData.game_state == GameData.GAME_RUNNING){
        			Main.gameData.game_state = GameData.GAME_MENU;
        			Main.gameData.inventory_window.openWindow();
        		//	Main.gameData.inventory_window.update(Main.gameData.hero.getInventoryNames());
        		}
        		else if(Main.gameData.game_state == GameData.GAME_MENU){
        			Main.gameData.game_state = GameData.GAME_RUNNING;
        			//close inventory window
        			Main.gameData.inventory_window.closeWindow();
        		}
        	break;		
        	default:
        		break;
        }
		if(GameData.hero.x == Main.gameMap.getExit().x && GameData.hero.y == Main.gameMap.getExit().y) {
			Main.gameMap.goNext();
			GameData.hero.x = Main.gameMap.getEntrance().x;
			GameData.hero.y = Main.gameMap.getEntrance().y;
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