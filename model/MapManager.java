<<<<<<< HEAD
package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class MapManager {
	private int currentMap;
	private ArrayList<GameMap> maps;
	
	public MapManager() {
		maps = new ArrayList<>();
		maps.add(new GameMap());
		currentMap = 0;
	}
	
	public void goNext() {				
		maps.add(new GameMap(maps.get(currentMap).exit));		
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
	
}
=======
package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class MapManager {
	private int currentMap;
	private ArrayList<GameMap> maps;
	
	public MapManager() {
		maps = new ArrayList<>();
		maps.add(new GameMap());
		currentMap = 0;
	}
	public void goNext() {
		
		
		maps.add(new GameMap(maps.get(currentMap).exit));
		GameData.swab(); // remove old enemies
        GameData.spawn(); // make new enemy as new map is generated
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
		return maps.get(currentMap).entrance;
	}
	public void render(Graphics2D g) {
		maps.get(currentMap).render(g);
	}
	
}
>>>>>>> Clifton_sprint2
