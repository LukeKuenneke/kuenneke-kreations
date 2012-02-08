package Death_From_Below;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/****************************
 *@(#)  SmallEnemy.java
 *@author: Lucas Kuenneke
 *@version 1.0 May 24, 2009
 * Class: CS241L-06
 * Professor: Dr. Doom
 */
public class SmallEnemy extends GameEntity {

    ImageIcon small_ship = new ImageIcon("dig_dug.jpg");

    /**
     *Constructor for SmallEnemy building off GameEntity attributes
     */
    public SmallEnemy() {
    }//end of default constructor

    SmallEnemy(int Panelheight, int Panelwidth) {
        panel_Height = Panelheight;
        panel_Width = Panelwidth;
        yCoordinate = (int) ((Math.random() * 25) + (Math.random() * 200));
        width = 15;
        run = width;
        size = 15;
        run = 1;
        rise = (int) (Math.random() * 10);
    }//end of constructor

    /**
     * @param g variable used for reference of drawing the ship
     */
    @Override
    public void draw(Graphics g) {
        if (hit == false) {
            entity_image = "dig_dug.jpg";
            ImageIcon space3 = new ImageIcon(entity_image);
     g.drawImage(space3.getImage(), xCoordinate, yCoordinate, size, size, this);
        } else if (hit == true) {
            entity_image = "dig_dug_blow.jpg";
            ImageIcon space2 = new ImageIcon(entity_image);
     g.drawImage(space2.getImage(), xCoordinate, yCoordinate, size, size, this);
        }
    }

    /**
     * Move method overrides the Gameentity one as this is more irratic.
     */
    @Override
    public void move() {
        // Move's the Ball
        if (xCoordinate < (0 - run) || xCoordinate > (panel_Width - width)) {
            run = -run;
        }

        if (yCoordinate < (0 - rise) || yCoordinate > (panel_Height - height)) {
            rise = -rise;
        }
        if (yCoordinate > 250 && yCoordinate > 200) {
            rise = (int) (Math.random() * -10);
        }
        //ball movemet is based on rise and run
        xCoordinate += run;
        yCoordinate += rise;
    }
}
