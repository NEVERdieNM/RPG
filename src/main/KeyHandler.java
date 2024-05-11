package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    
    //MOVEMENT
    public boolean wPressed, sPressed, aPressed, dPressed;
    //OTHER KEYS
    public boolean spacePressed;
    //DEBUG
    public boolean showCoordinates, showDrawTime;


    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }


    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if(gp.gameState == gp.PLAY_STATE){
            // MOVEMENT
            if(keyCode == KeyEvent.VK_W){
                wPressed = true;
            }
            if(keyCode == KeyEvent.VK_S){
                sPressed = true;
            }
            if(keyCode == KeyEvent.VK_A){
                aPressed = true;
            }
            if(keyCode == KeyEvent.VK_D){
                dPressed = true;
            }
            //INTERACT
            if(keyCode == KeyEvent.VK_SPACE){
                spacePressed = true;
            }
            // PAUSE GAME
            if(keyCode == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.PAUSE_STATE;
            }
            //DEBUG

            //DISPLAY COORDINATES
            if(keyCode == KeyEvent.VK_K){
                if(showCoordinates == false){
                    showCoordinates = true;
                }else if(showCoordinates == true){
                    showCoordinates = false;
                }
            }
            //DISPLAY DRAW TIME
            if(keyCode == KeyEvent.VK_L){
                if(showDrawTime == false){
                    showDrawTime = true;
                }else if(showDrawTime == true){
                    showDrawTime = false;
                }
            }
        }
        // PAUSE STATE
        else if(gp.gameState == gp.PAUSE_STATE){
            
            // RESUME GAME
            if(keyCode == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.PLAY_STATE;
            }
        }
        // DIALOGUES STATE
        else if(gp.gameState == gp.DIALOGUE_STATE){
            if(keyCode == KeyEvent.VK_SPACE){
                gp.gameState = gp.PLAY_STATE;
            }
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            wPressed = false;
        }
        if(keyCode == KeyEvent.VK_S){
            sPressed = false;
        }
        if(keyCode == KeyEvent.VK_A){
            aPressed = false;
        }
        if(keyCode == KeyEvent.VK_D){
            dPressed = false;
        }
        if(keyCode == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    

}
