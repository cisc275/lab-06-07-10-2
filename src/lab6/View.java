/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 * */
package lab6;

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

class View extends JPanel{

    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imageWidth = 165;
    final static int imageHeight = 165;
    final static int frameCount = 10;
    final static int fireFrameCount = 4;
    final static int jumpFrameCount = 8;
    Direction direction;
    int xloc = 0;
    int yloc = 0;
    static int picNum = 0;
    static BufferedImage[][] pics;
    static BufferedImage[][] firePics;
    static BufferedImage[][] jumpPics;
    static JFrame frame;
    JButton jb;
    static boolean paused = false;
    static boolean fire = false;
    static boolean jump = false;

    public View() {
        frame = new JFrame();
        frame.setFocusable(true);
        jb = new JButton("Pause");
        jb.setBounds(frameWidth - 150, 0, 100, 50);
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paused = !paused;
                jb.setFocusable(false);
            }
        });
        jb.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        frame.add(jb);
        frame.getContentPane().add(this);
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);

        String[] directions = {"north", "northeast", "east", "southeast", "south", "southwest",
            "west", "northwest"};

        pics = new BufferedImage[8][10];
        firePics = new BufferedImage[8][4];
        jumpPics = new BufferedImage[8][8];
        //load orc firing
        for (int i = 0; i < 8; i++) {
            BufferedImage img = createImage("orc_fire_" + directions[i]);
            //frameCount updates to 4 when key is pressed
            for (int j = 0; j < fireFrameCount; j++) {
                firePics[i][j] = img.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
            }
        }
        //load orc jumping
        for (int i = 0; i < 8; i++) {
            BufferedImage img = createImage("orc_jump_" + directions[i]);
            //frameCount updates to 8 when key is pressed
            for (int j = 0; j < jumpFrameCount; j++) {
                jumpPics[i][j] = img.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
            }
        }
        //load orc walking
        for (int i = 0; i < 8; i++) {
            BufferedImage img = createImage("orc_forward_" + directions[i]);
            for (int j = 0; j < frameCount; j++) {
                pics[i][j] = img.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
            }
        }
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        if (!paused && jump) {
            picNum++;
            if(picNum == jumpFrameCount){
                jump = false;
            }
            else{
                g.drawImage(jumpPics[direction.ordinal()][picNum], xloc, yloc, Color.gray, this);
            }
        } else if (!paused && fire) {
            picNum++;
            if(picNum == fireFrameCount){
                fire = false;
            }
            else{
                g.drawImage(firePics[direction.ordinal()][picNum], xloc, yloc, Color.gray, this);
            }
            
        } else if (!paused) {
            picNum = (picNum + 1) % frameCount;
            g.drawImage(pics[direction.ordinal()][picNum], xloc, yloc, Color.gray, this);
        } else {
            g.drawImage(pics[direction.ordinal()][picNum], xloc, yloc, Color.gray, this);
        }

    }

    private BufferedImage createImage(String path) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File("images/orc/"
                    + path + ".png"));
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

        // TODO: Change this method so you can load other orc animation bitmaps
    }

    public int getWidth() {
        return frameWidth;
    }

    public int getHeight() {
        return frameHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void update(int xlocation, int ylocation, Direction dir) {
        direction = dir;
        xloc = xlocation;
        yloc = ylocation;
        frame.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
