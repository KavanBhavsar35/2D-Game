package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

    // SCALE IMAGE
    public static final BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    // GET TEXT LENGTH
    public static final int textLength(Graphics2D g2, String text) {

        return (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }
}
