package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;

public class DisplayKey implements EventListener {
    private Label labelKey;

    DisplayKey(){
        this.labelKey = new Label();
        labelKey.setVisible(false);
    }

    Label getLabelKey() { return labelKey; }

    @Override
    public void update(String keyName) {
        try {
            File file = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice8" +
                    "\\src\\standardKeyboardKeys\\" + keyName + ".png");
            String localUrl = file.toURI().toURL().toString();
            Image image = new Image(localUrl);
            labelKey.setGraphic(new ImageView(image));
            labelKey.setVisible(true);
        } catch (MalformedURLException exc){
            exc.getStackTrace();
        }
    }
}
