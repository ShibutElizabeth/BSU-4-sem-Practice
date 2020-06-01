package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
            final List<String> list = new ArrayList<>();
            String TEXT = "Volcanic eruptions:\nVesuvius 24-08-79 \nMon Pele 08.05.1902 \n" +
                    "Ruiz 13.11?1985 \nKrakatau 26-08-1883 \nEtna 11/03/1669 \nMayon 23-10-1766 " +
                    "\nWrongExample 31.02.1900 \nCorrectExample 28.02.1900";
            TextArea textArea = new TextArea(TEXT);
            textArea.setPrefColumnCount(15);
            textArea.setPrefRowCount(15);
            ObservableList<String> observable = FXCollections.observableArrayList(list);
            ListView<String> listView = new ListView<>(observable);

            Button ok = new Button("ok");
            ok.setOnAction(event -> {
                list.clear();
                observable.clear();
                String text = textArea.getText();
                String[] subStr;
                String del = " ";
                subStr = text.split(del);
                for (String s : subStr) {
                    if (isChecked(s)) {
                        list.add(s);
                    }
                }
                observable.addAll(list);
                listView.setItems(observable);
            });

            FlowPane root = new FlowPane(10, 10, textArea, listView, ok);
            root.setAlignment(Pos.CENTER);
            primaryStage.setTitle("Practice 5.2");
            Scene scene = new Scene(root, 800, 605);
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }

    private boolean isChecked(String str){
        Pattern date = Pattern.compile("^(?:(?:31([/\\-.])(?:0?[13578]|1[02]))\\1|" +
                "(?:(?:29|30)([/\\-.])(?:0?[1,3-9]|1[0-2])\\2))" +
                "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29([/\\-.])0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?" +
                "(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^" +
                "(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4" +
                "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher matcher = date.matcher(str);
        return matcher.find();
    }


}
