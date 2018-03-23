package controller;

import java.awt.Color;
import java.util.List;
import view.Grid2d;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;
import model.GameData;
import model.GameFigure;
import static view.Map.col;
import static view.Map.row;

import model.GameData;

public class Animator implements Runnable {

    public boolean running = true, userTurn = true;
    private final int FRAMES_PER_SECOND = 20;

    @Override
    public void run() {

        while (running) {
            long startTime = System.currentTimeMillis();
            
            processCollisions();         
            Main.gameData.update();
            Main.frame.gameRender();
            Main.frame.printScreen();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND*1000)
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
            //If the hero's health reaches zero, stop animation, dispose of the window, and stop audio.
            if(Main.gameData.hero.getHealth() <= 0) {
            	running = false;
            	Main.frame.stopSound();
            	Main.frame.dispose();
            	gameOver(); //Shows the gameover splashscreen
            }
            
        }
        System.exit(0);
        
    }
    
    public void switchTurns() {
		if(userTurn) userTurn=false;
		else userTurn=true;
		
	}
    
	private static void gameOver() {
		JWindow window = new JWindow();
		window.getContentPane().setBackground(Color.BLACK);
		ImageIcon deathImage = new ImageIcon("model/skeleton.png");
		JLabel deathText = new JLabel("You have Perished!", deathImage,  JLabel.CENTER);
		deathText.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 28));
		deathText.setVerticalTextPosition(JLabel.BOTTOM);
		deathText.setHorizontalTextPosition(JLabel.CENTER);
		deathText.setForeground(Color.RED);
		window.getContentPane().add(deathText);
		window.setBounds(100, 100, 400, 400);
		window.setVisible(true);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.setVisible(false);
		window.dispose();
	}
    

	private void processCollisions() {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method
		if (Main.gameData.friendFigures.get(0).x == Main.gameData.merchant.x && Main.gameData.friendFigures.get(0).y == Main.gameData.merchant.y) {
			Main.gameData.game_state = GameData.MERCHANT_DIALOG;
			Main.gameData.merchant_dialogue_window.openWindow();
			GameData.hero.x = Main.gameData.location_memory_min_1_x;
			GameData.hero.y = Main.gameData.location_memory_min_1_y;
		}
    }
        
        public void enemyMovements()
        {
        	if(Main.gameData.enemyFigures.size() > 0)
        	{
        	for(int z = 0; z < Main.gameData.enemyFigures.size();z++)
        	{
        		
        		double range_of_sight = 5.0;
        		double eX = Main.gameData.enemyFigures.get(z).x;
        		double eY = Main.gameData.enemyFigures.get(z).y;
        		double hX = GameData.hero.x;
        		double hY = GameData.hero.y;
            
        		int[][] map = new int[10][10];
            
        		for (int i = 0; i < row; i++){
        			for (int j = 0; j < col; j++){

                        	map[i][j] = 0;                        
        			}
        		}

//            map[4][5] = -1;
//            map[4][4] = -1;
//            map[4][6] = -1;
//            map[4][7] = -1;
                Grid2d map2d = new Grid2d(map, false);
                System.out.println(map2d.findPath(Main.gameData.enemyFigures.get(z).x, Main.gameData.enemyFigures.get(z).y, GameData.hero.x, GameData.hero.y));

                double totalX = eX - hX;
                double totalY = eY - hY;
   //             System.out.println(Math.sqrt((totalX*totalX)+(totalY*totalY)));
                double distance_away = Math.sqrt((totalX*totalX)+(totalY*totalY));
                if(distance_away <=range_of_sight)
                {
                	if(distance_away > 1.9)
                	{
                	
                		int x =Integer.parseInt(map2d.findPath(Main.gameData.enemyFigures.get(z).x, Main.gameData.enemyFigures.get(z).y, GameData.hero.x, GameData.hero.y).get(1).toString().substring(1,2));
                		int y =Integer.parseInt(map2d.findPath(Main.gameData.enemyFigures.get(z).x, Main.gameData.enemyFigures.get(z).y, GameData.hero.x, GameData.hero.y).get(1).toString().substring(4,5));
                
                		Main.gameData.enemyFigures.get(z).x = x;
                		Main.gameData.enemyFigures.get(z).y = y;
                	}
                }
            }
        }
        }

}