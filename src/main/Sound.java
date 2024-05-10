package main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL[]  soundURL = new URL[30];

    public Sound(){
        try{

            soundURL[0] = new File("res/sounds/BlueBoyAdventure.wav").toURI().toURL();
            soundURL[1] = new File("res/sounds/coin.wav").toURI().toURL();
            soundURL[2] = new File("res/sounds/powerup.wav").toURI().toURL();
            soundURL[3] = new File("res/sounds/unlock.wav").toURI().toURL();
            soundURL[4] = new File("res/sounds/fanfare.wav").toURI().toURL();

        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }

    public void setFile(int index){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

}
