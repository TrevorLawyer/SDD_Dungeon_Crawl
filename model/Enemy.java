/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Matthew
 */
public class Enemy extends GameFigure{
    private Image bossImage;

    public Enemy(int x, int y) {
        super(x, y);
       
        bossImage = null;
        try 
        {
            super.currentPic = ImageIO.read(getClass().getResource("skeleton.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open skeleton.jpg");
            System.exit(-1);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(currentPic, (int)super.x, (int)super.y, 
                80, 80, null);    }

    @Override
    public void update() {
    	//Empty for now
    }

}
