package mainGame.gfx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MusicPlayer {

    private MusicPlayer(){}

    public static void initJavaFx(){
        // Init JavaFX for music
        new JFXPanel();
    }

    public static MediaPlayer initMediaPlayer(String sound) {
        URL file = MusicPlayer.class.getResource(sound);
        final Media media = new Media(file.toString());
        return new MediaPlayer(media);
    }

}
