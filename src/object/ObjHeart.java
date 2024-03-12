package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class ObjHeart extends SuperObject {

    public ObjHeart() {
        // CONSTANTS
        String name = "Heart";
        String fileName1 = "heart_full.png"; 
        String fileName2 = "heart_half.png"; 
        String fileName3 = "heart_blank.png"; 

        // INITIATION
        this.name = name;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/" + fileName1));
            this.image2 = ImageIO.read(getClass().getResourceAsStream("/objects/" + fileName2));
            this.image3 = ImageIO.read(getClass().getResourceAsStream("/objects/" + fileName3));
            this.image = UtilityTool.scaleImage(image, GamePanel.tileSize, GamePanel.tileSize);
            this.image2 = UtilityTool.scaleImage(image2, GamePanel.tileSize, GamePanel.tileSize);
            this.image3 = UtilityTool.scaleImage(image3, GamePanel.tileSize, GamePanel.tileSize);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
