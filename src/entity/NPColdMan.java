package entity;

import java.util.Random;

import main.GamePanel;

public class NPColdMan extends Entity{
    
    public NPColdMan(GamePanel gamePanel, int x, int y) {
        this(gamePanel);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
    }

    public NPColdMan(GamePanel gamePanel) {
        super(gamePanel);
        
        direction = "down";
        speed = 1;

        this.getPlayerImage();
    }


    // LOAD IMAGES
    public void getPlayerImage() {
    
        up1 = setup("/npc/oldman_up_1.png");
        up2 = setup("/npc/oldman_up_2.png");
        down2 = setup("/npc/oldman_down_2.png");
        down1 = setup("/npc/oldman_down_1.png");
        left1 = setup("/npc/oldman_left_1.png");
        left2 = setup("/npc/oldman_left_2.png");
        right1 = setup("/npc/oldman_right_1.png");
        right2 = setup("/npc/oldman_right_2.png");
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // PICK A NUM FROM 1 - 100
            
            if (i <= 25) direction = "up";
            if (i >= 25 && i <=50) direction = "down";
            if (i >= 51 && i <=75) direction = "left";
            if (i >= 75 && i <=100) direction = "right";
            
            actionLockCounter = 0;
        }
    }

}