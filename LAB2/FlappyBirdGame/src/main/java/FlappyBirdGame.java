import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBirdGame extends JPanel implements ActionListener, KeyListener {
    JFrame frame;
    Timer timer;
    int width = 360, height = 640;

    Image background, birdImage, topPipe, bottomPipe;
    int birdX = 50, birdY = 300, birdWidth = 34, birdHeight = 24;
    int velocity = 0, gravity = 1, lift = -12;
    boolean gameOver = false;

    ArrayList<Pipe> pipes = new ArrayList<>();
    Random rand = new Random();
    int score = 0;

    public FlappyBirdGame() {
        frame = new JFrame("Flappy Bird");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);

        loadImages();

        this.setFocusable(true);
        this.addKeyListener(this);
        this.requestFocusInWindow();


        timer = new Timer(20, this);
        timer.start();
        spawnPipe();
    }

    void loadImages() {
        background = new ImageIcon("assets/flappybirdbg.png").getImage();
        birdImage = new ImageIcon("assets/flappybird.png").getImage();
        topPipe = new ImageIcon("assets/toppipe.png").getImage();
        bottomPipe = new ImageIcon("assets/bottompipe.png").getImage();
 
    }

    void spawnPipe() {
        int gap = 120;
        int topHeight = rand.nextInt(200) + 50;
        pipes.add(new Pipe(width, topHeight, gap));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, width, height, null);
        g.drawImage(birdImage, birdX, birdY, birdWidth, birdHeight, null);

        for (Pipe pipe : pipes) {
            g.drawImage(topPipe, pipe.x, pipe.topHeight - 320, 52, 320, null);
            g.drawImage(bottomPipe, pipe.x, pipe.topHeight + pipe.gap, 52, 320, null);
        }

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, 20, 40);

        if (gameOver) {
            g.drawString("Game Over!", 100, height / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", 90, height / 2 + 40);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            birdY += velocity;
            velocity += gravity;

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.x -= 4;

                if (pipe.x + 52 < 0) {
                    pipes.remove(i);
                    i--;
                    spawnPipe();
                    score++;
                }

                
                Rectangle birdRect = new Rectangle(birdX, birdY, birdWidth, birdHeight);
                Rectangle topRect = new Rectangle(pipe.x, pipe.topHeight - 320, 52, 320);
                Rectangle bottomRect = new Rectangle(pipe.x, pipe.topHeight + pipe.gap, 52, 320);
                if (birdRect.intersects(topRect) || birdRect.intersects(bottomRect) || birdY > height || birdY < 0) {
                    gameOver = true;
                }
            }
        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) && !gameOver) {
            velocity = lift;
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            // Restart game
            birdY = 300;
            velocity = 0;
            score = 0;
            pipes.clear();
            spawnPipe();
            gameOver = false;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    class Pipe {
        int x, topHeight, gap;

        Pipe(int x, int topHeight, int gap) {
            this.x = x;
            this.topHeight = topHeight;
            this.gap = gap;
        }
    }


    public static void main(String[] args) {
        new FlappyBirdGame();
        
    }
}
