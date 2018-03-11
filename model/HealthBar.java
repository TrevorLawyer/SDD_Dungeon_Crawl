package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import controller.Main;

public class HealthBar {
	
	int currenthealth;
	int previoushealth;
	List<String> hearts = new ArrayList<>();

	public HealthBar() {}


	public void render(Graphics2D g) {
		int xStart = 10;
		previoushealth = Main.gameData.hero.getHealth();
		Font font = new Font("ComicSans",Font.PLAIN, 20);
		g.setFont(font);
		int a1 = previoushealth % 10;
		float a2 = a1 / (float)10.0;
		
		Color normalColor = new Color((float)1.0,(float)0.0,(float)0.0,(float)1.0);
		Color leftoverColor = new Color((float)1.0,(float)0.0,(float)0.0,a2);
		g.setColor(normalColor);
		hearts.clear();
		
		for(int i = 0; i < previoushealth / 10; i++) {
			hearts.add("\u2665");
		}
		
		for(int i = 0; i < hearts.size(); i++) {
			g.drawString(hearts.get(i), xStart, 480);
			xStart += 20;
		}
		
		g.setColor(leftoverColor);
		g.drawString("\u2665", xStart, 480);
		
	}

	public void update() {
	}

}
