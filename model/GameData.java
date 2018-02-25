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
	
	public boolean player_dialogue_state;
	public int player_dialogue_type;
	
	public int game_state;
	public static final int GAME_RUNNING=0, GAME_MENU=1, MERCHANT_DIALOG=2;
    
    public static Enemy enemy;
    public static Hero hero;
    public static Merchant merchant;
    public final List<GameFigure> friendFigures, enemyFigures;
    public static Weapon weapon;
    public static Armor armor;
    public static Consumable potion;
    public static MerchantDialogueWindow merchant_dialogue_window, inventory_window;
    
    public GameData()
    {
    	game_state = GAME_RUNNING;
        hero = new Hero(1, 1);
        enemy = new Enemy(5, 5);
        weapon = new Weapon("sword","a sword",5,9,1);
        armor = new Armor("steel Armor", "steel armor",5,8,0);
        potion = new Consumable("health potion", "Health Potion",5,9,0);

        merchant = new Merchant(6,6);
        friendFigures = Collections.synchronizedList(
        new ArrayList<GameFigure>() );
        enemyFigures = Collections.synchronizedList(new ArrayList<GameFigure>());
     
        
        friendFigures.add(hero);
        friendFigures.add(weapon);
        friendFigures.add(armor);
        friendFigures.add(potion);
        Main.frame.PlaceCharacter(hero);
        Main.frame.PlaceCharacter(weapon);
        
        friendFigures.add(merchant);
        Main.frame.PlaceCharacter(merchant);
        enemyFigures.add(enemy);
        Main.frame.PlaceCharacter(enemy);
        
        player_dialogue_state = false;
        player_dialogue_type = Merchant.GREETING;
        merchant_dialogue_window = Merchant.merchant_dialogue[0];
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
	    		if (!player_dialogue_state) {
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
				if (!player_dialogue_state) {
					for(GameFigure g: enemyFigures){
						g.update();
						Main.frame.PlaceCharacter(g);
					}
				}
			}
			synchronized(Merchant.merchant_dialogue){
				if (player_dialogue_state) {
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
}