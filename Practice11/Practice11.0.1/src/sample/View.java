package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class View implements ViewInterface{

    Scene scene;
    BorderPane pane;

    TableView<Planet> table;
    BorderPane littlePane;
    Stage stage;
    Controller controller;
    Button addButton, removeButton;

    View(Stage stage){
        this.stage = stage;
        this.controller = new Controller(this);
        this.pane = new BorderPane();
        this.scene = new Scene(pane, 800, 545);
        MenuBar menuBar = new MenuBar();
        Menu XML = new Menu("XML");
        MenuItem openXML = new MenuItem("Open");
        MenuItem saveXML = new MenuItem("Save");
        XML.getItems().addAll(openXML, saveXML);
        Menu SAX = new Menu("SAX");
        MenuItem max = new MenuItem("Max");
        MenuItem min = new MenuItem("Min");
        SAX.getItems().addAll(min, max);
        Menu DAT = new Menu("DAT");
        MenuItem openDAT = new MenuItem("Open");
        MenuItem saveDAT = new MenuItem("Save");
        DAT.getItems().addAll(openDAT, saveDAT);
        menuBar.getMenus().addAll(XML, DAT, SAX);
        pane.setTop(menuBar);
        pane.setStyle("-fx-background-color:black");
        littlePane = new BorderPane();
        pane.setCenter(littlePane);
        setBackground();
        addButton = new Button("Add");
        removeButton = new Button("Remove");

        addButton.setStyle("-fx-background-color: linear-gradient(to bottom, #A59D9C, #686564);-fx-text-fill: azure");
        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom, #A59D9C, #686564);-fx-text-fill: azure");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(addButton, removeButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        pane.setBottom(hBox);
        table = new TableView<>();

        openXML.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showOpenDialog(stage);
            if(f != null){
                controller.open(f.getPath());
            }
            else {
                showError("Incorrect file!");
            }
        });
        saveXML.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showSaveDialog(stage);
            if(f != null){
                controller.write(f.getPath());
            }
            else {
                showError("Incorrect file!");
            }
        });

        max.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showOpenDialog(stage);
            if(f != null){
                controller.SAXCounting(f.getPath(), "max");
            }
            else {
                showError("Incorrect file!");
            }
        });
        min.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showOpenDialog(stage);
            if(f != null){
                controller.SAXCounting(f.getPath(), "min");
            }
            else {
                showError("Incorrect file!");
            }
        });

        openDAT.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DAT files (*.dat)", "*.dat");
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showOpenDialog(stage);
            if(f!= null){
                controller.openDAT(f.getPath());
            }
            else {
                showError("Incorrect file!");
            }
        });
        saveDAT.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DAT files (*.dat)", "*.dat");
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showSaveDialog(stage);
            if(f!= null){
                controller.writeDAT(f.getPath());
            }
            else {
                showError("Incorrect file!");
            }
        });
    }
    private void setBackground(){
        try{
            File file = new File("C:\\Users\\Лиза\\IdeaProjects\\Practice11.0.1\\src\\background.jpg");
            final String localUrl = file.toURI().toURL().toString();
            Image backgroundImage = new Image(localUrl);
            littlePane.setBackground(new javafx.scene.layout.Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));

        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }

    private Planet selectedPlanet = null;

    private void setSelected(){
        TableView.TableViewSelectionModel<Planet> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((val, oldVal, newVal) -> {
            if(newVal != null) {
                selectedPlanet= newVal;
            }
        });
    }

    void setAction(){
        addButton.setOnAction(event -> {
            Label nameLabel = new Label("Name: ");
            TextField nameInput = new TextField();
            Label massLabel = new Label("Mass: ");
            TextField massInput = new TextField();
            Label diameterLabel = new Label("Diameter: ");
            TextField diameterInput = new TextField();
            Label countLabel = new Label("Number of satellites: ");
            TextField countInput = new TextField();
            Label ansLabel = new Label("Ring system: ");
            RadioButton yes = new RadioButton("Yes");
            RadioButton no = new RadioButton("No");
            ButtonBar bar = new ButtonBar();
            bar.getButtons().add(yes);
            bar.getButtons().add(no);

            no.setSelected(true);
            ToggleGroup group = new ToggleGroup();
            group.getToggles().add(yes);
            group.getToggles().add(no);

            Button ok = new Button("Ok");
            Button cancel = new Button("Cancel");
            HBox box = new HBox();
            box.getChildren().addAll(ok, cancel);
            box.setAlignment(Pos.CENTER);
            box.setSpacing(10);
            GridPane gridPane = new GridPane();
            gridPane.add(nameLabel, 0, 0);
            gridPane.add(nameInput, 1, 0);
            gridPane.add(massLabel, 0, 1);
            gridPane.add(massInput, 1, 1);
            gridPane.add(diameterLabel, 0, 2);
            gridPane.add(diameterInput, 1, 2);
            gridPane.add(countLabel, 0, 3);
            gridPane.add(countInput, 1, 3);
            gridPane.add(ansLabel, 0, 4);
            gridPane.add(bar, 1, 4);
            gridPane.add(box, 0, 5);
            gridPane.setAlignment(Pos.BASELINE_CENTER);


            Scene secondScene = new Scene(gridPane, 400, 200);
            Stage newStage = new Stage();
            newStage.setScene(secondScene);
            newStage.setTitle("Add planet");

            newStage.setX(stage.getX()+200);
            newStage.setY(stage.getY()+100);

            newStage.show();
            ok.setOnAction(event1 -> {
                if (nameInput.getText() == null || nameInput.getText().trim().isEmpty()
                        || massInput.getText() == null || massInput.getText().trim().isEmpty()
                        || diameterInput.getText() == null || diameterInput.getText().trim().isEmpty()
                        || countInput.getText() == null || countInput.getText().trim().isEmpty()) {
                    showError("Empty field!");
                }
                else {
                    try {
                        String name = nameInput.getText();
                        double mass = Double.parseDouble(massInput.getText());
                        double diameter = Double.parseDouble(diameterInput.getText());
                        int count = Integer.parseInt(countInput.getText());
                        String ans = "No";
                        if (yes.isSelected()) {
                            ans = "Yes";
                        } else if (no.isSelected()) {
                            ans = "No";
                        }
                        if(name.isEmpty() || mass <= 0 || diameter <= 0 || count < 0
                                ||(!ans.toUpperCase().equals("YES") && !ans.toUpperCase().equals("NO")))
                            showError("Incorrect input!");
                        else{
                            Planet addingPlanet = new Planet(name, mass,diameter, count,ans);
                            controller.updateList(addingPlanet, "add");
                            newStage.close();
                        }

                    } catch (NumberFormatException e) {
                        showError(e.getMessage());
                    }
                }
            });
        });
        removeButton.setOnAction(event -> {
            if(selectedPlanet!= null){
                controller.updateList(selectedPlanet, "remove");
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
    TableColumn<Planet, String> nameColumn;
    TableColumn<Planet, String> massColumn;
    TableColumn<Planet, String> diameterColumn;
    TableColumn<Planet, String> countColumn;
    TableColumn<Planet, String> systemColumn;

    ObservableList<Planet> planets;

    @Override
    public void showTable(List<Planet> list){
        planets = FXCollections.observableArrayList(list);
        table=new TableView<>(planets);
        table.setPrefWidth(800);
        nameColumn = new TableColumn<>("Name");
        massColumn = new TableColumn<>("Mass");
        diameterColumn = new TableColumn<>("Diameter");
        countColumn = new TableColumn<>("Satellite count");
        systemColumn = new TableColumn<>("Ring System");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        massColumn.setCellValueFactory(new PropertyValueFactory<>("mass"));
        diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        systemColumn.setCellValueFactory(new PropertyValueFactory<>("system"));
        nameColumn.setPrefWidth(250);
        massColumn.setPrefWidth(150);
        diameterColumn.setPrefWidth(150);
        countColumn.setPrefWidth(100);
        systemColumn.setPrefWidth(150);
        table.getColumns().add(nameColumn);
        table.getColumns().add(massColumn);
        table.getColumns().add(diameterColumn);
        table.getColumns().add(countColumn);
        table.getColumns().add(systemColumn);
        littlePane.setCenter(table);
        setSelected();
        setAction();
    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
    }
    @Override
    public void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
