import java.awt.*;
import java.awt.Graphics;

public class Paddle {

    // Define constants for the various dimensions
    public static final Color Paddle_COLOR = Color.WHITE; // Color For Paddle
    int Paddle_Speed = 0;

    // paddle variables paddle_x and paddle_y are the top left position and paddle_width and paddle_height are the width and height
     int paddle_x = 200;
     int paddle_y = 50;
     int paddle_width = 10;
     int paddle_height = 100;




    public void Paint (Graphics g) {

        g.fillRect(paddle_x, paddle_y, paddle_width,paddle_height); // Drawing the Paddle


    }
}

