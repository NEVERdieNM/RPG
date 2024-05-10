package objects;

import main.GamePanel;

public class ObjectKey extends SuperObject{
    
    public ObjectKey(GamePanel gp){
        this.gp = gp;
        this.name = "key";
        
        setup("key");
    }

}
