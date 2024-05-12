package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 1;

        getImage();
        setDialogue();
        // solidArea = new Rectangle(12, 24, 24, 24);
        // solidAreaDefaultX = solidArea.x;
        // solidAreaDefaultY = solidArea.y;
    }

    public void getImage(){

        up1 = setup("npc/oldman_up_1");
        up2 = setup("npc/oldman_up_2");
        down1 = setup("npc/oldman_down_1");
        down2 = setup("npc/oldman_down_2");
        left1 = setup("npc/oldman_left_1");
        left2 = setup("npc/oldman_left_2");
        right1 = setup("npc/oldman_right_1");
        right2 = setup("npc/oldman_right_2");
    }

    @Override
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "up";
            }else if(i <= 50){
                direction = "down";
            }else if(i <= 75){
                direction = "left";
            }else if(i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void setDialogue(){

        dialogues[0] = "Greetings, fellow traveler.";
        dialogues[1] = "So you've come to this island to find \nthe treasure?";
        dialogues[2] = "I used to be a great wizard, but now...\nI'm a bit too old for taking an adveture.";
        dialogues[3] = "Well, I wish you much luck traveler.";
    }

    public void speak(){
        
        super.speak();
    }
}