package main;

import object.ObjChest;
import object.ObjDoor;
import object.ObjKey;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new ObjKey();
        gamePanel.obj[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[1] = new ObjKey();
        gamePanel.obj[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.obj[2] = new ObjKey();
        gamePanel.obj[2].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[3] = new ObjDoor();
        gamePanel.obj[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 11 * gamePanel.tileSize;

        gamePanel.obj[4] = new ObjDoor();
        gamePanel.obj[4].worldX = 8 * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.obj[5] = new ObjDoor();
        gamePanel.obj[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.obj[5].worldY = 22 * gamePanel.tileSize;

        gamePanel.obj[6] = new ObjChest();
        gamePanel.obj[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.obj[6].worldY = 7 * gamePanel.tileSize;
    }
}
