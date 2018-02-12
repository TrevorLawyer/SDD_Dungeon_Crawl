package controller;

import java.awt.Color;
import java.util.List;
import view.Grid2d;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import model.GameData;
import model.GameFigure;
import static view.Map.col;
import static view.Map.row;

import model.GameData;

public class Animator implements Runnable {

    public boolean running = true, userTurn = true;
    private final int FRAMES_PER_SECOND = 50;

    @Override
    public void run() {

        while (running) {
            long startTime = System.currentTimeMillis();
            
            processCollisions();

            Main.gameData.update();
            Main.frame.gameRender();
            Main.frame.printScreen();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
            if(userTurn){
            	//Player's Turn
            } else{
            	//Enemy's Turn
                enemyMovements();
            	switchTurns();
            }
        }
        System.exit(0);
    }
    
    public void switchTurns() {
		if(userTurn) userTurn=false;
		else userTurn=true;
		
	}

	private void processCollisions() {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method
		if (Main.gameData.friendFigures.get(0).x == Main.gameData.merchant.x && Main.gameData.friendFigures.get(0).y == Main.gameData.merchant.y) {
			Main.gameData.player_dialogue_state = true;
			Main.gameData.merchant_dialogue_window.openWindow();
			GameData.hero.x = Main.gameData.location_memory_min_1_x;
			GameData.hero.y = Main.gameData.location_memory_min_1_y;
		}
    }
        
        public void enemyMovements()
        {
            double range_of_sight = 5.0;
            double eX = GameData.enemy.x;
            double eY = GameData.enemy.y;
            double hX = GameData.hero.x;
            double hY = GameData.hero.y;
            double[][] map = new double[10][10];
            
            for (int i = 0; i < row; i++){
        for (int j = 0; j < col; j++){
            map[i][j] = 0;
        }
    }
System.out.println(eX);
    		if(eX != hX)
                {
                    if(eY != hY)
                    {
                    double totalX = eX - hX;
                    double totalY = eY - hY;
                    System.out.println(Math.sqrt((totalX*totalX)+(totalY*totalY)));
                if(Math.sqrt((totalX*totalX)+(totalY*totalY)) <=range_of_sight)
                {
                    Grid2d map2d = new Grid2d(map, false);
                    int x =Integer.parseInt(map2d.findPath(GameData.enemy.x, GameData.enemy.y, GameData.hero.x, GameData.hero.y).get(1).toString().substring(1,2));
                    int y =Integer.parseInt(map2d.findPath(GameData.enemy.x, GameData.enemy.y, GameData.hero.x, GameData.hero.y).get(1).toString().substring(4,5));
                
                    GameData.enemy.x = x;
                    GameData.enemy.y = y;
                }
                }
                }
                }


}