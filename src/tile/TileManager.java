package tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[42];
        mapTileNum = new int[gp.MAX_WORLD_ROWS][gp.MAX_WORLD_COLUMNS];
        getTileImage();
        loadMap("res/maps/worldV2.txt");
        

    }

    public void getTileImage(){
        
        //PLACEHOLDERS
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);

        //TILES
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);

        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);

        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
    }

    public void setup(int index, String imageName, boolean collsion){
        
        UtilityTool uTool = new UtilityTool();

        try {
            BufferedImage scaledImage = uTool.scaleImage(ImageIO.read(new File("res/tiles/" + imageName + ".png")), gp.TILE_SIZE, gp.TILE_SIZE);

            tile[index] = new Tile();
            tile[index].image = scaledImage;
            tile[index].collsion = collsion;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFile){

        try {
            
            File mapToLoad = new File(mapFile);
            FileReader reader = new FileReader(mapToLoad);
            BufferedReader bReader = new BufferedReader(reader);

            int column = 0;
            int row = 0;

            while(column < gp.MAX_WORLD_COLUMNS && row < gp.MAX_WORLD_ROWS){

                String line = bReader.readLine();
                String[] numbersOnRow = line.split(" ");
                
                while(column < gp.MAX_WORLD_COLUMNS){

                    int num = Integer.parseInt(numbersOnRow[column]);
                    mapTileNum[row][column] = num;
                    column++;

                }
                
                row++;
                column = 0;

            }

            bReader.close();
            reader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){

        int worldColumn = 0;
        int worldRow = 0;

        while(worldColumn < gp.MAX_WORLD_COLUMNS && worldRow < gp.MAX_WORLD_ROWS){

            int tileNum = mapTileNum[worldRow][worldColumn];

            int worldX = worldColumn * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;

            int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;
            int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

            if(worldX > gp.player.worldX - gp.player.SCREEN_X - gp.TILE_SIZE && worldX < gp.player.worldX + gp.player.SCREEN_X + gp.TILE_SIZE &&
               worldY > gp.player.worldY - gp.player.SCREEN_Y  - gp.TILE_SIZE && worldY < gp.player.worldY + gp.player.SCREEN_Y + gp.TILE_SIZE){
                
                //DRAW TILE
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

                //DRAW TILE COORDINATES
                if(gp.keyH.showCoordinates){
                    g2.setColor(Color.red);
                    g2.setFont(new Font("classic", Font.BOLD, 15));

                    g2.drawString(Integer.toString(worldX / gp.TILE_SIZE) + "|" + 
                    Integer.toString(worldY / gp.TILE_SIZE), screenX + 5, screenY - 20 + gp.TILE_SIZE);
                }

               }
            
            worldColumn++;

            if(worldColumn == gp.MAX_WORLD_COLUMNS){
                worldColumn = 0;
                worldRow++;
            }
        }

    }

}
