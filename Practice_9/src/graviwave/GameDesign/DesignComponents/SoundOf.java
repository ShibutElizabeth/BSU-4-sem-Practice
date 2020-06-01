package graviwave.GameDesign.DesignComponents;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundOf {
    private MediaPlayer player;

    public SoundOf(String path)throws Exception{
        File file = new File(path);
        Media music = new Media(file.toURI().toURL().toString());
        this.player = new MediaPlayer(music);
    }

    public MediaPlayer getPlayer() {
        return player;
    }

}
