package com.kagaries.audio;

import com.kagaries.main.Game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class SimpleAudioPlayer {
    static Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playSound(AudioRegistry audioRegistry) {
       Thread thread = new Thread(() -> {
           try {
               AudioInputStream audioInputStream;

               InputStream inputStream = Game.getResourceLoader().getResourceAsStream(audioRegistry.getPath());

               audioInputStream = AudioSystem.getAudioInputStream(inputStream);

               clip = AudioSystem.getClip();

               clip.open(audioInputStream);

               FloatControl gainControl =
                       (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
               gainControl.setValue(audioRegistry.getGain());

               clip.start();

               stopNearEnd(clip, audioRegistry);
           } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
               throw new RuntimeException(e);
           }
       });

       thread.setName("Sound-Thread" + UUID.randomUUID());
       thread.start();
    }

    private static void stopNearEnd(Clip clip, AudioRegistry audioRegistry) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (clip.getFramePosition() > clip.getFrameLength() * audioRegistry.getCutOff()) {
                    timer.cancel();
                    clip.stop();
                }
            }
        }, 0, 1);
    }
}
