package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjDoor extends SuperObject {

    public ObjDoor(int x, int y) {
        // CONSTANTS
        String NAME = "Door";
        String FILE_NAME = "door.png"; 
        boolean COLLISION = true;

        // INITIATION
        this.name = NAME;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/" + FILE_NAME));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        this.collision = COLLISION;
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
    }
}
