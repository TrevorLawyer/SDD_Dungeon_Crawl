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
        		if(GameData.hero.y>0){
        			GameData.hero.y--;
        		}
        		break;
        	case KeyEvent.VK_DOWN:
        		if(GameData.hero.y<Map.row){
        			GameData.hero.y++;
        		}
        		break;
        	case KeyEvent.VK_RIGHT:
        		if(GameData.hero.x<Map.col){
        			GameData.hero.x++;
        		}
        		break;
        	case KeyEvent.VK_LEFT:
        		if(GameData.hero.x>0){
        			GameData.hero.x--;
        		}
        		break;
        	case KeyEvent.VK_ESCAPE:
//        		Main.animator.gameState = Main.animator.GAME_OVER;
        	default:
        		break;
        }
    //move();
		System.out.println("Hero coords\nx: "+GameData.hero.x+"  y: "+GameData.hero.y);
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
