/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Matthew
 */
public class GameData {
	
	public static int location_memory_min_1_x;
	public static int location_memory_min_1_y;
	
	//public boolean player_dialogue_state;
	public int player_dialogue_type;
	
	public int game_state;
	public static final int GAME_RUNNING=0, GAME_MENU=1, MERCHANT_DIALOG=2;
	public static final int MENU_EQUIPMENT=3;
	public static ItemManager manager;
    public static Enemy enemy;
    public static Hero hero;
    public static Merchant merchant;
       public final List<GameFigure> friendFigures;
    public static List<GameFigure> enemyFigures;

    public static MerchantDialogueWindow merchant_dialogue_window, inventory_window;
    
    public GameData()
    {
    	
    	manager = new ItemManager();
    	game_state = GAME_RUNNING;
        hero = new Hero(1, 1);
        merchant = new Merchant(6,6);
        friendFigures = Collections.synchronizedList(
        new ArrayList<GameFigure>() );
        enemyFigures = Collections.synchronizedList(new ArrayList<GameFigure>());
     
        
        friendFigures.add(hero);

        
        hero.AddItemToInventory(new Weapon("Sword 1","Sword",5,1,1));
        hero.AddItemToInventory(new Weapon("Sword 2","Sword",5,1,1));
        hero.AddItemToInventory(new Weapon("Sword 3","Sword",5,1,1));
        Main.frame.PlaceCharacter(hero);
        
        friendFigures.add(merchant);
        Main.frame.PlaceCharacter(merchant);
//        enemyFigures.add(enemy);
//        Main.frame.PlaceCharacter(enemy);
        
//        player_dialogue_state = false;
        player_dialogue_type = Merchant.GREETING;
        merchant_dialogue_window = Merchant.merchant_dialogue[0];

        	spawn();//first enemy created
        
        inventory_window = new MerchantDialogueWindow("Inventory");
        
    }
    
    public void update(){
    	if(Main.animator.userTurn){
    		synchronized (friendFigures) {
	            ArrayList<GameFigure> remove = new ArrayList<>();
	            GameFigure f;
	            for (int i = 0; i < friendFigures.size(); i++) {
	                f = friendFigures.get(i);
	//                if (f.state == GameFigureState.STATE_DONE) {
	 //                   remove.add(f);
	 //               }
	            }
	            friendFigures.removeAll(remove);
	
	            for (GameFigure g : friendFigures) {
	                g.update();
	                Main.frame.PlaceCharacter(g);
	            }
	        }
	    	synchronized (friendFigures) {
	    		if (Main.gameData.game_state == GameData.GAME_RUNNING) {
	            ArrayList<GameFigure> remove = new ArrayList<>();
	            GameFigure f;
	            for (int i = 0; i < friendFigures.size(); i++) {
	                f = friendFigures.get(i);
	//                if (f.state == GameFigureState.STATE_DONE) {
	 //                   remove.add(f);
	 //               }
	            	}
	            	friendFigures.removeAll(remove);
	
	            	for (GameFigure g : friendFigures) {
	                	g.update();
	                	Main.frame.PlaceCharacter(g);
	            	}
	    		}
	    	}
			synchronized(enemyFigures){
				if (Main.gameData.game_state == GameData.GAME_RUNNING) {
					//take life away
					if (enemy.health<1) {
						enemyFigures.remove(enemy);
					    hero.xp=+enemy.xp;
					    System.out.println(""+hero.xp);
					     			}
					for(GameFigure g: enemyFigures){
						g.update();
						Main.frame.PlaceCharacter(g);
					}
				}
			}
			synchronized(Merchant.merchant_dialogue){
				if (Main.gameData.game_state == GameData.MERCHANT_DIALOG) {
					merchant_dialogue_window.update();
				}
			}
			synchronized(Main.gameData.inventory_window){
				if(Main.gameData.game_state == GAME_MENU){
					inventory_window.update();
				}
			}

	
			synchronized(enemyFigures){
				for(GameFigure g: enemyFigures){
	    			g.update();
	    			Main.frame.PlaceCharacter(g);
	    		}
	    	}
	    }
    }
 // add enemy
    public static void spawn(){
        	
    	enemy = new Enemy((int)(Math.random()*9), (int)(Math.random()*9));
    	enemyFigures.add(enemy);
    	Main.frame.PlaceCharacter(enemy);
     }
        // remove all enemies
        public static void swab() {
        	enemyFigures.removeAll(enemyFigures);
        }
}