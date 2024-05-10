package objects;

import main.GamePanel;

public class ObjectChest extends SuperObject {
    
    public ObjectChest(GamePanel gp){
        this.gp = gp;
        this.name = "chest";

        setup("chest");
    }

}
