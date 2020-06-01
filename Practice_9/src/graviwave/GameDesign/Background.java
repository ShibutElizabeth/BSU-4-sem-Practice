package graviwave.GameDesign;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import graviwave.GameAction.SettingsIcons;

import java.io.File;

public class Background{
    private Pane pane;
    private HBox hBox;
    private SettingsIcons settingsIcons;

    Background(Base base) throws Exception {
        this.pane = base.getPane();
        MusicalArrangement musicalArrangement = new MusicalArrangement();
        setGameBackground();
        setSettings(base, musicalArrangement);
    }

    private void setSettings(Base base, MusicalArrangement musicalArrangement){
        try{
            Point2D point = new Point2D(10, 10);
            this.hBox = new HBox();
            hBox.setSpacing(1090);
            hBox.setMaxHeight(150);
            hBox.setTranslateX(point.getX());
            hBox.setTranslateY(point.getY());
            this.settingsIcons = new SettingsIcons(base, musicalArrangement);
            hBox.getChildren().add(settingsIcons.getSound().getImageView());
            //hBox.getChildren().add(settingsIcons.getTimePanel().getImageView());
            hBox.getChildren().add(settingsIcons.getRestart().getImageView());
            settingsIcons.setActionSound();
            pane.getChildren().add(settingsIcons.getGroup());
            pane.getChildren().add(hBox);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
    private void setGameBackground(){
        try{
            File file = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\background\\mainBackground.jpg");
            final String localUrl = file.toURI().toURL().toString();
            Image backgroundImage = new Image(localUrl);
            pane.setBackground(new javafx.scene.layout.Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));

        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public HBox gethBox() {
        return hBox;
    }

    public Pane getPane() {
        return pane;
    }

    public SettingsIcons getSettingsIcons() {
        return settingsIcons;
    }
}
