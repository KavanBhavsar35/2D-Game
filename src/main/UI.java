package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    // INITIATION
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    int messageDuration = 2; // sec
    public boolean gameFinished = false;
    public String currentDialouge = "";

    public UI(GamePanel gamePanel) {

        // UI SETTINGS
        this.gamePanel = gamePanel;
        arial_40 = new Font("Dialog", Font.PLAIN, 40);
        arial_80B = new Font("Dialog", Font.BOLD, 80);
    }

    // ACCESS FUNCTIONS
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    // RENDERING MESSAGES
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gamePanel.gameState == gamePanel.playState) {
            // DO STUFF
        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            this.drawPauseScreen();
        }
        if (gamePanel.gameState == gamePanel.dialougeState) {
            this.drawDialougeState();
        }
    }

    private void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "Paused";
        int x;
        x = UtilityTool.getXForCenteredText(g2, text);
        int y = GamePanel.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    private void drawDialougeState() {

        // WINDOW
        int x = GamePanel.tileSize * 2;
        int y = GamePanel.tileSize / 2;
        int width = GamePanel.screenWidth - (GamePanel.tileSize * 4);
        int height = GamePanel.tileSize * 5;

        drawSubWindow(x, y, width, height);

        x += GamePanel.tileSize;
        y += GamePanel.tileSize;
        g2.setFont(g2.getFont().deriveFont(30f));

        for (String line : currentDialouge.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }

    // SUBWINDOW
    public void drawSubWindow(int x, int y, int width, int height) {

        Color color = new Color(0, 0, 0, 200);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255, 230);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}