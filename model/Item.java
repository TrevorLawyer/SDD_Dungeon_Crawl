package model;

import java.awt.Graphics2D;

public abstract class Item extends GameFigure {
	
	String name, description;
	
	public Item(String name, String description, int x, int y) {
		super(x, y);
		this.name = name;
		this.description = description;
	}
	
	
	public void render(Graphics2D g) {		
	}

	
	public void update() {		
	}
}
