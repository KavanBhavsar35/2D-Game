package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjBoots extends SuperObject{
    public ObjBoots(int x, int y) {

        // CONSTANTS
        String NAME = "Boots";
        String FILE_NAME = "boots.png"; 

        // INITIATIONS
        this.name = NAME;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/" + FILE_NAME));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
    }
}
