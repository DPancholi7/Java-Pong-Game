/**
 * Assignment: Pong Game
 * Author: Dhvani Pancholi
 * Date: March 10th, 2018
 * Description: A simple pong game where the user tries
 * to prevent the ball from going off the screen.
 */
import java.awt.*; // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interface
import javax.swing.*; // Using Swing's components and containers
/**
 * Custom Graphics Example: Using key/button to move a line up or down.
 */
@SuppressWarnings("serial")
public class Pong extends JFrame {

    // Declaring new objects for Ball Class and Paddle Class
    Paddle myPaddle = new Paddle();
    Ball myBall = new Ball();

    // Declaring new Canvas
    private DrawCanvas canvas; // Custom drawing board

    // Boolean Variables For KeyEvent Methods
    boolean upPressed;
    boolean downPressed;

    // drawing board parameters
    int WIDTH = 1000;
    int HEIGHT = 600;

    // Lock is used to maintain a while loop for the JOption Dialogs!
    int lock = 1;
    int score = 0;
    String value = "Default";
    JLabel label = new JLabel("Score: " + score);

    // Background Color
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;



    // Constructor to set up the GUI components and event handlers
    public Pong() {
// Set up a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

// Set up a custom drawing JPanel
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));

// Adding panels to this JFrame's content-pane
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(buttonPanel, BorderLayout.SOUTH);

        label.setFont(new Font("Serif", Font.BOLD, 40));
        label.setForeground(Color.white);
        canvas.add(label);
        JOptionPane intro = new JOptionPane();
        JOptionPane.showMessageDialog(intro, "Welcome To My Pong Game!"); // Introduction Dialog Message
        String inputValue = "Default";

        while (lock == 1) {
            inputValue = JOptionPane.showInputDialog("Difficulty Choice, Pick A Number From 1 to 5 :");
            JOptionPane error = new JOptionPane();
            // Simple try-catch dialog for invalid input
            try {
                myBall.dx = Integer.parseInt(inputValue);
                myBall.dy = Integer.parseInt(inputValue);
                myPaddle.Paddle_Speed = myBall.dx;
                if (myPaddle.Paddle_Speed > 5) {
                    JOptionPane.showMessageDialog(error, "Error! Number Must Be From 1 to 5! ");
                } else {
                    lock = 0;
                }
            } catch (NumberFormatException a) {
                JOptionPane.showMessageDialog(error, "Error! Enter a Proper Number!");
            }
        }
        addKeyListener(new KeyAdapter() { // Adding KeyListener
            @Override
            public void keyTyped(KeyEvent evt) {
                // detects the key-press method
            }

            public void keyPressed(KeyEvent evt) {
                int keyCode = evt.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) { //If the key is the up key
                    upPressed = true;     //Paddle is being moved up
                }
                if (keyCode == KeyEvent.VK_DOWN) { //If the key is the down key
                    downPressed = true;  //Paddle is being moved down
                }
            }

            public void keyReleased(KeyEvent evt) {
                int keyCode = evt.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) { //If the key is the up key
                    upPressed = false;    //Paddle has stopped being moved
                }
                if (keyCode == KeyEvent.VK_DOWN) { //If the key is the down key
                    downPressed = false;    //Paddle has stopped being moved
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
        setTitle("Pong Game : Dhvani Pancholi");
        pack(); // pack all the components in the JFrame
        setVisible(true); // Makes Canvas Visible
        setResizable(false);
        requestFocus(); // set the focus to JFrame to receive KeyEvent
    }

    /**
     * Defining DrawCanvas, which is a custom drawing board for this program.
     */
    class DrawCanvas extends JPanel implements ActionListener {

        Timer t = new Timer(5, this);

        public void paintComponent(Graphics g) { // This method handles all graphics throughout the program
            super.paintComponent(g);
            setBackground(BACKGROUND_COLOR);
            g.setColor(myPaddle.Paddle_COLOR); // Paddle Color
            myPaddle.Paint(g);
            canvas.add(label);
          myBall.Paint(g);
            t.start();
        }

        public void actionPerformed(ActionEvent e) { // implements the action listener

            if (upPressed == true) {
                if (myPaddle.paddle_y <= 0) {
                    myPaddle.paddle_y += 0;
                } else {
                    myPaddle.paddle_y -= myPaddle.Paddle_Speed;
                }
            }
            if (downPressed == true) {
                if (myPaddle.paddle_y >= canvas.getHeight() - myPaddle.paddle_height) {
                    myPaddle.paddle_y += 0;
                } else {
                    myPaddle.paddle_y += myPaddle.Paddle_Speed;
                }
            }
            // Reversing ball direction if it touches wall
            if (myBall.x + myBall.dx > this.getWidth() - myBall.Ball_W) {
                myBall.dx = -myBall.dx;
            }
            if (myBall.y + myBall.dy > this.getHeight() - myBall.Ball_W || myBall.y + myBall.dy < 0) {
                myBall.dy = -myBall.dy;
            }
            // Ball moving and bouncing off the borders
            if ((myBall.x + myBall.dx <= myPaddle.paddle_x + myPaddle.paddle_width && myBall.x + myBall.dx >= myPaddle.paddle_x) &&
                    (myBall.y + myBall.dy <= myPaddle.paddle_y + myPaddle.paddle_height && myBall.y + myBall.dy + myBall.Ball_H >= myPaddle.paddle_y )) {
                if (myBall.y + myBall.dy == myPaddle.paddle_y || myBall.y + myBall.dy == myPaddle.paddle_y + myPaddle.paddle_height) {
                    myBall.dy = -myBall.dy;
                }
                myBall.dx = -myBall.dx;
                score += 1;
                value = Integer.toString(score);
                label.setText(" Score: " + value);
            }
            if (myBall.x + myBall.dx < -100) {
                 // Confirm dialog with Yes and No buttons:
                JOptionPane finalScore = new JOptionPane();
                JOptionPane.showMessageDialog(finalScore, "Your Final Score Was: " + score);
                int choice = JOptionPane.showConfirmDialog(null, "You Lost, Better Luck Next Time! Play Again?", "Game Over!",
                        JOptionPane.YES_NO_OPTION);
                   // Message dialog when you lose the game
                if (choice == JOptionPane.YES_OPTION) {
                    myBall.x = canvas.getWidth() - (canvas.getWidth()/2);
                    myBall.y = canvas.getHeight() - (canvas.getHeight()/3);
                    score = 0;
                    value = Integer.toString(score);
                    myBall.dx *= -1;
                    label.setText(" Score: " + value);
                } else {
                    System.exit(0);
                }
            }
// updates the screen to constantly draw the ball
            myBall.x += myBall.dx;
            myBall.y += myBall.dy;
            repaint();
        }
    }
    // The entry main() method
    public static void main(String[] args) {
// Run GUI codes on the Event-Dispatcher Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Pong(); // Constructor
            }
        });
    }
}