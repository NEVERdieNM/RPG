package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI{

    GamePanel gp;
    Graphics2D g2;
    Font arial40;

    public boolean messageOn;
    public String message = "";
    int messageCounter = 0;

    public boolean gameFinished = false;

    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){

        this.gp = gp;
        arial40 = new Font("Arial", Font.BOLD, 40);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.PLAY_STATE) {

        }
        else if(gp.gameState == gp.PAUSE_STATE) {

            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));

        String text = "PAUSED";
        int textWidth = getXforXCeteredText(text);
        int x = gp.SCREEN_WIDTH/2 - textWidth/2;
        int y = gp.SCREEN_HEIGHT/2;

        g2.drawString(text, x, y);
    }

    public int getXforXCeteredText(String text){
        int x = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return x;
    }

}