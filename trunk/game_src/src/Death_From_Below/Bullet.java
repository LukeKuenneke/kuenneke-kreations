package Death_From_Below;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/****************************
 *@(#) Bullet.java
 *@author: Lucas Kuenneke
 *@version 1.0 May 24, 2009
 * Class: CS241L-06
 * Professor: Dr. Doom
 */
public class Bullet extends GameEntity {

    ImageIcon bullet = new ImageIcon("bullet.jpg");

    /**
     *Constructor
     */
    public Bullet() {
    }//end of default constructor

    /**
     * @param x_cor location where the bullet starts
     * @param y_cor location where the bullet starts
     * @param Panelheight The size of the panel
     * @param Panelwidth The size of the panel
     */
    public Bullet(int x_cor, int y_cor, int Panelheight, int Panelwidth) {
        panel_Height = Panelheight;
        panel_Width = Panelwidth;
        xCoordinate = (x_cor + 50);
        yCoordinate = y_cor;
        width = 25;
        run = width;
        size = 5;
    }//end of constructor

    /**
     * Fires straight up at -7
     */
    @Override
    public void move() {
        if (yCoordinate < (panel_Height + width)) {
            rise = -6;
        }
        yCoordinate += rise;
        if (yCoordinate < 0) {
        }
    }//end of void method move

    /**
     * @param g draws the bullet
     */
    @Override
    public void draw(Graphics g) {
     g.drawImage(bullet.getImage(), xCoordinate, yCoordinate, size, size, this);
    }//end of void method draw
}
