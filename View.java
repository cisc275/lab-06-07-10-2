import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.KeyStroke;

public class View extends JPanel{
	
	int picNum;
	int frameCount;
	BufferedImage[] flyForward;
	BufferedImage[] flyUp;
	BufferedImage[] flyDown;
	BufferedImage[] catchPrey;
	BufferedImage[] crash;
	BufferedImage[] enterNest;
	BufferedImage[] exitNest;
	BufferedImage[] miniMap1;
	BufferedImage[] miniMap2;
	BufferedImage[] miniMap3;
	BufferedImage[] miniMap4;
	JFrame frame;
	JPanel pane;
	int frameWidth;
	int frameHeight;
	int imgWidth;
	int imgHeight;
	int xLocation;
	int yLocation;
	Direction direction;
	boolean paused;
	
	public View() {
		
	}
	
	public void loadImages() {
		
	}
	
	public BufferedImage createImage() {
		
		return null;
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void update(int xLoc, int yLoc, Direction dir) {
		
	}
	
	public void renderGame() {
		
	}
	
	public void displayStartScreen() {
		
	}
	
	public void displayMiniMap() {
		
	}
	
	public void displayEndScreen() {
		
	}
	
}
