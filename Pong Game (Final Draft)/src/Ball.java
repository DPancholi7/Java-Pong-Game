import java.awt.*;
import java.awt.Graphics;


public class Ball {
    // Location Of Ball
    int x = Canvas.WIDTH / 2;
    int y = Canvas.HEIGHT / 2;

    // Width And Height Of Ball
    int Ball_W = 25;
    int Ball_H = 25;

    // Speed Of Ball (x position -> dx) (y position -> dy)
    int dx = 2;
    int dy = 3;


    public void Paint(Graphics g) { // This method handles all graphics throughout the program

        g.fillOval(x, y, Ball_W, Ball_H); // Drawing the Ball

    }

}