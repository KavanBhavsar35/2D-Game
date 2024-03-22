package main;

import entity.NpcOldMan;
import monster.MonGreenSlime;

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
        
        // gamePanel.npc[0] = new NpcOldMan(gamePanel, 21, 21);
        gamePanel.npc[0] = new NpcOldMan(gamePanel, 9, 10);
    }

    public void setMonster() {
        gamePanel.monster[0] = new MonGreenSlime(gamePanel, 11, 10);
        // gamePanel.monster[0] = new MonGreenSlime(gamePanel, 23, 36);
        gamePanel.monster[1] = new MonGreenSlime(gamePanel, 11, 11);
        // gamePanel.monster[1] = new MonGreenSlime(gamePanel, 23, 37);

    }
}
