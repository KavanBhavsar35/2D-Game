package main;

import object.ObjBoots;
import object.ObjChest;
import object.ObjDoor;
import object.ObjKey;

public class AssetSetter {
    GamePanel gamePanel;

    // INITIATION
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // SETUP OBJECTS 
    public void setObject() {
        gamePanel.obj[0] = new ObjKey(23, 7);
        gamePanel.obj[1] = new ObjKey(23, 40);
        gamePanel.obj[2] = new ObjKey(37, 7);
        gamePanel.obj[3] = new ObjDoor(10, 11);
        gamePanel.obj[4] = new ObjDoor(8, 28);
        gamePanel.obj[5] = new ObjDoor(12, 22);
        gamePanel.obj[6] = new ObjChest(10, 7);
        gamePanel.obj[7] = new ObjBoots(37, 42);    
    }
}
