package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
    
    /*------------------------------------------------------------ */

    GamePanel gp;

    //PLAYER INFO
    public int worldX, worldY;
    public int speed;
    //PLAYER HEALTH
    public int maxHealth;
    public int health;

    //SPRITES
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    //SPRITE ANIMATIONS
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public String direction;

    //HITBOX
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;  

    //NPC ACTION COOLDOWN
    public int actionLockCounter;

    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    /*------------------------------------------------------------ */

    public Entity(GamePanel gp){

        this.gp = gp;
    }

    public BufferedImage setup(String imagePath){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try{

        scaledImage = uTool.scaleImage(ImageIO.read(new File("res/" + imagePath + ".png")), gp.TILE_SIZE, gp.TILE_SIZE);

        }catch(IOException e){
            e.printStackTrace();
        }

        return scaledImage;
    }

    public void setAction() {}

    //NPC DIALOGUE
    public void speak() {

        //RESET DIALOGUE
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        
        //PASS NEXT DIALOGUE
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        //TURN NPC TO PLAYER
        switch(gp.player.direction){
            case "up":
                direction = "down";
            break;

            case "down":
                direction = "up";
            break;

            case "right":
                direction = "left";
            break;

            case "left":
                direction = "right";
            break;
        }
    }
    
    public void update() {

        setAction();

        //COLLSIONS
        collisionOn = false;
        
        //TILE COLLISION
        gp.collisionC.checkTile(this);

        //PLAYER COLLISION
        gp.collisionC.checkPlayer(this);

        //OBJECT COLLSION
        gp.collisionC.checkObject(this, false);

        if(collisionOn == false){

            switch (direction) {
                case "up":
                    this.worldY -= this.speed;
                    break;
                case "down":
                    this.worldY += this.speed;
                    break;
                case "left":
                    this.worldX -= this.speed;
                    break;
                case "right":
                    this.worldX += this.speed;
                    break;

            }
        }

        spriteCounter++;
        if(spriteCounter >= 14){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = this.worldX - gp.player.worldX + gp.player.SCREEN_X;
        int screenY = this.worldY - gp.player.worldY +  gp.player.SCREEN_Y;

        if(worldX > gp.player.worldX - gp.player.SCREEN_X - gp.TILE_SIZE && worldX < gp.player.worldX + gp.player.SCREEN_X + gp.TILE_SIZE &&
           worldY > gp.player.worldY - gp.player.SCREEN_Y - gp.TILE_SIZE && worldY < gp.player.worldY + gp.player.SCREEN_Y + gp.TILE_SIZE){
            switch (direction) {

                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    else if(spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down": 
                    if(spriteNum == 1){
                        image = down1;
                    }
                    else if(spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    else if(spriteNum == 2){
                        image = right2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    else if(spriteNum == 2){
                        image = left2;
                    }
                    break;
    
            }
            g2.drawImage(image, screenX, screenY, null);
           }
        

    }
}
