package controller;

import java.util.concurrent.TimeUnit;

public class Animator implements Runnable {

    public boolean running = true;
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
        }
        System.exit(0);
    }
    
    private void processCollisions() {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method
    }

}