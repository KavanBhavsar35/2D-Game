package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    // CONSTRUCTOR
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // CHECK IF COLLISION WITH TILE
    public void checkTile(Entity entity) {

        // GET ENTITY'S CO-ORDINATES
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // GET ENTITY'S BLOCK COL AND ROW
        int entityLeftCol = entityLeftWorldX / GamePanel.tileSize;
        int entityRightCol = entityRightWorldX / GamePanel.tileSize;
        int entityTopRow = entityTopWorldY / GamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / GamePanel.tileSize;

        int tileNum1 = -1, tileNum2 = -1;

        // FIND POSSIBLE COLLIDING BLOCKS
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / GamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];

                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / GamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / GamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];

                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / GamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                break;
        }
        
        // CHECK IF COLLIDING
        if (gamePanel.tileManager.tile[tileNum1].collision == true ||
                gamePanel.tileManager.tile[tileNum2].collision == true) {
            entity.collionOn = true;
        }
    }

    // CHECK IF ENTITY IS COLLIDING WITH OBJECT
    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gamePanel.obj.length; i++) {

            if (gamePanel.obj[i] != null) {

                // GET ENTITY'S SOLID AREA POSITION
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                // GET OBJECTS'S SOLID AREA POSITION
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y;

                // PREDICT ENTITY'S MOVEMENT
                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                // CHECK IF BOTH COLLIDES
                if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {

                    // PREVENT ENTITY FROM MOVING  
                    if (gamePanel.obj[i].collision) {
                        entity.collionOn = true;
                    }

                    //CHECK IF PLAYER
                    if (isPlayer) {
                        index = i;
                    }
                }

                //RESET ENTITY'S SOLID AREA
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                
                //RESET OBJECT'S SOLID AREA
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
