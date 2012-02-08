package Death_From_Below;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/****************************
 *@(#)  GamelPanel.java
 *@author: Lucas Kuenneke
 *@version 1.0 May 24, 2009
 * Class: CS241L-06
 * Professor: Dr. Doom
 */
public class GamelPanel extends JPanel {

    ImageIcon bgImage = new ImageIcon("abstact_bg.jpg");
    ImageIcon shooter = new ImageIcon("Shooter.jpg");
    GameEntity Bullet;
    GameEntity GameEntity;
    GameEntity SmallShip;
    private ArrayList<GameEntity> game_component;
    private ArrayList<Bullet> bullet_store;
    int ammo_used = 0, current_score = 0, bullet_miss = 0;

    /** Creates a new instance of GamelPanel */
    public GamelPanel() {
        super();
        game_component = new ArrayList<GameEntity>();
        bullet_store = new ArrayList<Bullet>();
    }

    /**
      *Paints the game_component at their current positions within the panel.
      */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage.getImage(), 0, 0, null);
        g.drawImage(shooter.getImage(), ((getWidth() / 2) - 50), (getHeight() - 30), null);
        for (GameEntity b : game_component) {
            b.draw(g);
        }
        for (Bullet b : bullet_store) {
            b.draw(g);
        }
    } // end method paintComponent   

    /**
     *Moves the targets
     */
    public void move() {
        // Move GameEntity 1
        // If ball is approaching a wall, reverse direction
        for (GameEntity b : game_component) {
            b.move();
        }
        for (Bullet b : bullet_store) {
            b.move();
        }
        if (Bullet != null) {
            Bullet.move();
        }
    } // end method move

    /**
     *Adds a large ship by a random generated x coordinate
     */
    public void addGameEntity() {
        game_component.add(new GameEntity(getHeight(), getWidth()));
    }

    /**
     *Adds a smallship by a random generated x coordinate
     */
    public void addSmallShip() {
        game_component.add(new SmallEnemy(getHeight(), getWidth()));
    }

    /**
     * fires a bullet only if there are no other ones present on the screen
     */
    public void Fire() {
        if ((bullet_store.size() <= 0) && ammo_used < 20) {
            bullet_store.add(new Bullet(((getWidth() / 2) - 50),
                    (getHeight() - 30), this.getHeight(), this.getWidth()));
            ammo_used++;
        }
    }

    /**
     * @param object1 object to compare with other
     * @param object2 object to compare with other
     * checks for overlapping objects
     * @return
     */
    public boolean detectCollision(GameEntity object1, GameEntity object2) {
        int shiptopbound = object1.getYCoordinate();
        int shipbottombound = shiptopbound + 24;
        int shipleftbound = object1.getXCoordinate();
        int shiprightbound = shipleftbound + 24;
        int bullettopbound = object2.getYCoordinate();
        int bulletbottombound = bullettopbound + 4;
        int bulletleftbound = object2.getXCoordinate();
        int bulletrightbound = bulletleftbound + 4;
        boolean hit = false;
     if (bullettopbound < shipbottombound && bulletbottombound > shiptopbound) {
     if (bulletrightbound > shipleftbound && bulletleftbound < shiprightbound) {
                hit = true;
            }
        }
        return hit;
    }//end of boolean method detectColl

    /**
     *Detects collisions of 2 entitiy's.
     */
    public void detectCollisions() {
        for (int i = 0; i < game_component.size(); i++) {
            for (int j = 0; j < bullet_store.size(); j++) {
              if (detectCollision(game_component.get(i), bullet_store.get(j))) {
                    if (game_component.get(i) instanceof SmallEnemy) {
                        game_component.get(i).hit = true;
                        game_component.get(i).repaint();
                        addSmallShip();
                        current_score += 2;
                    } else if (game_component.get(i) instanceof GameEntity) {
                        game_component.get(i).hit = true;
                        game_component.get(i).repaint();
                        addGameEntity();
                        current_score++;
                    }
                    bullet_store.remove(j);
                    i--;
                    j--;
                }
            }
        }
    }

    public void deathAnimate() {
        for (int i = 0; i < game_component.size(); i++) {
            if (game_component.get(i).getXCoordinate() >= getWidth() - 100
                    && game_component.get(i).hit == true) {
                game_component.remove(i);
            }
        }
    }//end of void method detectCollisions

    /**
     *Removes the bullet if it goes out of the frame
     */
    public void removeBullet() {
        for (int j = 0; j < bullet_store.size(); j++) {
            if (bullet_store.get(j).yCoordinate < 0) {
                bullet_store.remove(j);
                bullet_miss++;
            }
        }
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return current_score;
    }

    /**
     * @return the current number of shots fired
     */
    public int getShotsFired() {
        return ammo_used;
    }

    /**
     *
     * @return total number of misses
     */
    public int getMissedBullet() {
        return bullet_miss;
    }

    /**
     *will exit the game.
     */
    public void gameOver() {
        System.exit(0);
    }
}//end of class GamelPanel