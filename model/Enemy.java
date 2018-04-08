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
	
	Random rand = new Random();
	
	
	public EnemyType[] enemies = {new Enemy_Bat(), new Enemy_Skeleton(), new Enemy_Orc(), new Enemy_DarkElf()};
	int  n = rand.nextInt(enemies.length) + 0;
	public EnemyType chosenEnemy;
	
	public int range_of_sight;
    public int xp;
    public int health;
    public int attack;
    public int pain;
    
    public Enemy(int x, int y) {
        super(x, y);
        
        chosenEnemy = enemies[n];
        super.range = chosenEnemy.range_of_sight;
        super.xp = chosenEnemy.xp;
        super.health = chosenEnemy.health;
        super.attack = chosenEnemy.attack;
        super.pain = chosenEnemy.pain;
        try {
            if (pain==1) {
            	super.currentPic[0] =ImageIO.read(getClass().getResource("hit.png"));
            }
            else {
            	
            	super.currentPic[0] = ImageIO.read(getClass().getResource(chosenEnemy.picString));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + chosenEnemy.picString);
            System.exit(-1);
        }
        

    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(currentPic[0], super.x*47, super.y*50, 80, 80, null);
    }
    @Override
    public void update() {
    	//Empty for now
    }

}