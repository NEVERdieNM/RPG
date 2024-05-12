package objects;

import main.GamePanel;

public class ObjectHeart extends SuperObject{

    public ObjectHeart(GamePanel gp){
        this.gp = gp;
        this.name = "heart";

        setup("heart_full", "heart_half", "heart_blank");
    }
} 