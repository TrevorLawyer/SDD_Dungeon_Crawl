package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Main;

public class GameMapTile extends JLabel{

	private JFrame screen = Main.frame; 
	//Dimension size = screen.getContentPane().getSize();
	public boolean tileEmpty;
	public Coordinate loc;
	private int xTiles = 10;
	private int yTiles = 10;
	public boolean obstacle = false;
	private Image boulder = Main.textures.boulder;
	private Image grass = Main.textures.grass;
	private Image treeBoundary = Main.textures.treeBoundary;
    private int xInc= 500/xTiles- 1;
    private int yInc = 500/yTiles - 1;
    public boolean ladderTile = false;
  
    
	
	public GameMapTile(Coordinate xy) {
		loc = xy;
		tileEmpty = false;
		
	
		 
	}
	
	
	public GameMapTile(int x, int y) {
		loc = new Coordinate(x,y);
		
	}
	public Coordinate getCoord() {
		return loc;
	}
	public int getX() {
		return loc.x;
	}
	public int getY() {
		return loc.y;
	}

	public void render(Graphics2D g) throws IOException {
			
		
		if(obstacle) {			
						
			g.drawImage(boulder, this.loc.x* xInc +8, this.loc.y* yInc +28, xInc, yInc, null);
		}
		else if (ladderTile) {
			Image ladderDown = Main.textures.ladderDown;
			g.drawImage(ladderDown, this.loc.x* xInc +8, this.loc.y* yInc +28, xInc, yInc, null);
			
		}
		else if(tileEmpty) {
			
			g.drawImage(grass, this.loc.x* xInc +8, this.loc.y* yInc +28, xInc, yInc, null);
		}
		else {
			
			g.drawImage(treeBoundary, this.loc.x* xInc +8, this.loc.y* yInc +28, xInc, yInc, null);
		}
		//g.fillRect(this.getX() * xInc +8, this.getY() * yInc +28, xInc, yInc);
	}
	public boolean isPassable() {
		if (!tileEmpty || obstacle) {
			return false;
		}
		else return true;
	}

}