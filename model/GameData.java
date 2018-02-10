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
    
    public static Enemy enemy;
    public static Hero hero;
    public final List<GameFigure> friendFigures, enemyFigures;
    public static Weapon weapon;
    public static Armor armor;
    public static Consumable potion;
    
    public GameData()
    {
        hero = new Hero(1, 1);
        enemy = new Enemy(5, 5);
        weapon = new Weapon("sword","a sword",5,9,1);
        armor = new Armor("steel Armor", "steel armor",5,8,0);
        potion = new Consumable("health potion", "Health Potion",5,9,0);
        
        friendFigures = Collections.synchronizedList(
        new ArrayList<GameFigure>() );
        enemyFigures = Collections.synchronizedList(new ArrayList<GameFigure>());
        
        friendFigures.add(hero);
        friendFigures.add(weapon);
        friendFigures.add(armor);
        friendFigures.add(potion);
        Main.frame.PlaceCharacter(hero);
        Main.frame.PlaceCharacter(weapon);
        
        enemyFigures.add(enemy);
        Main.frame.PlaceCharacter(enemy);
        
    }
    
    public void update(){
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
    	synchronized(enemyFigures){
    		for(GameFigure g: enemyFigures){
    			g.update();
    			Main.frame.PlaceCharacter(g);
    		}
    	}
    }
}
