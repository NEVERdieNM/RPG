package objects;

import main.GamePanel;

public class ObjectDoor extends SuperObject {

    public ObjectDoor(GamePanel gp){
        this.gp = gp;
        this.name = "door";

        setup("door");

        collsion = true;
    }

}