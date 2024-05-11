package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import java.text.DecimalFormat;

public class UI{

    /*------------------------------------------------------------ */

    GamePanel gp;
    Graphics2D g2;
    Font defaultFont;

    //MESSAGES
    public boolean messageOn;
    public String message = "";
    int messageCounter = 0;

    //DIALOGUE 
    public String currentDialogue = "";

    public boolean gameFinished = false;

    //DEBUG
    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    /*------------------------------------------------------------ */

    //CONSTRUCTOR
    public UI(GamePanel gp){
        this.gp = gp;
        
        //      CUSTOM FONT
        // try{

        //     InputStream ancientTalesFont = new FileInputStream(new File("res/fonts/ZenMasters-6YWRY.ttf"));
        //     defaultFont = Font.createFont(Font.TRUETYPE_FONT, ancientTalesFont);
        //     defaultFont = ancientTales.deriveFont(Font.PLAIN, 40f);

        // }catch(FontFormatException | IOException e){
        //     e.printStackTrace();
        // }
        
        defaultFont = new Font("Arial", Font.PLAIN, 40);

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(defaultFont);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //PLAY STATE
        if(gp.gameState == gp.PLAY_STATE) {

        }
        //PAUSE STATE
        else if(gp.gameState == gp.PAUSE_STATE) {

            drawPauseScreen();
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.DIALOGUE_STATE){
            drawDialogueScreen();
        }
    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));

        String text = "PAUSED";
        int textWidth = getXforXCeteredText(text);
        int x = gp.SCREEN_WIDTH/2 - textWidth/2;
        int y = gp.SCREEN_HEIGHT/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){

        //DIALOGUE WINDOW
        int x = gp.TILE_SIZE*2;
        int y = gp.TILE_SIZE/2;
        int width = gp.SCREEN_WIDTH - (gp.TILE_SIZE*4);
        int height = gp.TILE_SIZE*4;

        drawSubWindow(x, y, width, height);

        //DIALOGUE
        x += gp.TILE_SIZE;
        y += gp.TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color color = new Color(0, 0, 0, 205);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(220, 220, 220);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforXCeteredText(String text){
        int x = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return x;
    }

}