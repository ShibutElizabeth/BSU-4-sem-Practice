package graviwave;

import javafx.application.Application;

import javafx.stage.Stage;
import graviwave.GameDesign.Base;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Base base = new Base();
        primaryStage.setScene(base.getScene());
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
