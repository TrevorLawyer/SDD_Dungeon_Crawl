package model;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapTextures {
	
	public Image grass;
	public Image boulder;
	public Image treeBoundary;
	public Image ladderDown;
	
	public MapTextures() {
		
		try {
			boulder = ImageIO.read(this.getClass().getResource("boulder.png"));
			grass = ImageIO.read(this.getClass().getResource("grass.png"));
			treeBoundary = ImageIO.read(this.getClass().getResource("treeBoundary.png"));
			ladderDown = ImageIO.read(this.getClass().getResource("ladderDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("FUCCKKKK");
		}
		
	}
}
