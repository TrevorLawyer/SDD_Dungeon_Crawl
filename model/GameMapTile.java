package model;

import java.awt.Color;
import java.awt.Graphics2D;

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
	
	
    private int xInc= 500/xTiles- 1;
    private int yInc = 500/yTiles - 1;
    private Color c;
    
	
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

	public void render(Graphics2D g) {
		if(obstacle) {
			g.setColor(Color.GRAY);
		}
		else if(tileEmpty) {
			g.setColor(Color.GREEN);
		}
		else g.setColor(Color.BLACK);
		g.fillRect(this.getX() * xInc +8, this.getY() * yInc +28, xInc, yInc);
	}
	public boolean isPassable() {
		if (!tileEmpty || obstacle) {
			return false;
		}
		else return true;
	}

}