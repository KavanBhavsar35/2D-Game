package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjChest extends SuperObject{

    public ObjChest(int x, int y) {
        // CONSTANTS
        String NAME = "Chest";
        String FILE_NAME = "chest.png"; 

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
