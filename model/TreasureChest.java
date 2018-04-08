package model;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TreasureChest extends GameFigure {
	
	int gold_amount;
	boolean opened;
	boolean onMap;


	public TreasureChest(int x, int y) {
		super(x, y);
		opened = false;
		gold_amount = (int)(Math.random()*250);
		onMap = false;
		try 
        {
            super.currentPic[0] = ImageIO.read(getClass().getResource("chest_unopened.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open chest_unopened.png");
            System.exit(-1);
        }

	}

	@Override
	public void render(Graphics2D g) {
		if(onMap){
			g.drawImage(currentPic[0], super.x*47, super.y*50, 80, 80, null);
		}
		//System.out.println("Drawing Chest");
	}

	@Override
	public void update() {
		
	}
	
	public boolean isOnMap() {
		return onMap;
	}

	public void setOpened(boolean b) {
		opened = b;
	}
	
	public int getGold(){
		return gold_amount;
	}
}