package graviwave.GameDesign;

import graviwave.GameDesign.DesignComponents.SoundOf;

import java.util.Timer;
import java.util.TimerTask;

public class MusicalArrangement {
    private SoundOf start;
    private SoundOf backgroundMenu;
    private SoundOf click;
    private SoundOf game;

    MusicalArrangement()throws Exception{
        this.start = new SoundOf("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\music\\play.mp3");
        this.backgroundMenu = new SoundOf("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\music\\background.mp3");
        this.click = new SoundOf("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\music\\click.mp3");
        backgroundMenu.getPlayer().play();
        this.game = new SoundOf("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\music\\game.mp3");
        game.getPlayer().play();
        game.getPlayer().pause();

    }

    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            game.getPlayer().play();
            timer.cancel();
        }
    };

    public void playPlayerPlay(){
        backgroundMenu.getPlayer().stop();
        start.getPlayer().play();
        timer.schedule(timerTask, 3000);

    }


    public SoundOf getClick() {
        return click;
    }

    public SoundOf getBackgroundMenu() {
        return backgroundMenu;
    }

    public SoundOf getGame() {
        return game;
    }



}
