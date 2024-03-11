package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjKey extends SuperObject{

    public ObjKey() {
        // CONSTANTS
        String NAME = "Key";
        String FILE_NAME = "key.png"; 
    
        // INITIATIONS
        this.name = NAME;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/" + FILE_NAME));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    public ObjKey(int x, int y) {
        this();
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
    }

}
