package com.kagaries.audio;

import com.kagaries.main.Game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
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
        if (Game.muted) {
            return;
        }
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

    public static void playDamageSound(AudioRegistry audioRegistry, float damage) {
        if (Game.muted) {
            return;
        }
        Thread thread = new Thread(() -> {
            try {
                AudioInputStream audioInputStream;

                InputStream inputStream = Game.getResourceLoader().getResourceAsStream(audioRegistry.getPath());

                audioInputStream = AudioSystem.getAudioInputStream(inputStream);
                AudioFormat originalFormat = audioInputStream.getFormat();

                float newSampleRate = originalFormat.getSampleRate() / (damage / 3);
                AudioFormat deepenedFormat = new AudioFormat(
                        originalFormat.getEncoding(),
                        newSampleRate,
                        originalFormat.getSampleSizeInBits(),
                        originalFormat.getChannels(),
                        originalFormat.getFrameSize(),
                        newSampleRate,
                        originalFormat.isBigEndian()
                );

                AudioInputStream deepenedAudioStream = AudioSystem.getAudioInputStream(deepenedFormat, audioInputStream);

                clip = AudioSystem.getClip();

                clip.open(deepenedAudioStream);

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