package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


import main.GamePanel;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenLength / 2 - (gamePanel.tileSize / 2);

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
	        if (keyHandler.upPressed == true) {
	            direction = "up";
	            worldY -= speed;
	        } else if (keyHandler.downPressed == true) {
	            direction = "down";
	            worldY += speed;
	        } else if (keyHandler.leftPressed == true) {
	            direction = "left";
	            worldX -= speed;
	        } else if (keyHandler.rightPressed == true) {
	            direction = "right";
	            worldX += speed;
	        }
	        spriteCounter++;
	        if (spriteCounter > 12) {
	        	if (spriteNum == 1) {
	        		spriteNum = 2;
	        	}
	        	else if (spriteNum == 2) {
	        		spriteNum = 1;
	        	}
	        	spriteCounter = 0;
	        }
    	}
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gamePanel.titleSize, gamePanel.titleSize);

        BufferedImage image = null;

        switch (direction) {
        case "up":
            if (spriteNum == 1) {
                image = up1;     
            }
            if (spriteNum == 2) {
                image = up2;   
            }
            break;
        case "down":
            if (spriteNum == 1) {
                image = down1;     
            }
            if (spriteNum == 2) {
                image = down2;   
            }
            break;
        case "left":
            if (spriteNum == 1) {
                image = left1;     
            }
            if (spriteNum == 2) {
                image = left2;   
            }
            break;
        case "right":
            if (spriteNum == 1) {
                image = right1;     
            }
            if (spriteNum == 2) {
                image = right2;   
            }
            break;
        default:
            break;
    }

    
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}