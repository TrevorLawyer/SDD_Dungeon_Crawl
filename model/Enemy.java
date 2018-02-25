/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Matthew
 */
public class Enemy extends GameFigure{
    private Image bossImage;
    public int xp = 10;
    public int health = 6;
    public int attack = 2;
    public int pain = 0;
    public Enemy(int x, int y) {
        super(x, y);
        
        bossImage = null;
        try {
            if (pain==1) {
            	super.currentPic =ImageIO.read(getClass().getResource("hit.png"));
            }
            else {
            	super.currentPic = ImageIO.read(getClass().getResource("skeleton.png"));
            }
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
