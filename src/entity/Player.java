package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


import main.GamePanel;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int hasKeys = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenLength / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        this.setDefaultValue();
        this.getPlayerImage();
    }

    public void setDefaultValue() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            System.out.println("errror" + e);
            e.printStackTrace();
        }
    }

    // update player if
    public void update() {
    	
    	if (keyHandler.upPressed == true || keyHandler.downPressed == true || 
    			keyHandler.leftPressed == true || keyHandler.rightPressed == true) {

	        if (keyHandler.upPressed == true)  direction = "up"; 
            else if (keyHandler.downPressed == true)  direction = "down"; 
            else if (keyHandler.leftPressed == true)  direction = "left"; 
            else if (keyHandler.rightPressed == true)  direction = "right"; 
            
            //checl if collide 
            collionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            //checl object collision
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // IF collison is move plaery can move

            if (collionOn == false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }


	        spriteCounter++;
	        if (spriteCounter > 12) {
	        	if (spriteNum == 1) spriteNum = 2; 
	        	else if (spriteNum == 2) spriteNum = 1; 
	        	spriteCounter = 0;
	        }
    	}
    }

    public void pickUpObject(int objIndex) {
        if (objIndex != 999) {
            String objName = gamePanel.obj[objIndex].name;
            switch (objName) {
                case "Key":
                    gamePanel.playSoundEffect(1);
                    hasKeys++;
                    gamePanel.obj[objIndex] = null;
                    System.out.println("Keys: " + hasKeys);
                    break;
                case "Door":
                    gamePanel.playSoundEffect(3);
                    if (hasKeys > 0) {
                        gamePanel.obj[objIndex] = null;
                        hasKeys--;
                    }
                    System.out.println("Keys: " + hasKeys);
                    break;
                case "Boots":
                    gamePanel.playSoundEffect(2);
                    speed += 1;
                    gamePanel.obj[objIndex] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) { image = up1; }
                if (spriteNum == 2) { image = up2; }
                break;
            case "down":
                if (spriteNum == 1) { image = down1; }
                if (spriteNum == 2) { image = down2; }
                break;
            case "left":
                if (spriteNum == 1) { image = left1; }
                if (spriteNum == 2) { image = left2; }
                break;
            case "right":
                if (spriteNum == 1) { image = right1; }
                if (spriteNum == 2) { image = right2; }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
