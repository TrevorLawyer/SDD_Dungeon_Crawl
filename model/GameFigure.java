package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class GameFigure {

    // public for a faster access during animation
    public int x;
    public int y;
    public BufferedImage currentPic;
    public int state;


    public GameFigure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // how to render on the canvas
    public abstract void render(Graphics2D g);

    // changes per frame
    public abstract void update();

}
