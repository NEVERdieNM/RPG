package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
    
    public BufferedImage image;
    public String name;
    public boolean collsion = false;

    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public GamePanel gp;


    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = this.worldX - gp.player.worldX + gp.player.SCREEN_X;
        int screenY = this.worldY - gp.player.worldY +  gp.player.SCREEN_Y;

        if(worldX > gp.player.worldX - gp.player.SCREEN_X - gp.TILE_SIZE && worldX < gp.player.worldX + gp.player.SCREEN_X + gp.TILE_SIZE &&
           worldY > gp.player.worldY - gp.player.SCREEN_Y - gp.TILE_SIZE && worldY < gp.player.worldY + gp.player.SCREEN_Y + gp.TILE_SIZE)
        g2.drawImage(this.image, screenX, screenY, null);

    }

    public void setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try{

            scaledImage = uTool.scaleImage(ImageIO.read(new File("res/objects/" + imageName + ".png")), gp.TILE_SIZE, gp.TILE_SIZE);

        }catch(IOException e){
            e.printStackTrace();
        }

        this.image = scaledImage;
    }

}
