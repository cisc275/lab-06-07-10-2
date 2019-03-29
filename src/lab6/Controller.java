/**
 * Do not modify this file without permission from your TA.
 * */
package lab6;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{

    private Model model;
    private View view;

    public Controller() {
        view = new View();
        model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
    }

    //run the simulation
    public void start() {
        for (int i = 0; i < 5000; i++) {
            //increment the x and y coordinates, alter direction if necessary
            model.updateLocationAndDirection();
            //update the view
            view.update(model.getX(), model.getY(), model.getDirect());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("KeyPressed");
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_F && !View.paused) {
            View.picNum = 0;
            View.fire = true;
        }
        else if (key == KeyEvent.VK_SPACE && !View.paused) {
            View.picNum = 0;
            View.jump = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
