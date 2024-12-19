package com.kagaries.audio;

import com.kagaries.main.Game;
import com.kagaries.main.ResourceLoader;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SimpleAudioPlayer {
    public static void playSound(String path) {
       new Thread(() -> {
           InputStream inputStream = Game.getResourceLoader().getResourceAsStream(path);

           AudioInputStream audioInputStream =
                   null;
           try {
               audioInputStream = AudioSystem.getAudioInputStream(inputStream);
           } catch (UnsupportedAudioFileException | IOException e) {
               throw new RuntimeException(e);
           }

           Clip clip;
           try {
               clip = AudioSystem.getClip();
           } catch (LineUnavailableException e) {
               throw new RuntimeException(e);
           }

           clip.flush();

           try {
               clip.open(audioInputStream);
           } catch (LineUnavailableException | IOException e) {
               throw new RuntimeException(e);
           }

           FloatControl gainControl =
                   (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
           gainControl.setValue(-10.0f);

           clip.start();
       }).start();
    }
}
