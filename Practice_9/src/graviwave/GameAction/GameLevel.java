package graviwave.GameAction;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import graviwave.GameObjects.Boy;
import graviwave.GameObjects.Bullet;
import graviwave.GameObjects.Target;
import graviwave.Interfaces.ShotInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameLevel implements ShotInterface {
    private Game game;
    private ArrayList<Target> targets;
    private int numberOfLevel;
    private TargetPanel targetPanel;
    private Pane pane;
    GameLevel(Game game, int level){
        this.pane = new Pane();
        this.game = game;
        this.numberOfLevel = level;
        Boy boy = new Boy(game.getBase());
        targetPanel = new TargetPanel(game);
        this.targets = targetPanel.getTargets();
        pane.getChildren().add(targetPanel.getPane());
        pane.getChildren().add(boy.getGun());
        pane.getChildren().add(boy.getImageView());
        Point2D point = new Point2D(1090, 10);
        VBox hBox = new VBox();
        hBox.setSpacing(20);
        hBox.setTranslateX(point.getX());
        hBox.setTranslateY(point.getY());

        hBox.getChildren().add(game.getBase().getBackground().getSettingsIcons().getSound().getImageView());
        hBox.getChildren().add(game.getBase().getBackground().getSettingsIcons().getRestart().getImageView());
        game.getBase().getBackground().getSettingsIcons().setGameActionSound();
        game.getBase().getBackground().getSettingsIcons().setActionRestart(game);
        pane.getChildren().add(hBox);
        game.getBase().getPane().getChildren().add(pane);

    }

    TargetPanel getTargetPanel() {
        return targetPanel;
    }

    int getNumberOfLevel() {
        return numberOfLevel;
    }

    Pane getPane() {
        return pane;
    }
    @Override
    public boolean update(Bullet bullet){
        for(Target target: targets){
            if(bullet.getImageView().getBoundsInParent().intersects(target.getImageView().getBoundsInParent())){
                targets.remove(target);
                File file;
                Image image;
                try {
                    file = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\boom"+ target.getName());
                    final String localUrl = file.toURI().toURL().toString();
                    image = new Image(localUrl);
                    target.getImageView().setImage(image);
                }
                catch (Exception exc){
                    exc.printStackTrace();
                }
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        target.getImageView().setVisible(false);
                        timer.cancel();
                    }
                };
                timer.schedule(task, 50);

                if(target.getName().equals("red.png")){
                    game.getScore().scoreUpdate(3);
                }
                else{
                    game.getScore().scoreUpdate(1);
                }

                return true;
            }
        }
        return false;
    }
}
