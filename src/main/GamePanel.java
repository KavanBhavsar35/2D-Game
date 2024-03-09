package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN Settings
    final int originalTileSize = 16; // 16*16
    final int scale = 3;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    // Calculating Sizes
    public final int tileSize = originalTileSize * scale;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenLength = tileSize * maxScreenRow;

    // FPS
    final int fps = 60;

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldHeight =  tileSize * maxWorldCol;
    public final int worldWidth =  tileSize * maxWorldRow;

    //manager
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Sound sound = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public SuperObject[] obj = new  SuperObject[10];
    public Player player = new Player(this, keyHandler);
    public AssetSetter assetSetter = new AssetSetter(this);

    
    // Game panel config
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenLength));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    // main thread call
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
    }
    // run function - called on thread
    @Override
    public void run() {

        double nanoSec = 1 * Math.pow(10, 9);
        double drawInterval = nanoSec / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= nanoSec) {
                System.out.println("FPS: " + drawCount);
                System.out.println("Direction: " + player.direction);

                drawCount = 0;
                timer = 0;
            }
        }
    }
    // update player if 
    public void update () {

        player.update();
    }
    // update screen
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        
        tileManager.draw(g2);
        for (int i = 0; i < obj.length ; i++) {
            if (obj[i] != null) {
                obj[i].draw(this, g2);
            }
        }
        player.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i) {
        
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
