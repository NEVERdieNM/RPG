package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objects.ObjectHeart;
import objects.SuperObject;


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

    //DEBUG
    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    //MAIN MENU
    public int selectedOption = 0;
    public int MainMenuScreenState = 0; // 0: first screen; 1: second screen;

    //IMAGES 
    BufferedImage heart_full, heart_half, heart_blank;

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
        
        // HUD OBJECTS
        SuperObject heart = new ObjectHeart(gp);

        // HUD IMAGES
        heart_full = heart.image;
        heart_half =  heart.image2;
        heart_blank = heart.image3;
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

        //MAIN MENU STATE
        if(gp.gameState == gp.MAIN_MENU_STATE){
            drawMainMenuScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.PLAY_STATE) {
            drawPlayerHealth();
        }
        //PAUSE STATE
        else if(gp.gameState == gp.PAUSE_STATE) {

            drawPauseScreen();
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.DIALOGUE_STATE){
            drawPlayerHealth();
            drawDialogueScreen();
        }
    }

    public void drawPlayerHealth(){

        int x = gp.TILE_SIZE/2;
        int y = gp.TILE_SIZE/2;

        //DRAW BLANK HEARTS
        int i = 0;
        while(i < gp.player.maxHealth/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.TILE_SIZE;
        }
        
        x = gp.TILE_SIZE/2;
        //DRAW CURRENT HEALTH
        i = 0;
        while(i < gp.player.health){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.health){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.TILE_SIZE;
        }
    }

    public void drawMainMenuScreen(){

        //  TITLE SCREEN
        if(MainMenuScreenState == 0){
            
            g2.setColor(new Color(70,120,80));
            g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);

            String text;

            //GAME TITLE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70f));
            text = "Blue Boy Adventure";
            int x = getXforXCeteredText(text);
            int y = gp.TILE_SIZE*3;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            
            //PLAYER IMAGE
            x = gp.SCREEN_WIDTH/2;
            y += gp.TILE_SIZE*2;
            g2.drawImage(gp.player.down1, x - (gp.TILE_SIZE*2)/2, y, gp.TILE_SIZE*2, gp.TILE_SIZE*2, null);

            //  ~   MENU    ~
            
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));

            // NEW GAME
            text = "New Game";
            x = getXforXCeteredText(text);
            y += gp.TILE_SIZE*4;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(selectedOption == 0){
                g2.drawString(">", x - gp.TILE_SIZE, y);
            }

            // LOAD GAME
            text = "Load game";
            x = getXforXCeteredText(text);
            y += gp.TILE_SIZE + gp.TILE_SIZE/2;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(selectedOption == 1){
                g2.drawString(">", x - gp.TILE_SIZE, y);
            }

            // QUIT
            text = "Quit";
            x = gp.TILE_SIZE/2;
            y += gp.TILE_SIZE;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(selectedOption == 2){
                g2.drawString("<", x + gp.TILE_SIZE*2 + gp.TILE_SIZE/2, y);
            }
        }
        else if(MainMenuScreenState == 1){

            String text;

            //CLASS SELECTION SCREEN
            g2.setColor(new Color(70,120,80));
            g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 58f));

            text = "Select your class!";
            int x = getXforXCeteredText(text);
            int y = gp.TILE_SIZE*3;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(48f));

            text = "Fighter";
            x = getXforXCeteredText(text);
            y += gp.TILE_SIZE*3;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(selectedOption == 0){
                g2.drawString(">", x - gp.TILE_SIZE, y);
            }

            text = "Thief";
            x = getXforXCeteredText(text);
            y += gp.TILE_SIZE + gp.TILE_SIZE/4;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(selectedOption == 1){
                g2.drawString(">", x - gp.TILE_SIZE, y);
            }

            text = "Wizard";
            x = getXforXCeteredText(text);
            y += gp.TILE_SIZE + gp.TILE_SIZE/4;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(selectedOption == 2){
                g2.drawString(">", x - gp.TILE_SIZE, y);
            }

            text = "back";
            x = getXforXCeteredText(text);
            y += gp.TILE_SIZE*2;
            //SHADOW TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(selectedOption == 3){
                g2.drawString(">", x - gp.TILE_SIZE, y);
            }
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
        int textWidth = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int centeredX = gp.SCREEN_WIDTH/2 - textWidth/2;
        return centeredX;
    }

}