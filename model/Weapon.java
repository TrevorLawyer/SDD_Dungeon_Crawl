package model;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.Graphics2D;

public class Weapon extends Item {

	int power;  //Increases Damage dealt by this amount
	
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
	
    public void render(Graphics2D g, int x, int y) {
    	g.drawImage(super.currentPic, x, y, 30, 30, null);
    }
}