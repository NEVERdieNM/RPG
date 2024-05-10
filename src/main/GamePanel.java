package main;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import objects.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    
    //SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    public final int SCALE = 3;

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile
    public final int MAX_SCREEN_COLUMNS = 16; //16
    public final int MAX_SCREEN_ROWS = 12; // 12
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS; // 768 pixels
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS; // 576 pixels

    // WORLD SETTIGNS
    public final int MAX_WORLD_COLUMNS = 50;
    public final int MAX_WORLD_ROWS = 50;
    public final int WORLD_WIDTH = MAX_WORLD_COLUMNS * TILE_SIZE; 
    public final int WORLD_HEIGHT = MAX_WORLD_ROWS * TILE_SIZE;

    //FPS
    public int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH;
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker collisionC  = new CollisionChecker(this);
    public AssetSetter assetS =  new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY'S AND OBJECT'S
    public Player player;
    public SuperObject[] obj = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //GAME STATE
    public int gameState;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 0;

    public GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        keyH = new KeyHandler(this);
        player = new Player(this, keyH);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){

        assetS.setObject();
        assetS.setNPC();
        playMusic(0);
        gameState = PLAY_STATE;

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void update(){

        if(gameState == PLAY_STATE){
            //PLAYER
            player.update();
            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        else if(gameState == PAUSE_STATE){

        }
        

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyH.showDrawTime){
            
            drawStart = System.nanoTime();
        }

        //TILES
        tileM.draw(g2);
        //OBJECTS
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //NPC
        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].draw(g2);
            }
        }
        //PLAYER
        player.draw(g2);
        //UI
        ui.draw(g2);
        

        //DEBUG

        long drawEnd = System.nanoTime();
        long drawTime = drawEnd - drawStart;

        if(keyH.showDrawTime){

            g2.setFont(getFont().deriveFont(20f));
            g2.setColor(Color.white);
            g2.drawString("Time: " + drawTime, TILE_SIZE, TILE_SIZE);
            System.out.println("drawTime: " + drawTime);
        }

        g2.dispose();

    }

    public void playMusic(int index){

        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int index){
        soundEffect.setFile(index);
        soundEffect.play();
    }


    // @Override
    // public void run() {

    //     double drawInterval = 1000000000/FPS;// 0.01666 seconds
    //     double nextDrawTime = System.nanoTime() + drawInterval;

    //     while(gameThread != null){

    //         // 1 UPDTATE: updtate the information such as character positions
    //         update();
    //         // 2 DRAW: draw the screen with the updtated information
    //         repaint();

    //         double remainingTime = nextDrawTime - System.nanoTime();
    //         remainingTime = remainingTime / 1000000; // Converted from nanoseconds to miliseconds

    //         if(remainingTime < 0)
    //             remainingTime = 0;

    //         try {
    //             Thread.sleep((long)remainingTime);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }

    //         nextDrawTime += drawInterval;
    //     }

    // }
 
    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            

            if(delta >= 1){
                update();
                repaint();
                delta--;

                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println(drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }
    
}
