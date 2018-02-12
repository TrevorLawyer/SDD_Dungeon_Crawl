package model;

import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

public class GameMap {
	//Game Board width and height
	public int mapWidth = 10;
	public int mapHeight = 10;
	
	private Coordinate previousExit;
	public  Coordinate entrance;
	public Coordinate exit;
	
	GameMapTile[][] thisMap;
	
	public GameMap() {
		generateMap();
		
	}
	public GameMap(Coordinate previousExit) {
		this.previousExit = previousExit;
		generateMap();
	}
	private void generateMap() {
		thisMap = new GameMapTile[mapWidth][mapHeight];
		for(int x = 0; x < mapWidth; x++ ) {
			for(int y = 0; y < mapHeight; y++) {
				thisMap[x][y] = new GameMapTile(x,y);
				if(x == 0 || y == 0 || x == mapWidth - 1 || y== mapHeight - 1) {
					thisMap[x][y].tileEmpty = false;
				}
				else thisMap[x][y].tileEmpty = true;			
			}
		}
		if (previousExit != null) {
			entrance = getEntrance();
			thisMap[entrance.x][entrance.y].tileEmpty = true;
		}
		exit = makeExit(entrance);
		thisMap[exit.x][exit.y].tileEmpty = true;
		
		
	}
	//Returns an X coord between 1 and Map Width -1. This prevents corners and boundaries from being selected.
	public int randXCoord() {
		return ThreadLocalRandom.current().nextInt(1, mapWidth);
	}
	//Returns a Y coord between 1 and Map Width -1. This prevents corners and boundaries from being selected.
	public int randYCoord() {
		return ThreadLocalRandom.current().nextInt(1, mapWidth);
	}
	private int pickExitSide(){
		return ThreadLocalRandom.current().nextInt(0, 4);
	}
	
	public Coordinate makeExit(Coordinate currentEntrance) {
		if(this.entrance == null) {
			return new Coordinate(mapWidth/2,  mapHeight - 1);
		}
		else {
			int x,y;
			int randSide;
			if (entrance.x == 0) {
				if (pickExitSide() == 0) {
					x = randXCoord(); y = 0;
				}
				else if(pickExitSide() == 1) {
					x = mapWidth -1 ; y = randYCoord();
				}
				else {
					x = randXCoord(); y = mapHeight - 1;
				}
			}
			else if(entrance.y == 0) {
				if (pickExitSide() == 0) {
					x = 0; y = randYCoord();
				}
				else if(pickExitSide() == 1) {
					x = mapWidth -1 ; y = randYCoord();
				}
				else {
					x = randXCoord(); y = mapHeight - 1;
				}
			}
			else if(entrance.x == mapWidth - 1) {
				if (pickExitSide() == 0) {
					x = 0; y = randYCoord();
				}
				else if(pickExitSide() == 1) {
					x = randXCoord(); y = 0;
				}
				else {
					x = randXCoord(); y = mapHeight - 1;
				}
				
			}
			else {
				if (pickExitSide() == 0) {
					x = 0; y = randYCoord();
				}
				else if(pickExitSide() == 1) {
					x = randXCoord(); y = 0;
				}
				else {
					x = mapWidth -1 ; y = randYCoord();
				}
			}
			return new Coordinate(x,y);
		}
	}
	public Coordinate getEntrance() {
		int x, y;
		if(previousExit.x == 0) {
			x = mapWidth -1; y = previousExit.y;
		}
		else if(previousExit.x == mapWidth - 1) {
			x = 0; y = previousExit.y;
		}
		else if(previousExit.y == 0) {
			x = previousExit.x; y = mapHeight - 1;
		}
		else {
			x = previousExit.x; y = 0;
		}
		
		return new Coordinate(x,y);
	}
	public void render(Graphics2D g) {
		//printToConsole();
		for(int x = 0; x < mapWidth; x++ ) {
			for(int y = 0; y < mapHeight; y++) {
				thisMap[x][y].render(g);
			}
		}
	}
	public GameMapTile getTile(int x, int y) {
		return thisMap[x][y];
	}
//	public void printToConsole() {
//		for(int x = 0; x < mapWidth; x++ ) {
//			for(int y = 0; y < mapHeight; y++) {
//				if(thisMap[x][y].tileEmpty) {
//					System.out.print("X ");
//				}
//				else System.out.print("- ");
//			}
//			System.out.println();
//		}
//	}
	
}