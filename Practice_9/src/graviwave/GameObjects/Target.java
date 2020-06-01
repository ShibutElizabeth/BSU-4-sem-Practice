package graviwave.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import graviwave.GameAction.ActionComponents.Coordinates;
import graviwave.GameAction.ActionComponents.Function;
import graviwave.GameAction.ActionComponents.Trajectory;

import java.io.File;

public class Target {
    private ImageView imageView;

    private  String name;
    public Target(int number, String path, Function function){
        try {
            Coordinates coordinates = new Coordinates();
            Point2D point2D = new Point2D(coordinates.getX(), coordinates.getY());
            File file = new File(path);
            final String localUrl = file.toURI().toURL().toString();
            Image image = new Image(localUrl);
            this.imageView = new ImageView(image);
            imageView.setX(point2D.getX());
            imageView.setY(point2D.getY());

            this.name = path.substring(60);
            new Trajectory(this, function, number);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
    public ImageView getImageView() {
        return imageView;
    }

    public String getName() {
        return name;
    }

}
