package model;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import controller.Main;

public class Consumable extends Item {
	
	int healthRestored; //What the item does

	//Deprecated, use other constructor if possible. Kept for backwards compatibility.
	public Consumable(String n, String desc, int h, int x ,int y) {
		super(n, desc, x, y);
       // super.currentPic = null;
        super.worth_in_gold = 100;
        healthRestored = h;
        try 
        {          super.currentPic[0] = ImageIO.read(getClass().getResource("consumable.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open consumable.png");
            System.exit(-1);
        }
	}
	
	//Overloaded constructor allows us to specify where the sprite is located.
	public Consumable(String n, String desc, int h, int x ,int y, String spriteLocation) {
		super(n, desc, x, y);
        super.currentPic = null;
        healthRestored = h;
        try 
        {
          super.currentPic[0] = ImageIO.read(getClass().getResource(spriteLocation));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open"+spriteLocation);
            System.exit(-1);
        }catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + spriteLocation);
            System.exit(-1);
        }
	}
	
	public void use(){
		Main.gameData.hero.setHealth(healthRestored);
	}
}
