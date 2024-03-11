package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;


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

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

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
    }

    private void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "Paused";
        int x;
        x = UtilityTool.getXForCenteredText(g2, text);
        int y = GamePanel.screenHeight / 2;

        g2.drawString(text, x, y);

    }
}
