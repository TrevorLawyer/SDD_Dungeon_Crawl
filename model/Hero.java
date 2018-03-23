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
	
	public int gold;
	
	public ArrayList<Item> inventory, equipment;
	Weapon equippedWeapon;
	Armor equippedArmor;
	public int powerLevel;
	public int xp;
	public int maxHealth;
	public int health;
	public int attack;
	public int wrath = 0;

    public Hero(int x, int y) {
        super(x, y);
        
        equippedWeapon = new Weapon("Fist", "Your Fists", 1,-1,-1);
        equippedArmor = new Armor("Clothes", "Basic Clothes", 1,-1,-1);
        powerLevel=(int) xp/4;
        attack = Weapon.getPower()+powerLevel;
        maxHealth = powerLevel + 35;
        health = maxHealth;
        equipment = new ArrayList<Item>();
        
        inventory = new ArrayList<Item>();
        inventory.add(new Weapon("Basic Sword","It's a sword",1,0,0));
        try 
        {
            super.currentPic = ImageIO.read(getClass().getResource("pixel_hero.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open pixel_hero.jpg");
            System.exit(-1);
        }
        equipment.add(equippedWeapon);
        equipment.add(equippedArmor);
        
        gold = 10000;
        
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
    
    public void removeItemFromInventory(int i){
    	inventory.remove(i);
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
    
    public String[] getEquipmentNames(){
    	if(equipment.size()>0){
    	String[] equipmentNames = new String[equipment.size()];
    	for(int i=0; i<equipment.size();i++){
    		equipmentNames[i] = equipment.get(i).name;
    		}
    		return equipmentNames;
    	}
    	return new String[]{""};
    	}
    
    //Send this method how much you want to add or remove health from the hero
    //Negative numbers remove health, positive numbers add health   
    public void setHealth(int h) {
    	if(!(h+health < 0 || h+health > maxHealth)) {
    		health+=h;
    	}
    	else if(h+health < 0) {
    		health = 0;
    	}
    	else if(h+health > maxHealth) {
    		health = maxHealth;
    	}
    	
    }
    
    public int getHealth() {
    	return health;
    }
    
    public int getMaxHealth() {
    	return maxHealth;
    }
    
    public void useItem(Item i){
    	System.out.println("Using an item "+i.name);
    	if(i instanceof Weapon){
    		inventory.add(equippedWeapon);
    		equipment.remove(equippedWeapon);
    		equippedWeapon = (Weapon) i;
    		inventory.remove(i);
    		equipment.add(i);
    	}
    	else if(i instanceof Armor){
    		inventory.add(equippedArmor);
    		equipment.remove(equippedArmor);
     		equippedArmor = (Armor) i;
    		inventory.remove(i);
    		equipment.add(i);
    	}
    	else if(i instanceof Consumable){
    		((Consumable) i).use();
    		inventory.remove(i);
    	}
    	
    }
    public void useItem(int i){
    	useItem(inventory.get(i));
    }
    
}
