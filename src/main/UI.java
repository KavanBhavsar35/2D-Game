package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.ObjKey;

public class UI {

    GamePanel gamePanel;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    int messageDuration = 2; //sec
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Dialog", Font.PLAIN, 40);
        arial_80B = new Font("Dialog", Font.BOLD, 80);
        ObjKey key = new ObjKey();
        keyImage = key.image;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {


        if (gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You Found the treasure !!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
            g2.drawString(text, x, y);


            text = "Your Time is " + decimalFormat.format(playTime) + "!!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "Congratulations";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 2);
            g2.drawString(text, x, y);
            gamePanel.gameThread =  null;
        } else {
            
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawString(" X " + gamePanel.player.hasKeys, 70, 65);

            //TIME
            playTime += (double)1/gamePanel.fps;
            g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.tileSize * (gamePanel.maxScreenRow - 1), gamePanel.tileSize * 1 + gamePanel.tileSize / 4);

            //MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gamePanel.tileSize * (gamePanel.maxScreenRow - 1) + gamePanel.tileSize / 2, gamePanel.tileSize * (gamePanel.maxScreenCol - 5) + gamePanel.tileSize / 2);
                
                messageCounter++;
                
                if (messageCounter > messageDuration * gamePanel.fps) {
                    messageCounter = 0;
                    messageOn = false;
                }

                
            }
        }
    }
}
