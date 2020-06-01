package graviwave.GameDesign;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Base {
    private Scene scene;
    private Pane pane;
    private Background background;

    public Base()throws Exception{
        this.pane = new Pane();
        this.scene = new Scene(pane, 1190, 710);
        this.background = new Background(this);
    }

    public Scene getScene() {
        return scene;
    }

    public Pane getPane() {
        return pane;
    }

    public Background getBackground() {
        return background;
    }
}
