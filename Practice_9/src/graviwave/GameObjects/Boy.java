package graviwave.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import graviwave.GameDesign.Base;

import java.io.File;

public class Boy {
    private Image imageRight;
    private ImageView imageView;
    private Image imageLeft;

    private Image gunRight, gunLeft;
    private ImageView gun;

    public Boy(Base base) {
        try {
            Point2D pointBoy = new Point2D(400, 370);
            File file = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\boy\\onlyBoyRight.png");
            final String localUrl = file.toURI().toURL().toString();
            this.imageRight = new Image(localUrl);
            File file2 = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\boy\\onlyBoyLeft.png");
            final String localUrl2 = file2.toURI().toURL().toString();
            this.imageRight = new Image(localUrl);
            this.imageLeft = new Image(localUrl2);
            this.imageView = new ImageView(imageRight);
            imageView.setX(pointBoy.getX());
            imageView.setY(pointBoy.getY());

            File gunFileR = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\boy\\gunRight1.png");
            final String localUrlG1 = gunFileR.toURI().toURL().toString();
            this.gunRight = new Image(localUrlG1);
            File gunFileL = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\boy\\gunLeft1.png");
            final String localUrlG2 = gunFileL.toURI().toURL().toString();
            this.gunLeft = new Image(localUrlG2);
            this.gun = new ImageView(gunRight);
            gun.setX(pointBoy.getX());
            gun.setY(pointBoy.getY());

            Scene scene = base.getScene();
            scene.setOnKeyPressed(event -> {
                KeyCode key = event.getCode();
                switch (key) {
                    case LEFT:
                        imageView.setImage(imageLeft);
                        gun.setImage(gunLeft);
                        imageView.translateXProperty().set(imageView.getTranslateX() - 30);
                        gun.translateXProperty().set(gun.getTranslateX() - 30);
                        break;
                    case RIGHT:
                        imageView.setImage(imageRight);
                        gun.setImage(gunRight);
                        imageView.translateXProperty().set(imageView.getTranslateX() + 30);
                        gun.translateXProperty().set(gun.getTranslateX() + 30);
                        break;
                    case COMMA:
                        if ((gun.getRotate() != -20)) {
                            gun.setRotate(gun.getRotate() - 2);
                            gun.translateXProperty().set(gun.getTranslateX() - 3);
                        }
                        break;
                    case PERIOD:
                        if (gun.getRotate() != 20) {
                            gun.setRotate(gun.getRotate() + 2);
                            gun.translateXProperty().set(gun.getTranslateX() + 3);
                        }
                        break;
                    case SPACE:
                        double xPos = gun.getTranslateX();
                        double angle = gun.getRotate();
                        if (imageView.getImage() == imageRight) {
                            Shot shot = new Shot(xPos + 845 + 6 * angle / 2, 395 + 6 * angle / 2, angle, 1, base);
                            base.getPane().getChildren().add(shot.getBullet().getImageView());
                        } else {
                            Shot shot = new Shot(xPos + 390 + 6 * angle / 2, 395 + 6 * angle / 2, angle, -1, base);
                            base.getPane().getChildren().add(shot.getBullet().getImageView());
                        }
                        break;
                }
            });
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public ImageView getGun() {
        return gun;
    }

    public ImageView getImageView() {
        return imageView;
    }

}
