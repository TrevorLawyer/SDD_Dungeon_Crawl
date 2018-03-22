/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Audio;
import controller.Main;
import model.GameData;
import model.GameFigure;
import model.GameMapTile;
import model.HealthBar;
import model.Hero;
import model.MapManager;
import model.Merchant;

/**
 *
 * @author Matthew
 */
public class Map extends JFrame {
	private static HealthBar health;
	int healthleft;
	JPanel panel = new JPanel();
    public final static int row = 10, col = 10;
    GameMapTile[][] grid= new GameMapTile[row][col];
    private Graphics2D g2;
    private Image dbImage = null;  //Taken from OOP project to get the screen to update
    public static int width, height;
    MapManager gameMap;
    
    public Map() {
		//theme song for application
        
	    // contentPane's default layout manager --> Border Layout
	    getContentPane().add(panel);
	    health = new HealthBar();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 500, 500);
	    panel.setLayout(new GridLayout(row, col));
	    
	    gameMap = Main.gameMap;
	    for (int i = 0; i < row; i++){
	        for (int j = 0; j < col; j++){
	            grid[i][j] = gameMap.getCurrent().getTile(j, i);
	            grid[i][j].setBorder(new LineBorder(Color.BLACK));
	            grid[i][j].setOpaque(true);
	            panel.add(grid[i][j]);
	        }
	    }
	    //generate new enemy
	    
    }
    
    public void PlaceCharacter(GameFigure character)
    {
        BufferedImage image = character.currentPic;
        
        Image img= image.getScaledInstance(grid[character.y][character.x].getWidth(), grid[character.y][character.x].getHeight(),
        Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(img);
        
        grid[character.y][character.x].setIcon(imageIcon);
    }
     public void PlaceEnemy(int x, int y)
    {  
//       grid[row/2][col/2]
    }

	public void printScreen() {
		Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
		
	}

	public void gameRender() {
		
		width = getSize().width;
        height = getSize().height;
        if (dbImage == null) {
            // Creates an off-screen drawable image to be used for double buffering
            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }

        g2.clearRect(0, 0, width, height);
        gameMap.render(g2);
        synchronized (Main.gameData.friendFigures){

            
        	for (GameFigure f : Main.gameData.friendFigures) {
                f.render(g2);
                PlaceCharacter(f);
                if(f instanceof Hero){
                	g2.setColor(Color.white);
                	g2.drawString("Level "+Main.gameData.hero.level, width-100, 460);
                	g2.drawString("Exp: "+Main.gameData.hero.xp+"/100", width-125, 480);
                }
            }
            
     
		}
        
        //vvvv taking this out removes top left guys vvvvv
        
    /*  synchronized (Main.gameData.friendFigures){
            
        	for (GameFigure f : Main.gameData.friendFigures) {
                f.render(g2);
                PlaceCharacter(f);
            }
            
        }
        synchronized (Main.gameData.enemyFigures){
            
        	for (GameFigure f : Main.gameData.enemyFigures) {
                f.render(g2);
                PlaceCharacter(f);
            }
            
        }
       																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																	
     
	
        
            
        	for (GameFigure f : Main.gameData.friendFigures) {
                f.render(g2);
                PlaceCharacter(f);
            }
    */ 
            synchronized(health) {
            	health.render(g2);
            }
            
            
     
	
            synchronized(Main.gameData.inventory_window){
            	if(Main.gameData.game_state == GameData.GAME_MENU || Main.gameData.game_state == GameData.MENU_EQUIPMENT){
        		Main.gameData.inventory_window.render(g2);
        	}
        }
			synchronized(Merchant.merchant_dialogue){
				if (Main.gameData.game_state == GameData.MERCHANT_DIALOG) {
 					Main.gameData.merchant_dialogue_window.render(g2);
 				}

			}
        }
	}
