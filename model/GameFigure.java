package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class GameFigure {

    // public for a faster access during animation
    public int x;
    public int y;
    public BufferedImage currentPic;
    public int state;
    public int range = 0;
    public int xp;
    public int health;
    public int attack;
    public int pain;

	public int wrath;

    public GameFigure(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public GameFigure (Coordinate xy) {
    	this.x = xy.getX();
    	this.y= xy.getY();
    }

    // how to render on the canvas
    /* (non-Javadoc)
	 * @see model.GameDraw#render(java.awt.Graphics2D)
	 */
	public abstract void render(Graphics2D g);

    // changes per frame
    /* (non-Javadoc)
	 * @see model.GameDraw#update()
	 */
	public abstract void update();

}