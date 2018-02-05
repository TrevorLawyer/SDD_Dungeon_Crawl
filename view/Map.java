/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;




import controller.Main;
import model.GameFigure;
/**
 *
 * @author Matthew
 */
public class Map extends JFrame {
	JPanel panel = new JPanel();
    public final static int row = 10, col = 10;
    JLabel[][] grid= new JLabel[row][col];
    private Graphics2D g2;
    private Image dbImage = null;  //Taken from OOP project to get the screen to update
    public static int width, height;
    
    public Map() {
        // contentPane's default layout manager --> Border Layout
    getContentPane().add(panel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 500, 500);
    panel.setLayout(new GridLayout(row, col));

    for (int i = 0; i < row; i++){
        for (int j = 0; j < col; j++){
            grid[i][j] = new JLabel();
            grid[i][j].setBorder(new LineBorder(Color.BLACK));
            grid[i][j].setOpaque(true);
            panel.add(grid[i][j]);
        }
    }
    }
    
    public void PlaceCharacter(GameFigure character)
    {
        BufferedImage image = character.currentPic;
        
        Image img= image.getScaledInstance(grid[character.x][character.y].getWidth(), grid[character.x][character.y].getHeight(),
        Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(img);
        
        grid[character.x][character.y].setIcon(imageIcon);
    }
     public void PlaceEnemy(int x, int y)
    {  
//       grid[row/2][col/2]
    }

	public void printScreen() {
		Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
		
	}

	public void gameRender() {
		
		width = getSize().width;
        height = getSize().height;
        if (dbImage == null) {
            // Creates an off-screen drawable image to be used for double buffering
            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }

        g2.clearRect(0, 0, width, height);
/*		synchronized (Main.gameData.enemyFigures) {
            
            for (GameFigure f : Main.gameData.enemyFigures) {
                f.render(g2);
            }
        }
*/        synchronized (Main.gameData.friendFigures){
            for (GameFigure f : Main.gameData.friendFigures) {
                f.render(g2);
            }
          
		
	}
     
	}
     
     
}