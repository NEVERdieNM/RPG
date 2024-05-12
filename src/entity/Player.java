package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int SCREEN_X, SCREEN_Y;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);

        this.keyH = keyH;

        SCREEN_X = gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2);
        SCREEN_Y = gp.SCREEN_HEIGHT/2 - (gp.TILE_SIZE/2);

        solidArea = new Rectangle(16, 28, 16, 16);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){

        this.worldX = 23 * gp.TILE_SIZE;
        this.worldY = 21 * gp.TILE_SIZE;
        this.speed = 3 * gp.SCALE / 3;
        this.direction = "down";

        //PLAYER HEALTH 
        maxHealth = 6;
        health = maxHealth;
    }

    public void getPlayerImage(){

        up1 = setup("player/boy_up_1");
        up2 = setup("player/boy_up_2");
        down1 = setup("player/boy_down_1");
        down2 = setup("player/boy_down_2");
        left1 = setup("player/boy_left_1");
        left2 = setup("player/boy_left_2");
        right1 = setup("player/boy_right_1");
        right2 = setup("player/boy_right_2");
    }

    public void update(){

        if(keyH.wPressed || keyH.sPressed || keyH.aPressed || keyH.dPressed){

            if(keyH.wPressed){
                direction = "up";
            }
            else if(keyH.sPressed){
                direction = "down";
            }
            else if(keyH.aPressed){
                direction = "left";
            }
            else if(keyH.dPressed){
                direction = "right";
            }
            
            //COLLISIONS
            collisionOn = false;

            //CHECK TILE COLLISION
            gp.collisionC.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.collisionC.checkObject(this, true);
            interactWithObject(objIndex);

            //CHECK COLLSION WITH NPC
            int npcIndex = gp.collisionC.checkEntity(this, gp.npc);
            interactWithNPC(npcIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;
            gp.keyH.spacePressed = false;

            //IF COLLSION IS FALSE PLAYER CAN MOVE 
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

    }

    public void interactWithObject(int index){

        if(index != 999){
            
        }
    }

    public void interactWithNPC(int index){

        if(index != 999){

            if(keyH.spacePressed){
            
                gp.gameState = gp.DIALOGUE_STATE;
                gp.npc[index].speak();
            }
        }
    }

    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

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

        g2.drawImage(image, SCREEN_X, SCREEN_Y, null);

    }
}
