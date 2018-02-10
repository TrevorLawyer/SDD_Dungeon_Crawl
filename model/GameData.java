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
    
    public static Enemy enemy;
    public static Hero hero;
    public static Merchant merchant;
    public final List<GameFigure> friendFigures, enemyFigures;
    public static MerchantDialogueWindow merchant_dialogue_window;
    
    public GameData()
    {
    		
        hero = new Hero(1, 1);
        enemy = new Enemy(5, 5);
        merchant = new Merchant(6,6);
        friendFigures = Collections.synchronizedList(
        new ArrayList<GameFigure>() );
        enemyFigures = Collections.synchronizedList(new ArrayList<GameFigure>());
     
        
        friendFigures.add(hero);
        Main.frame.PlaceCharacter(hero);
        friendFigures.add(merchant);
        Main.frame.PlaceCharacter(merchant);
        enemyFigures.add(enemy);
        Main.frame.PlaceCharacter(enemy);
        
        player_dialogue_state = false;
        player_dialogue_type = Merchant.GREETING;
        merchant_dialogue_window = Merchant.merchant_dialogue[0];
        
    }
    
    public void update(){
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
    }
}
