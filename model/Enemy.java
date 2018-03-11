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
import java.util.Random;


/**
 *
 * @author Matthew
 */
public class Enemy extends GameFigure{
    private Image bossImage;
    public int xp;
    public int health;
    public int attack;
    public int pain;

	Random rand = new Random();
	int  n = rand.nextInt(2) + 0;
	
	public EnemyType[] enemies = {new Enemy_Bat(), new Enemy_Skeleton()};
	public EnemyType chosenEnemy;
	

    
    public Enemy(int x, int y) {
        super(x, y);
        
        chosenEnemy = enemies[n];
        xp = chosenEnemy.xp;
        health = chosenEnemy.health;
        attack = chosenEnemy.attack;
        pain = chosenEnemy.pain;
        
        try {
            if (pain==1) {
            	super.currentPic =ImageIO.read(getClass().getResource("hit.png"));
            }
            else {
            	
            	super.currentPic = ImageIO.read(getClass().getResource(chosenEnemy.picString));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + chosenEnemy.picString);
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
