package graviwave.GameDesign.DesignComponents;

import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class IconPng {
    private ImageView imageView;

    private Bloom bloom = new Bloom();
    public IconPng(String path) throws Exception{
        File file = new File(path);
        String localUrlIconPng = file.toURI().toURL().toString();
        Image iconImage = new Image(localUrlIconPng);
        this.imageView = new ImageView(iconImage);
        imageView.setOnMouseEntered(event -> {
            bloom.setThreshold(0.1);
            imageView.setEffect(bloom);
        });
        imageView.setOnMouseExited(event -> {
            bloom.setThreshold(1);
            imageView.setEffect(bloom);
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

}
