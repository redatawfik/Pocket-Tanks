package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Sound {

    private static Clip backGroundAudioClip;
    private static AudioInputStream backgroundAudioStream;

    public static void playBackgroundSound(String path) {


        if (backGroundAudioClip != null) {
            backGroundAudioClip.stop();
        }

        if (backgroundAudioStream != null) {
            try {
                backgroundAudioStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Create an AudioInputStream from a given sound file
        URL menuSoundPath = Sound.class.getResource(path);

        System.out.println();

        try {
            File audioFile = new File(menuSoundPath.toURI());

            backgroundAudioStream = AudioSystem.getAudioInputStream(audioFile);

            // Acquire audio format and create a DataLine.Infoobject
            AudioFormat format = backgroundAudioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Obtain the Clip
            backGroundAudioClip = (Clip) AudioSystem.getLine(info);

            // Open the AudioInputStream and start playing
            //backGroundAudioClip.addLineListener(this);

            backGroundAudioClip.open(backgroundAudioStream);

            backGroundAudioClip.loop(Clip.LOOP_CONTINUOUSLY);

            backGroundAudioClip.start();

            // Close and release resources acquired
            //audioClip.close();
            //audioStream.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static void playSound(String path) {

        URL menuSoundPath = Sound.class.getResource(path);

        try {
            File audioFile = new File(menuSoundPath.toURI());

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);

            audioClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
