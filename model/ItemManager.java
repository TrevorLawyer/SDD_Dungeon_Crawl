package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.*;

//Usage: Create the ItemManager object for the object that require an item to be spawned.
//Run Item item = ItemManager.ItemOutput() in your code using whatever ItemOut() you like.
//The Item manager will generate an item for you based on the parameters you gave it.
//Give the Manager -1 values for X and Y if you do not intend to render the item onscreen.

//level takes either a 1, 2, or 3 depending on how strong you want the item to be.
//Type takes a 1,2, or 3. 1 = weapon, 2 = armor, 3 = consumable.


public class ItemManager {
	
	private Random rand = new Random();	
	
	ItemManager(){
		
	}
	
	//Overloaded methods:
	//Allows an item to be generated on screen using random levels and types
	public Item ItemOutput(int x, int y) {
		return ItemOutput(x,y,Math.abs(rand.nextInt(3)+1),Math.abs(rand.nextInt(3)+1));
	}
	
	//Allows an item to be generated on screen of a specific type but random level
	public Item ItemOutput(int x, int y, int type) {
		return ItemOutput(x,y,rand.nextInt(3)+1,type);
	}
	
	//Allows an item to be generated on screen of a specific type and level.
	public Item ItemOutput(int x, int y, int level, int type) {	
		try {
			//The main engine of our file input.
			BufferedReader br = new BufferedReader (new FileReader ("gamedata/itemlist.txt"));
			String fileRead = br.readLine();
			
			//Handles the three levels of armor. It isn't pretty.
			//All other alternatives just mask the ugly-ness. This way you can see it up close!
			List<Item> weapon1 = new ArrayList<>();
			List<Item> weapon2 = new ArrayList<>();
			List<Item> weapon3 = new ArrayList<>();
			List<Item> armor1 = new ArrayList<>();
			List<Item> armor2 = new ArrayList<>();
			List<Item> armor3 = new ArrayList<>();
			List<Item> consume1 = new ArrayList<>();
			List<Item> consume2 = new ArrayList<>();
			List<Item> consume3 = new ArrayList<>();
			
			while(fileRead != null)
			{
				Weapon tempWep = null;
				Armor tempArm = null;
				Consumable tempConsume = null;
				String[] tokenize = fileRead.split(","); //Tokenizing the .txt file so that each item can be read in.
				String tempName = tokenize[0];
				String tempDesc = tokenize[1];
				String tempSprite = tokenize[2];
				int tempvalue = Integer.parseInt(tokenize[3]);
				int tempLevel = Integer.parseInt(tokenize[4]);
				int tempType = Integer.parseInt(tokenize[5]);
				
				//Creates placeholder objects for each type of item.
				switch(tempType) {
				case 1:
					//tempWep = new  Weapon(tempName,tempDesc,tempvalue,-1,-1,tempSprite);
					break;
				case 2: 
					tempArm = new Armor(tempName,tempDesc,tempvalue,-1,-1,tempSprite);
					break;
				case 3:
					tempConsume = new Consumable(tempName,tempDesc,tempvalue,-1,-1,tempSprite);
					break;
				}
				
				//Sorts and puts the items into their respective level.
				switch(tempLevel) {
				case 1:
					if(tempWep != null) {weapon1.add(tempWep);}
					else if(tempArm != null) {armor1.add(tempArm);}
					else if(tempConsume != null) {consume1.add(tempConsume);}
					break;
				case 2:
					if(tempWep != null) {weapon2.add(tempWep);}
					else if(tempArm != null) {armor2.add(tempArm);}
					else if(tempConsume != null) {consume2.add(tempConsume);}
					break;
				case 3:
					if(tempWep != null) {weapon3.add(tempWep);}
					else if(tempArm != null) {armor3.add(tempArm);}
					else if(tempConsume != null) {consume3.add(tempConsume);}
					break;					
				}
				//Continue the loop to the next line.
				fileRead = br.readLine();
			}
			br.close();
			Item sendback = null;
			
			switch(level) {
			case 1:
				if(type == 1) {sendback = weapon1.get(Math.abs(rand.nextInt(weapon1.size())));}
				else if(type == 2) {sendback = armor1.get(Math.abs(rand.nextInt(armor1.size())));}
				else if(type == 3) {sendback = consume1.get(Math.abs(rand.nextInt(consume1.size())));}
				break;
			case 2:
				if(type == 1) {sendback = weapon2.get(Math.abs(rand.nextInt(weapon2.size())));}
				else if(type == 2) {sendback = armor2.get(Math.abs(rand.nextInt(armor2.size())));}
				else if(type == 3) {sendback = consume2.get(Math.abs(rand.nextInt(consume2.size())));}
				break;
			case 3:
				if(type == 1) {sendback = weapon3.get(Math.abs(rand.nextInt(weapon3.size())));}
				else if(type == 2) {sendback = armor3.get(Math.abs(rand.nextInt(armor3.size())));}
				else if(type == 3) {sendback = consume3.get(Math.abs(rand.nextInt(consume3.size())));}
				break;
			}
			
			sendback.x = x;
			sendback.y = y;
			return sendback;
			
			
	        
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File not found"); //For bad file names
		}
		catch(IOException e){
			e.printStackTrace(); //For most other errors
		}
		
		return null;
		
		
	}
	
}
