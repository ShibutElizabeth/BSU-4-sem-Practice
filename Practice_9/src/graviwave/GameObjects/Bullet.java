package graviwave.GameObjects;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import graviwave.GameDesign.Base;
import graviwave.GameDesign.DesignComponents.SoundOf;

import java.io.File;

public class Bullet {
    private double angle;
    private ImageView imageView;
    private Base base;
    private int index;
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            shot();
        }
    };
    private SoundOf shotSound, balloonSound;

    Bullet(double xPos, double yPos, double angle, int index, Base base){
        this.base = base;
        Point2D imagePoint = new Point2D(xPos, yPos);
        this.index = index;
        this.angle = Math.abs(angle)+20;
        try {
            this.shotSound = new SoundOf("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\music\\shot.mp3");
            this.balloonSound = new SoundOf("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\music\\balloon.mp3");
            File file = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\startLittle.png");
            final String localUrl = file.toURI().toURL().toString();
            Image image = new Image(localUrl);
            this.imageView = new ImageView(image);
            imageView.setX(imagePoint.getX());
            imageView.setY(imagePoint.getY());
            timer.start();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
    public ImageView getImageView() {
        return imageView;
    }

    private void shot(){
        shotSound.getPlayer().play();
        imageView.setVisible(true);
        imageView.translateXProperty().set(imageView.getTranslateX()+ index*(angle));
        imageView.translateYProperty().set(imageView.getTranslateY() - (angle));

        if(base.getBackground().getSettingsIcons().getGame().getGameLevel().update(this)){
            timer.stop();
            balloonSound.getPlayer().play();
            imageView.setVisible(false);
        }

    }
}
