package main;

import entity.NPColdMan;

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
        
        gamePanel.npc[0] = new NPColdMan(gamePanel, 21, 21);//TODO construcor
    }
}
