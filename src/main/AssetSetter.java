package main;

import entity.NPC_OldMan;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;

    }

    public void setObject(){
        
    }

    public void setNPC(){

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = 21*gp.TILE_SIZE;
        gp.npc[0].worldY = 21*gp.TILE_SIZE;
    }

}