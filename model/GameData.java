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
	public static final int GAME_RUNNING=0, GAME_MENU=1, MERCHANT_DIALOG=2, GAME_IDLE=3;
	public static final int MENU_EQUIPMENT=3;
	public static ItemManager manager;
<<<<<<< HEAD
	public static Item item;
=======
//	public static Item item;
	public static Item dropped;
>>>>>>> devronhanks_final2
    public static Enemy enemy;
    public static Hero hero;
    public static Merchant merchant;
    public static List<GameFigure> friendFigures;
    public static List<GameFigure> enemyFigures;
    public static TreasureChest chest;

    public static MerchantDialogueWindow merchant_dialogue_window, inventory_window;
    
    public GameData()
    {
    	
    	manager = new ItemManager();
    	game_state = GAME_RUNNING;
        hero = new Hero(1, 1);
 //       merchant = new Merchant(6,6);
        friendFigures = Collections.synchronizedList(
        new ArrayList<GameFigure>() );
        enemyFigures = Collections.synchronizedList(new ArrayList<GameFigure>());
     
        chest = new TreasureChest((int)(Math.random()*9+1),(int)(Math.random()*9+1));
        friendFigures.add(hero);

         
        Main.frame.PlaceCharacter(hero);
        
<<<<<<< HEAD
        friendFigures.add(merchant);
        Main.frame.PlaceCharacter(merchant);
        
//        player_dialogue_state = false;
=======
        if (Merchant.randomWithRange(0,2) == 0) {
        	merchant.present = true;
        	friendFigures.add(merchant);
        	Main.frame.PlaceCharacter(merchant);
        }
>>>>>>> devronhanks_final2
        player_dialogue_type = Merchant.GREETING;
        merchant_dialogue_window = Merchant.merchant_dialogue[0];

        spawn();//first enemy created
        
        inventory_window = new MerchantDialogueWindow("Inventory");
        
    }
    
<<<<<<< HEAD
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
					    hero.xp=+enemy.xp;
					    System.out.println(""+hero.xp);
					    item = manager.ItemOutput(enemy.x, enemy.y);
					    friendFigures.add(item);
						enemyFigures.remove(enemy);
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
=======
>>>>>>> devronhanks_final2

 // add enemy and reset merchant
    public static void spawn(){
        	
    	int enemyX = 0;
    	int enemyY = 0;
    	while(Main.gameMap.isPassable(enemyX, enemyY) == false)
    	{
    		enemyX = (int) Math.ceil(Math.random()*8);
    		enemyY = (int) Math.ceil(Math.random()*8);
    	}
    	enemy = new Enemy(enemyX, enemyY);
    	enemyFigures.add(enemy);
    	friendFigures.remove(merchant);
    	merchant = new Merchant((int) Math.ceil(Math.random()*8),(int) Math.ceil(Math.random()*8));
    	friendFigures.add(merchant);
    	Main.frame.PlaceCharacter(enemy);
    	//friendFigures.remove(dropped);
    	chest = new TreasureChest((int)(Math.random()*9+1),(int)(Math.random()*9+1));
    	System.out.println("Chest x: "+chest.x+", y: "+chest.y);
     }
        // remove all enemies
        public static void swab() {
        	enemyFigures.removeAll(enemyFigures);
        	friendFigures.remove(dropped);
        	friendFigures.remove(merchant);
        
        }
}