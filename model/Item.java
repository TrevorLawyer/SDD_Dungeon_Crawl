package model;

import java.awt.Graphics2D;

public abstract class Item extends GameFigure {
	
	public String name, description;
	public int worth_in_gold;
	
	public Item(String name, String description, int x, int y) {
		super(x, y);
		this.name = name;
		this.description = description;
		this.worth_in_gold = 0;
	}
	
	public void render(Graphics2D g) {		
		g.drawImage(currentPic[0], super.x*47, super.y*50, 80, 80, null);
 	}

	
	public void update() {		
	}
}
