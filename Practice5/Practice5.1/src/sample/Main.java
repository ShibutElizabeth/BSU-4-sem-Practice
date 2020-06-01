package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.apache.commons.validator.DateValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        final List<String> list = new ArrayList<String>();
        list.add("natural");
        list.add("integer");
        list.add("fractional");
        list.add("date");
        list.add("time");
        list.add("e-mail");
        ObservableList<String> items = FXCollections.observableArrayList(list);
        final ComboBox<String> comboBox = new ComboBox<String>(items);
        comboBox.setValue(items.get(0));
        final TextField field = new TextField();
        Button ok = new Button("ok");

        File fileGreen = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice5.1\\src\\plain\\green.png");
        File fileRed = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice5.1\\src\\plain\\red.png");
        final String localUrl2 = fileGreen.toURI().toURL().toString();
        final String localUrl3 = fileRed.toURI().toURL().toString();
        Image green = new Image(localUrl2);
        Image red = new Image(localUrl3);
        final Label labelGreen = new Label();
        labelGreen.setGraphic(new ImageView(green));
        final Label labelRed = new Label();
        labelRed.setGraphic(new ImageView(red));
        labelGreen.setVisible(false);
        labelRed.setVisible(false);

        ok.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                String text1 = field.getText();
                boolean flag = getChecked(text1, comboBox.getValue());
                if(flag){
                    labelRed.setVisible(false);
                    labelGreen.setVisible(true);
                }
                else {
                    labelGreen.setVisible(false);
                    labelRed.setVisible(true);
                }
                field.clear();
            }
        });

        FlowPane rt = new FlowPane(10, 30, comboBox, field, ok, labelGreen, labelRed);
        rt.setAlignment(Pos.CENTER);
        Scene scene = new Scene(rt, 400, 300);
        primaryStage.setTitle("Practice 5.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }

    private boolean getChecked (String text, String item){
        try {
            if(item.equals("date")){
                DateValidator dateValidator = DateValidator.getInstance();
                return dateValidator.isValid(text, "dd/MM/yyyy", false);
            }
            if(item.equals("time")){
                Pattern comma = Pattern.compile("^(([0-1][0-9])|(2*[0-3])):[0-5][0-9]$");
                Matcher matcher1 = comma.matcher(text);
                if(matcher1.find()){ return true; }
            }
            else{
                double number = Double.parseDouble(text);
                if (item.equals("natural") && (number > 0) && (number % 1 == 0)) {
                    return true;
                }
                if (item.equals("integer") && (number % 1 == 0)) { return true; }
                if (item.equals("fractional")) {
                    Pattern eps = Pattern.compile("^[-+]?[0-9]*[.][0-9]+(?:[eE][-+]?[0-9]+)?$");
                    Matcher matcher = eps.matcher(text);
                    Pattern eps2 = Pattern.compile("^[-+]?([1-9]*([eE][-][0-9]+))$");
                    Matcher matcher2 = eps2.matcher(text);
                    Pattern point = Pattern.compile("^(0|[-+]?[1-9]\\d*)([.]\\d+)$");
                    Matcher matcher1 = point.matcher(text);
                    return matcher1.find() || matcher.find() || matcher2.find();
                }
            }
        }
        catch (NumberFormatException e){
            if(item.equals("e-mail")){
                Pattern mail = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)" +
                        "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                Matcher m = mail.matcher(text);
                if(m.find()){ return true; }
            }
            if (item.equals("fractional")) {
                Pattern eps = Pattern.compile("^[-+]?[0-9]*[,][0-9]+(?:[eE][-+]?[0-9]+)?$");
                Matcher matcher = eps.matcher(text);
                Pattern point = Pattern.compile("^(0|[-+]?[1-9]\\d*)([,]\\d+)$");
                Matcher matcher1 = point.matcher(text);
                return matcher1.find() || matcher.find();
            }
            else return false;
        }
        return false;
    }

}
