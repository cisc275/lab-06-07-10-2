import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
///THREAD SLEEP GOES SOMEWHERE IN HERE

@SuppressWarnings("serial")
public class View extends JPanel{
	
	int picNum = 0;
	int frameCount = 10;
	BufferedImage[] pics;
    BufferedImage[] picsSE;
    BufferedImage[] picsNE;
    BufferedImage[] picsSW;
    BufferedImage[] picsNW;
    BufferedImage[] picsN;
    BufferedImage[] picsS;
    BufferedImage[] picsE;
    BufferedImage[] picsW;
    JFrame frame;
    JPanel pane;
    
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    int xLocation = 0;
    int yLocation = 0;
    Direction direction = Direction.NORTHWEST;

	View(){
		// Loads the frame, places the view inside of it
		this.loadImages();
		JFrame frame = new JFrame();
		//JPanel pane = new JPanel();
		frame.getContentPane().add(this).setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(imgWidth, imgHeight);
		frame.setSize(frameWidth,frameHeight);
		
		//pane.setVisible(true);
		frame.add(this); ///PROBABLY VERY WRONGS
		frame.setVisible(true);
		
	}
	
	public void loadImages() {
		//Preloads the Buffered Image arrays to be used in View
		BufferedImage SE = createImage("orc/orc_forward_southeast.png");
    	BufferedImage NE = createImage("orc/orc_forward_northeast.png");
    	BufferedImage NW = createImage("orc/orc_forward_northwest.png");
    	BufferedImage SW = createImage("orc/orc_forward_southwest.png");
    	BufferedImage N = createImage("orc/orc_forward_north.png");
    	BufferedImage S = createImage("orc/orc_forward_south.png");
    	BufferedImage E = createImage("orc/orc_forward_east.png");
    	BufferedImage W = createImage("orc/orc_forward_west.png");
    	pics = new BufferedImage[10];
    	picsSE = new BufferedImage[10];
    	picsNE = new BufferedImage[10];
    	picsNW = new BufferedImage[10];
    	picsSW = new BufferedImage[10];
    	picsN = new BufferedImage[10];
    	picsS = new BufferedImage[10];
    	picsE = new BufferedImage[10];
    	picsW = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++) {
    		picsSE[i] = SE.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		picsNE[i] = NE.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		picsNW[i] = NW.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		picsSW[i] = SW.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		picsN[i] = N.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		picsS[i] = S.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		picsE[i] = E.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		picsW[i] = W.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i] = SE.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	}
	}
	 BufferedImage createImage(String fileName){
		 // Gets the buffered image
	    	BufferedImage bufferedImage;
	    	try {
	    		bufferedImage = ImageIO.read(new File(fileName)); 
	    		return bufferedImage;                
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }
	 public void paint(Graphics g) {
		 picNum = (picNum + 1) % frameCount;
		 g.drawImage(pics[picNum], xLocation, yLocation, Color.gray, this);
		 
	 }
	 public void update(int xLoc, int yLoc, Direction dir) {
		 //Updates the location of the orc image based on the logic calculated in the model class
		 xLocation = xLoc;
		 yLocation = yLoc;
		 direction = dir;
		 if (direction == Direction.NORTHWEST) {
			 pics = picsNW;
		 }
		 if (direction == Direction.SOUTHWEST) {
			 pics = picsSW;
		 }
		 if (direction == Direction.SOUTHEAST) {
			 pics = picsSE;
		 }
		 if (direction == Direction.NORTHEAST) {
			 pics = picsNE;
		 }
		 if (direction == Direction.NORTH) {
			 pics = picsN;
		 }
		 if (direction ==  Direction.SOUTH) {
			 pics = picsS;
		 }
		 if (direction == Direction.EAST) {
			 pics = picsE;
		 }
		 if (direction == Direction.WEST) {
			 pics = picsW;
		 }
		
		 this.repaint();
		 try {
			 Thread.sleep(100);
		 }
		 catch(InterruptedException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public int getWidth() {
		 return frameWidth;
	 }
	 public int getHeight() {
		 return frameHeight;
	 }
	public int getImageWidth() {
		 return imgWidth;
	 }
	public int getImageHeight() {
		 return imgHeight;
	 }
}