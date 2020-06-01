package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.fxyz3d.shapes.primitives.Text3DMesh;
import org.fxyz3d.shapes.primitives.TexturedMesh;
import org.fxyz3d.utils.CameraTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private double xValue = 0, yValue = 0;
    private Text3DMesh letters;
    private double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
    @Override
    public void start(Stage primaryStage) {

        final List<ColorItem> list = new ArrayList<>();
        list.add(new ColorItem("Snow", Color.SNOW));
        list.add(new ColorItem("DarkCyan", Color.DARKCYAN));
        list.add(new ColorItem("Purple", Color.PURPLE));
        list.add(new ColorItem("Yellow", Color.YELLOW));
        list.add(new ColorItem("DeepSkyBlue", Color.DEEPSKYBLUE));
        list.add(new ColorItem("Aquamarine", Color.AQUAMARINE));
        list.add(new ColorItem("Crimson", Color.CRIMSON));
        list.add(new ColorItem("Black", Color.BLACK));
        list.add(new ColorItem("Indigo", Color.INDIGO));
        list.add(new ColorItem("Aqua", Color.AQUA));
        final List<String> colors = new ArrayList<>();
        for(ColorItem colorItem : list){
            colors.add(colorItem.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(colors);
        ComboBox<String> comboBox = new ComboBox<>(items);
        comboBox.setValue(items.get(6));
        TextField textField = new TextField();
        VBox settings = new VBox(20);

        Slider sliderHeight = new Slider();
        Slider sliderWidth = new Slider();
        sliderHeight.setMin(-50);
        sliderHeight.setMax(50);
        sliderWidth.setMin(-50);
        sliderWidth.setMax(50);
        sliderHeight.setValue(-50);
        sliderWidth.setValue(50);
        sliderHeight.setShowTickMarks(true);
        sliderWidth.setShowTickMarks(true);
        DropShadow dropShadow = new DropShadow();
        sliderHeight.valueProperty().addListener((obsVal, oldVal, newVal) -> {
            yValue = (-1) *newVal.intValue();
            dropShadow.setOffsetY(yValue);
        });
        sliderWidth.valueProperty().addListener((obsVal, oldVal, newVal) -> {
            xValue = newVal.intValue();
            dropShadow.setOffsetX(xValue);
        });
        dropShadow.setOffsetY(yValue);
        dropShadow.setOffsetX(xValue);

        dropShadow.setWidth(20);
        dropShadow.setHeight(20);
        dropShadow.setColor(Color.GREY);


        Button ok = new Button("\uD835\uDC28\uD835\uDC24");
        Button plus = new Button("+");
        Button minus = new Button("-");

        FlowPane buttonPane = new FlowPane(10,10,  minus, plus);
        buttonPane.setAlignment(Pos.CENTER);

        Label color = new Label("\uD835\uDC02\uD835\uDC28\uD835\uDC25\uD835\uDC28\uD835\uDC2B \uD835\uDC28\uD835\uDC1F \uD835\uDC2D\uD835\uDC1E\uD835\uDC31\uD835\uDC2D");
        Label ShadowUp = new Label("\uD835\uDC12\uD835\uDC21\uD835\uDC1A\uD835\uDC1D\uD835\uDC28\uD835\uDC30\n\uD835\uDC03\uD835\uDC28\uD835\uDC30\uD835\uDC27  \uD835\uDC14\uD835\uDC29");
        Label ShadowLeft = new Label("\uD835\uDC12\uD835\uDC21\uD835\uDC1A\uD835\uDC1D\uD835\uDC28\uD835\uDC30\n\uD835\uDC0B\uD835\uDC1E\uD835\uDC1F\uD835\uDC2D  \uD835\uDC11\uD835\uDC22\uD835\uDC20\uD835\uDC21\uD835\uDC2D");

        FlowPane inputPanel = new FlowPane(10, 10, textField, ok);
        inputPanel.setAlignment(Pos.CENTER);
        settings.getChildren().addAll(inputPanel, color, comboBox, ShadowUp, sliderHeight,
                ShadowLeft, sliderWidth);
        settings.setAlignment(Pos.TOP_CENTER);

        Camera camera = new PerspectiveCamera(true);
        CameraTransformer cameraTransform = new CameraTransformer();
        cameraTransform.setTranslate(0, 0, 0);
        cameraTransform.getChildren().add(camera);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateX(1300);
        camera.setTranslateZ(-3000);
        cameraTransform.rx.setAngle(10.0);
        Group group = new Group(cameraTransform);
        SubScene subScene = new SubScene(group, 1300, HEIGHT);
        BorderPane root = new BorderPane();
        root.setCenter(subScene);
        root.setLeft(settings);
        Scene scene = new Scene(root, WIDTH+600, HEIGHT);
        cameraTransform.getChildren().add(new AmbientLight());
        subScene.setCamera(camera);


        ok.setOnAction(event -> {
            group.getChildren().clear();
            letters = new Text3DMesh("  " +textField.getText(), "Gadugi Bold",
                    180, true, 100, 10, 1);
            letters.setTextureModeNone(getColorItem(comboBox.getValue(), list));

            letters.setEffect(dropShadow);
            group.getChildren().addAll(letters);

            IntStream.range(0, letters.getChildren().size()).
                    forEach(i -> ((TexturedMesh) (letters.getChildren().get(i))).getTranslate().setY(100d ));

        });

        subScene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        subScene.setOnMouseDragged((MouseEvent me) -> {
            sliderHeight.setValue(0);
            sliderWidth.setValue(0);
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);
            double modifier = 10.0;
            double modifierFactor = 0.1;
            if (me.isControlDown()) { modifier = 0.1; }
            if (me.isShiftDown()) { modifier = 50.0; }
            if (me.isPrimaryButtonDown()) {
                cameraTransform.ry.setAngle(((cameraTransform.ry.getAngle() +
                        mouseDeltaX * modifierFactor * modifier * 2.0) % 360 + 540) % 360 - 180);
                cameraTransform.rx.setAngle(((cameraTransform.rx.getAngle() -
                        mouseDeltaY * modifierFactor * modifier * 2.0) % 360 + 540) % 360 - 180);
            } else if (me.isSecondaryButtonDown()) {
                double z = camera.getTranslateZ();
                double newZ = z + mouseDeltaX * modifierFactor * modifier;
                camera.setTranslateZ(newZ);
            } else if (me.isMiddleButtonDown()) {
                cameraTransform.t.setX(cameraTransform.t.getX() +
                        mouseDeltaX * modifierFactor * modifier * 0.3);
                cameraTransform.t.setY(cameraTransform.t.getY() +
                        mouseDeltaY * modifierFactor * modifier * 0.3);
            }
        });

        primaryStage.setTitle("\uD835\uDC0F\uD835\uDC2B\uD835\uDC1A\uD835\uDC1C\uD835\uDC2D\uD835\uDC22\uD835\uDC1C\uD835\uDC1E\uD835\uDFD5.\uD835\uDFD0\n \uD835\uDD44\uD835\uDD5A\uD835\uDD5F\uD835\uDD5A \uD835\uDD4E\uD835\uDD60\uD835\uDD63\uD835\uDD55 " +
                "\uD835\uDD38\uD835\uDD63\uD835\uDD65");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Color getColorItem(String name, List<ColorItem> list){
        for(ColorItem item : list){
            if(name.equals(item.getName())){ return item.getColor(); }
        }
        return null;
    }
    public static void main(String[] args) { launch(args); }
}

class ColorItem{
    private String name;
    private Color color;

    ColorItem(String name, Color color){
        this.name = name;
        this.color = color;
    }

    Color getColor() { return color; }
    String getName() { return name; }

}