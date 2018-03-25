package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class MapManager {
	private int currentMap;
	private ArrayList<GameMap> maps;
	public MapTextures textures;
	
	public MapManager() {
		textures = new MapTextures();
		maps = new ArrayList<>();
		
		currentMap = 0;
		
		maps.add(new GameMap());
	}
	public void goNext() {	
		maps.add(new GameMap(maps.get(currentMap).exit, currentMap));
		
		GameData.swab(); // remove old enemies
		for(int x = 0; x < Math.random()*2;x++)
		{
			GameData.spawn(); // make new enemy as new map is generated
		}
			currentMap++;
	}
	
	public void goBack() {
	    if(currentMap > 0) {
	    	currentMap--;
	    }
	}
	public GameMap getCurrent() {
		return maps.get(currentMap);
	}
	public Coordinate getExit() {
		return maps.get(currentMap).exit;
	}
	public Coordinate getEntrance() {
		if (currentMap == 0) return new Coordinate(0,0);
		else return maps.get(currentMap).entrance;
	}
	public void render(Graphics2D g) {
		maps.get(currentMap).render(g);
	}
	public boolean isPassable(int x,int y) {
		if(maps.get(currentMap).isPassable(x,y)) {
			return true;
		}
		else return false;
	}
}