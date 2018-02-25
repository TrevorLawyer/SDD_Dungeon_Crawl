package model;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.Graphics2D;

public class Weapon extends Item {

	static int power;  //Increases Damage dealt by this amount
	
	
	//Deprecated, Use other constructor if possible. Kept for backwards compatibility.
	public Weapon(String n, String desc, int p, int x, int y) {
		super(n, desc, x, y);
		power=p;
		
        super.currentPic = null;
        try 
        {
          super.currentPic = ImageIO.read(getClass().getResource("sword.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open sword.png");
            System.exit(-1);
        }
    }
<<<<<<< HEAD
	
	//Overloaded constructor allows up to specify a sprite location
	public Weapon(String n, String desc, int p, int x, int y, String spriteLocation) {
		super(n, desc, x, y);
		power=p;
		
        super.currentPic = null;
        try 
        {
          super.currentPic = ImageIO.read(getClass().getResource(spriteLocation));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + spriteLocation);
            System.exit(-1);
        }catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + spriteLocation);
            System.exit(-1);
        }
    }
	
	
=======
	// get attack damage power for hero
	public static int getPower(){
		return power;
	}
>>>>>>> Clifton_sprint2
    public void render(Graphics2D g, int x, int y) {
    	g.drawImage(super.currentPic, x, y, 30, 30, null);
    }
}
