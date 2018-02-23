package model;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Armor extends Item {
	
	int defense;	//Defense reduces damage taken by this amount

	//Deprecated. Use other constructor when possible. Kept for backwards compatibility.
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
	
	//Overloaded constructor so we can tell the item where the sprite is location.
	
	public Armor(String n, String desc, int def, int x, int y, String spriteLocation) {
		super(n, desc, x, y);
		defense = def;
		
        super.currentPic = null;
        try 
        {
          super.currentPic = ImageIO.read(getClass().getResource(spriteLocation));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open"+spriteLocation);
            System.exit(-1);
        }catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + spriteLocation);
            System.exit(-1);
        }
	}

}
