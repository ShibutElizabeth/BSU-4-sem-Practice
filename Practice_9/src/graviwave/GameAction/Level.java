package graviwave.GameAction;

import javafx.geometry.Point2D;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import graviwave.Interfaces.LevelInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Level implements LevelInterface {
    private ImageView imageView;
    private ArrayList<File> files;
    private String localUrl;
    private Pane pane;
    private Bloom bloom;
    private Game game;
    int number;
    Level(Game game){
        try {
            this.game = game;
            Point2D point2D = new Point2D(390, 0);
            this.pane = new Pane();
            this.files = new ArrayList<>();
            File f1 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\levels\\level1.png");
            File f2 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\levels\\level2.png");
            File f3 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\levels\\level3.png");
            File f4 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\levels\\level4.png");
            File f5 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\levels\\level5.png");
            File f6 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\levels\\saved.png");
            files.add(f1);
            files.add(f2);
            files.add(f3);
            files.add(f4);
            files.add(f5);
            files.add(f6);
            this.bloom = new Bloom();
            bloom.setThreshold(0.5);
            this.localUrl  = f1.toURI().toURL().toString();
            Image image = new Image(localUrl);
            this.imageView = new ImageView(image);
            imageView.setX(point2D.getX());
            imageView.setY(point2D.getY());
            pane.getChildren().add(imageView);
            levelUpdate(0);
            this.number = 1;
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    Pane getPane() {
        return pane;
    }

    @Override
    public void levelUpdate(int l) {
        try{
            if(l == 5){
                File f = files.get(l);
                localUrl = f.toURI().toURL().toString();
                imageView.setImage(new Image(localUrl));
                imageView.setVisible(true);
                bloom.setThreshold(1);
                imageView.setEffect(bloom);
                imageView.setX(300);
                game.stopGame(imageView);
            }
            else {
                number = l+1;
                game.getGameLevel().getPane().getChildren().clear();
                pane.getChildren().clear();
                pane.getChildren().add(imageView);
                File f = files.get(l);
                localUrl = f.toURI().toURL().toString();
                imageView.setImage(new Image(localUrl));
                imageView.setVisible(true);
                imageView.setEffect(bloom);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        imageView.setVisible(false);
                        timer.cancel();
                    }
                };
                timer.schedule(task, 2000);
                game.updateGame(l);
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
