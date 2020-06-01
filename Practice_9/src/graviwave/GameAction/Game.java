package graviwave.GameAction;

import graviwave.GameDesign.DesignComponents.IconPng;
import javafx.scene.image.ImageView;
import graviwave.GameDesign.Base;
import graviwave.Interfaces.GameInterface;

import java.util.Timer;
import java.util.TimerTask;

public class Game implements GameInterface {
    private Base base;
    private Score score;
    private Level level;
    private GameLevel gameLevel;
    private Timer timer;
    IconPng timePanel;
    Game(Base base){
        this.base = base;
        this.score = new Score(this);
        this.gameLevel = new GameLevel(this, 1);
        this.level = new Level(this);
        base.getPane().getChildren().add(score.getPane());
        base.getPane().getChildren().add(level.getPane());
        base.getBackground().getPane().getChildren().remove(base.getBackground().gethBox());
        try {
            this.timePanel = new IconPng("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\gameOver.png");
            base.getPane().getChildren().add(timePanel.getImageView());
            timePanel.getImageView().setVisible(false);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }


    public GameLevel getGameLevel() {
        return gameLevel;
    }
    Base getBase() {
        return base;
    }

    Level getLevel() {
        return level;
    }

    Score getScore() {
        return score;
    }


    @Override
    public void updateGame(int level) {
        if(timer != null){
            timer.cancel();
        }
        this.gameLevel = new GameLevel(this, level);
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timePanel.getImageView().setVisible(true);
                gameLevel.getTargetPanel().getTimer().stop();
                timer.cancel();
            }
        };
        timer.schedule(timerTask, 40000);
    }

    @Override
    public void stopGame(ImageView imageView) {
        base.getPane().getChildren().clear();
        gameLevel.getPane().getChildren().clear();
        timer.cancel();
        base.getBackground().getPane().getChildren().add(imageView);
    }

}
