package Death_From_Below;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/****************************
 *@(#)  GameEntity.java
 *@author: Lucas Kuenneke
 *@version 1.0 May 24, 2009
 * Class: CS241L-06
 * Professor: Dr. Doom
 */
public class GameEntity extends JPanel
{
     int panel_Height = 0, rise = 0, panel_Width = 0, xCoordinate = 0,
            yCoordinate = 0, height = 0, width = 0, run = 0, size = 25;
    String entity_image = "galaga.jpg";
    Boolean hit = false;

    /**
     *Constructors
     */
    public GameEntity()
    {
    }//end of default constructor
    
    /**
     * @param Panelheight height of panel
     * @param Panelwidth width of panel
     */
    public GameEntity(int Panelheight, int Panelwidth)
    {
        panel_Height = Panelheight;
        panel_Width = Panelwidth;
        xCoordinate = 0 + width;
        yCoordinate = (int) ((Math.random() * 200) + (Math.random() * 100));
        width = 25;
        run = 5;
    }//end of GameEntity arg constructor

    ImageIcon space = new ImageIcon(entity_image);

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    /**
     * @return current X coordinate
     */
    public int getXCoordinate()
    {
        return xCoordinate;
    }

    /**
     * @return current Y coordinate
     */
    public int getYCoordinate()
    {
        return yCoordinate;
    }
    //constructor

    /**
     *Moves the large ship
     */
    public void move()
    {
        if (xCoordinate < (0 - run) || xCoordinate > (panel_Width - width))
        {
            run = -run;
        }
        xCoordinate += run;
    }//end of void method move

    /**
     *
     * @param g draws the appropriate jpeg image
     */
    public void draw(Graphics g)
    {
        if (hit == false) {
            entity_image = "galaga.jpg";
            ImageIcon space3 = new ImageIcon(entity_image);
     g.drawImage(space3.getImage(), xCoordinate, yCoordinate, size, size, this);
        } else if (hit == true) {
            entity_image = "galaga_blow.jpg";
            ImageIcon space2 = new ImageIcon(entity_image);
     g.drawImage(space2.getImage(), xCoordinate, yCoordinate, size, size, this);
        }
    }//end of void method draw
}//end of class ball