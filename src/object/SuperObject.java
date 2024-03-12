// PACKAGE DECLARATIONS
package object;

//STANDARD LIBRARY CLASSES
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//PACKAGE CLASSES
import main.GamePanel;

//CONSTRUCTOR
public class SuperObject {

    //INITATION
    GamePanel gamePanel;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public Rectangle solidArea = new Rectangle(0, 0, GamePanel.tileSize, GamePanel.tileSize);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    // OBJECT RENDERING
    public void draw(GamePanel gamePanel, Graphics2D g2) {

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        // CONDITIONAL OBJECT RENDERING 
        if (worldX + GamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - GamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + GamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - GamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            g2.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }
}
