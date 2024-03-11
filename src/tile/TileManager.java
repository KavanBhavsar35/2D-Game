//PACKAGE DECLARATION
package tile;

//STANDARD LIBRARY CLASSES
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//EXTENDED LIBRARY CLASSES
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    // CONSTANTS
    int MAX_TILES = 10;
    String MAP_FILE_NAME = "world01.txt";
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {

        // INITIATION
        this.gamePanel = gamePanel;
        tile = new Tile[MAX_TILES];
        mapTileNumber = new int[GamePanel.maxWorldCol][GamePanel.maxWorldRow];

        // LOADING RESPECTIVE TILES ACCORDING TO MAP
        this.getTileImage();
        this.loadMap("/maps/" + MAP_FILE_NAME);
    }

    // LOAD MAP
    public void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < GamePanel.maxWorldCol && row < GamePanel.maxWorldRow) {

                String line = bufferedReader.readLine();

                while (col < GamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;

                    col++;
                }
                if (col == GamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // LOAD IMAGES
    public void getTileImage() {
        String[] TILE_NAMES = { "grass.png", "wall.png", "water.png", "earth.png", "tree.png", "sand.png" };
        boolean[] COLLISION = { false, true, true, false, true, false };

        for (int i = 0; i < TILE_NAMES.length; i++) {

            try {
                tile[i] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/" + TILE_NAMES[i])), COLLISION[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    // CONDITIONAL RENDERING FOR PLAYER VIEW GRID
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < GamePanel.maxWorldCol && worldRow < GamePanel.maxWorldRow) {

            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * GamePanel.tileSize;
            int worldY = worldRow * GamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + GamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - GamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + GamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - GamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == GamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
