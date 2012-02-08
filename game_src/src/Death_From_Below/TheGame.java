package Death_From_Below;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/****************************
 *@(#)  TheGame.java
 *@author: Lucas Kuenneke
 *@version 1.0 May 24, 2009
 * Class: CS241L-06
 * Professor: Dr. Doom
 */
public class TheGame extends JFrame implements ActionListener {
    //sets size of game window

    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    Frame message_frame;
    private GamelPanel Gamewindow;

    /**
     *method is used to control redraw rate : 20ms
     */
    public static void pause() {
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * continues to draw the game and sets up all the configuration for
     * input, display, and game elements.
     */
    public TheGame() {
        super("Lucas Kuenneke : Death From Below 3!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(new BorderLayout());
        Gamewindow = new GamelPanel();

        JButton fireButton = new JButton("Fire!");
        JLabel display_score = new JLabel();

        fireButton.addActionListener(this);

        this.getContentPane().add(fireButton, BorderLayout.SOUTH);
        this.getContentPane().add(display_score, BorderLayout.NORTH);

        add(Gamewindow);
        center(this);
        setVisible(true);
        Gamewindow.addGameEntity();
        Gamewindow.addSmallShip();

        while (true) {
            pause();
            Gamewindow.removeBullet();
            Gamewindow.detectCollisions();
            Gamewindow.deathAnimate();
            Gamewindow.move();
            display_score.setText("Score: " +
                    String.valueOf(Gamewindow.getScore()) + "           " +
                    "Shots fired: " +
                    String.valueOf(Gamewindow.getShotsFired()) + "           " +
                    "Shots missed: " + Gamewindow.getMissedBullet());
            Gamewindow.repaint();
            gameStatus(Gamewindow.getScore(), Gamewindow.getMissedBullet(),
                    Gamewindow.ammo_used);
        }
    }

    /**
     * @param frame initalizes the gameframe
     */
    public static void center(JFrame frame) {
     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();

        int w = frame.getWidth();
        int h = frame.getHeight();

        int x = center.x - w / 2, y = center.y - h / 2;
        frame.setBounds(x, y, w, h);
        frame.validate();
    }

    public void actionPerformed(ActionEvent e) {
        Gamewindow.Fire();
    }

    /**
     *
     * @param score passes the current value of score variable
     * @param bullet_miss passes the current value of missed bullets variable
     * @param shots passes the current value of total shots variable
     */
    public void gameStatus(int score, int bullet_miss, int shots) {
        int win = 20;
        if (score >= win && bullet_miss != 10) {
            JOptionPane.showMessageDialog(message_frame, "You have survived " +
                    "the battle!" + "\n with " + score + " Points and " +
                    bullet_miss + " Misses");
            Gamewindow.gameOver();
        } else if (bullet_miss == 10) {
            JOptionPane.showMessageDialog(message_frame, "You have lost the " +
                    "battle!" + "\n with " + score + " Points and " +
                    bullet_miss + " Misses!");
            Gamewindow.gameOver();
        } else if (shots == 20 && score < 20) {
            JOptionPane.showMessageDialog(message_frame, "You have used all " +
                    "of your ammo!" + "\n with " + score + " Points and "
                    + bullet_miss + " Misses!");
            Gamewindow.gameOver();
        }
    }

    /**
     *executes the game
     */
    public static void main(String[] args) {
        TheGame start = new TheGame();
    }
}