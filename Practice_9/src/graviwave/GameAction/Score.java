package graviwave.GameAction;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import graviwave.Interfaces.ScoreInterface;

import java.io.File;
import java.util.ArrayList;

public class Score implements ScoreInterface {
    private ImageView imageViewE;
    private ImageView imageViewD;
    private String localUrl1, localUrl2;
    private int count;
    private ArrayList<File> files;
    private Pane pane;
    private Game game;
    Score(Game game){
        try{
            this.game = game;
            this.count = 0;
            this.pane = new Pane();
            files = new ArrayList<>();
            File file0 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\null.png");
            File file1 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\one.png");
            File file2 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\two.png");
            File file3 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\three.png");
            File file4 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\four.png");
            File file5 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\five.png");
            File file6 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\six.png");
            File file7 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\seven.png");
            File file8 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\eight.png");
            File file9 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\nine.png");
            files.add(file0);
            files.add(file1);
            files.add(file2);
            files.add(file3);
            files.add(file4);
            files.add(file5);
            files.add(file6);
            files.add(file7);
            files.add(file8);
            files.add(file9);
            this.localUrl1  = file0.toURI().toURL().toString();
            Image imageE = new Image(localUrl1);
            this.imageViewE = new ImageView(imageE);
            this.localUrl2 = file1.toURI().toURL().toString();
            Image imageD = new Image(localUrl2);
            this.imageViewD = new ImageView(imageD);
            imageViewD.setVisible(false);
            Point2D pointD = new Point2D(20, 600);
            Point2D pointE = new Point2D(100, 600);
            imageViewE.setX(pointE.getX());
            imageViewE.setY(pointE.getY());
            imageViewD.setX(pointD.getX());
            imageViewD.setY(pointD.getY());
            pane.getChildren().add(imageViewD);
            pane.getChildren().add(imageViewE);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    Pane getPane() {
        return pane;
    }

    void setCount(int count) {
        this.count = count;
    }

    private int prevLevel = 0;
    @Override
    public void scoreUpdate(int i) {
        count += i;
        int iE, iD;
        String scoreCount = count + "";
        try {
            if (scoreCount.length() == 1) {
                imageViewD.setVisible(false);
                iE = Integer.parseInt(scoreCount.charAt(0)+"");
                File file = files.get(iE);
                localUrl1 = file.toURI().toURL().toString();
                imageViewE.setImage(new Image(localUrl1));

            } else {
                iE =Integer.parseInt(scoreCount.charAt(1)+"");
                iD =Integer.parseInt(scoreCount.charAt(0)+"");

                File f1 = files.get(iE);
                File f2 = files.get(iD);
                localUrl1 = f1.toURI().toURL().toString();
                localUrl2 = f2.toURI().toURL().toString();
                imageViewD.setVisible(true);
                imageViewD.setImage(new Image(localUrl2));
                imageViewE.setImage(new Image(localUrl1));
                if(prevLevel != iD){
                    prevLevel = iD;
                    game.getLevel().levelUpdate(iD);
                }
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
