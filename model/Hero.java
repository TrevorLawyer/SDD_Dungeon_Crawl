/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Matthew
 */
public class Hero extends GameFigure{
	
	ArrayList<Item> inventory;
	Weapon equippedWeapon;
	Armor equippedArmor;

    public Hero(int x, int y) {
        super(x, y);
        
        equippedWeapon = new Weapon("Fist", "Your Fists", 1,-1,-1);
        equippedArmor = new Armor("Clothes", "Basic Clothes", 1,-1,-1);

        inventory = new ArrayList<Item>();
        inventory.add(new Weapon("Basic Sword","It's a sword",1,0,0));
        try 
        {
            super.currentPic = ImageIO.read(getClass().getResource("pixel_hero.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open pixel_hero.jpg");
            System.exit(-1);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
    	g.drawImage(currentPic, x, y, 30, 30, null);
    }

    @Override
    public void update() {

    }

    public void AddItemToInventory(Item i){
    	inventory.add(i);
    }
    
    public String[] getInventoryNames(){
    	if(inventory.size()>0){
	    	String[] inventorynames = new String[inventory.size()];
	    	for(int i=0;i< inventory.size();i++){
	    		inventorynames[i] = inventory.get(i).name;
	    	}
	    	return inventorynames;
    	}
    	
    	return new String[]{""};
    }
    
    public void useItem(Item i){
    	if(i instanceof Weapon){
    		inventory.add(equippedWeapon);
    		equippedWeapon = (Weapon) i;
    	}
    	else if(i instanceof Armor){
    		inventory.add(equippedArmor);
    		equippedArmor = (Armor) i;
    	}
    	else if(i instanceof Consumable){
    		((Consumable) i).use();
    	}
    }
    
}
