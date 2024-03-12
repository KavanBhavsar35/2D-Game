package main;

import entity.NpcOldMan;

public class AssetSetter {
    GamePanel gamePanel;

    // INITIATION
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // SETUP OBJECTS 
    public void setObject() {
          
    }

    //SETUP NPCs
    public void setNPC() {
        
        gamePanel.npc[0] = new NpcOldMan(gamePanel, 21, 21);
    }
}
