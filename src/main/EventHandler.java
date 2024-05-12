package main;

import java.awt.Rectangle;

public class EventHandler {

    GamePanel gp;

    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle(15, 15, 18, 18);
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {

        if(hit(27,16,"right") == true){
            teleport(23, 21, gp.DIALOGUE_STATE);
        }
        if(hit(23,12,"up") == true){
            healingPool(gp.DIALOGUE_STATE);
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.TILE_SIZE + eventRect.x;
        eventRect.y = eventRow * gp.TILE_SIZE + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.equals(reqDirection) || reqDirection.equals("any")){
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit";
        gp.player.health--;
    }

    public void healingPool(int gameState) {

        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drank water from the pool,\nyour health has been restored";
            gp.player.health = gp.player.maxHealth;
        }
    }

    public void teleport(int x, int y, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleported";
        gp.player.worldX = x*gp.TILE_SIZE;
        gp.player.worldY = y*gp.TILE_SIZE;
    }
}
