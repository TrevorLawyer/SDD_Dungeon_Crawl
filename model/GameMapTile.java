package model;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class GameMapTile extends JLabel{

	public boolean tileEmpty;
	public Coordinate loc;
	private int xTiles = 10;
	private int yTiles = 10;

    private int xInc= 500/xTiles;
    private int yInc = 500/yTiles;
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
		if(tileEmpty) {
			g.setColor(Color.GREEN);
		}
		else g.setColor(Color.BLACK);
		g.fillRect(this.getX() * xInc, this.getY() * yInc, xInc, yInc);
	}

}