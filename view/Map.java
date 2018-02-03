/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import model.GameFigure;
/**
 *
 * @author Matthew
 */
public class Map extends JFrame {
JPanel panel = new JPanel();
    int row = 10;
    int col = 10;
    JLabel[][] grid= new JLabel[row][col];
    
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
     
     
}