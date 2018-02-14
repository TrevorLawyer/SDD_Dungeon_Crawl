package model;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Armor extends Item {
	
	int defense;	//Defense reduces damage taken by this amount

	public Armor(String n, String desc, int def, int x, int y) {
		super(n, desc, x, y);
		defense = def;
		
        super.currentPic = null;
        try 
        {
          super.currentPic = ImageIO.read(getClass().getResource("armor.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open armor.png");
            System.exit(-1);
        }
	}

}
