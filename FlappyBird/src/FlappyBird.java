import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    //player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    //pipes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    ArrayList<Pipe> pipes;

    //game logic
    Timer gameloop;
    Timer pipesCooldown;
    int gravity = 1;

    //game state
    boolean gameOver = false;
    int score = 0;
    JLabel scoreLabel;
    Font scoreFont = new Font("Arial", Font.BOLD, 30);

    // konstruktor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        // load assets
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Initialize score label
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(scoreFont);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(frameWidth - 100, 20, 80, 40);
        add(scoreLabel);

        GameOn();
    }

    public void GameOn() {
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();
        gameOver = false;
        score = 0;
        scoreLabel.setText("0");

        if (gameloop != null) gameloop.stop();
        if (pipesCooldown != null) pipesCooldown.stop();

        pipesCooldown = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    System.out.println("pipa");
                    placePipes();
                }
            }
        });
        pipesCooldown.start();

        gameloop = new Timer(1000/60, this);
        gameloop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, 0, frameWidth, frameHeight);
            g.setColor(Color.WHITE);
            g.setFont(scoreFont);

            // Hitung lebar teks untuk center
            FontMetrics metrics = g.getFontMetrics(scoreFont);

            // Game Over text
            String gameOverText = "Game Over";
            int gameOverWidth = metrics.stringWidth(gameOverText);
            g.drawString(gameOverText, (frameWidth - gameOverWidth)/2, frameHeight/2 - 50);

            // Score text
            String scoreText = "Score: " + score;
            int scoreWidth = metrics.stringWidth(scoreText);
            g.drawString(scoreText, (frameWidth - scoreWidth)/2, frameHeight/2);

            // Restart instruction
            String restartText = "Press R to restart";
            int restartWidth = metrics.stringWidth(restartText);
            g.drawString(restartText, (frameWidth - restartWidth)/2, frameHeight/2 + 50);
        }
    }

    public void move() {
        if (gameOver) return;

        // Apply gravity to player
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());

        // Check if player hits the ground
        if (player.getPosY() > frameHeight - player.getHeight()) {
            gameOver();
            return;
        }

        // Move pipes and check collisions
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // Check if player passed the pipe
            if (pipe.getImage() == upperPipeImage && !pipe.isPassed() &&
                    player.getPosX() > pipe.getPosX() + pipe.getWidth()) {
                pipe.setPassed(true);
                score++;
                scoreLabel.setText(String.valueOf(score));
            }

            // Check collision with pipe
            if (collision(player, pipe)) {
                gameOver();
                return;
            }

            // Remove pipes that are off screen
            if (pipe.getPosX() < -pipe.getWidth()) {
                pipes.remove(pipe);
                i--;
            }
        }
    }

    private void gameOver() {
        gameOver = true;
        gameloop.stop();
        pipesCooldown.stop();
    }

    private boolean collision(Player player, Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(),
                player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(),
                pipe.getWidth(), pipe.getHeight());
        return playerRect.intersects(pipeRect);
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/6;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            GameOn();
        } else if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
