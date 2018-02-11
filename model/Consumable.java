package model;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Consumable extends Item {
	
	int healthRestored; //What the item does

	public Consumable(String n, String desc, int h, int x ,int y) {
		super(n, desc, x, y);
        super.currentPic = null;
        healthRestored = h;
        try 
        {
          super.currentPic = ImageIO.read(getClass().getResource("consumable.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open consumable.png");
            System.exit(-1);
        }
	}
	
	public void use(){
		//empty placeholder function
	}
}
