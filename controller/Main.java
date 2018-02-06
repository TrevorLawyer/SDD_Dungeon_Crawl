/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.Map;
import javax.swing.JFrame;
import model.GameData;

/**
 *
 * @author trevo
 */
public class Main 
{
        public static GameData gameData;
        public static Animator animator;
        public static Map frame;
    public static void main(String[] args) {
        
        frame = new Map();
        frame.addKeyListener(new KeyController());
        animator = new Animator();

        frame.setTitle("DEMO MAP");
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        gameData = new GameData();
        
        new Thread(animator).start();
        
    }
    

}
