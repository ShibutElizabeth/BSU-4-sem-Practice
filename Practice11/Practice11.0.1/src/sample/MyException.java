package sample;

import javafx.scene.control.Alert;

class MyException extends RuntimeException {
    MyException(String problem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(problem);
        alert.showAndWait();

    }
}
