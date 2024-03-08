package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        this.getTileImage();
        this.loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")), false);
            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")), true);
            tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")), true);
            tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")), false);
            tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")), false); //TODO coorect this
            tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")), false);
        } catch (Exception e) {

            System.out.println("Error: " + e);
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

                String line = bufferedReader.readLine();

                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;

                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
