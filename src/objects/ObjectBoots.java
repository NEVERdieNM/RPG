package objects;

import main.GamePanel;

public class ObjectBoots extends SuperObject {

    public ObjectBoots(GamePanel gp){
        this.gp = gp;
        this.name = "boots";
        
        setup("boots");
    }
    
}
