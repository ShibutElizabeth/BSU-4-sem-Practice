package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Group group = new Group();
        LogRecord log = new LogRecord();
        ListView<String> listView = log.getListView();
        group.getChildren().add(listView);
        DisplayKey displayKey = new DisplayKey();

        FlowPane root = new FlowPane(10, 10, group, displayKey.getLabelKey());
        Scene scene = new Scene(root, 500, 500);

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            log.update(key.getName());
            displayKey.update(key.getName());
        });

        primaryStage.setTitle("Practice 8");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}