package Death_From_Below;

import java.awt.Graphics;
import javax.swing.ImageIcon;
/**
 *
 * @author LK
 */
public class SmallShip extends GameEntity
{
    ImageIcon small_ship = new ImageIcon("dig_dug.jpg");
    public SmallShip()
    {
    }//end of default constructor

    SmallShip(int Panelheight, int Panelwidth)
    {
        panel_Height = Panelheight;
        panel_Width = Panelwidth;
        yCoordinate = 100;
        width = 15;
        run = width;
        size = 15;
        run = 7;
        rise = (int) (Math.random() * 10);
    }//end of constructor
    @Override
            public void draw(Graphics g)
    {
    g.drawImage(small_ship.getImage(), xCoordinate, yCoordinate, size, size, this);
    }//end of void method draw

    @Override
    public void move() {
        // Move's the Ball
        if (xCoordinate < (0 - run) || xCoordinate > (panel_Width - width)) {
            run = -run;
        }

        if (yCoordinate < (0 - rise) || yCoordinate > (panel_Height - height)) {
            rise = -rise;
        }
        if (yCoordinate < 250)
        {
            rise = -rise;
        }
        //ball movemet is based on rise and run
        xCoordinate += run;
        yCoordinate += rise;
}
}
