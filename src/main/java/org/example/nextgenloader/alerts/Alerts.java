package org.example.nextgenloader.alerts;

import javafx.scene.control.Alert;

public class Alerts {

    public static void alertGenerator() {


        Alert alertWrongDirectory = new Alert(Alert.AlertType.ERROR);
        alertWrongDirectory.setTitle("Wrong directory");
        alertWrongDirectory.setHeaderText("Wrong Directory selected");
        alertWrongDirectory.setContentText("The files are not csv or the directory does not exist " +
                "ot it is empty");
        alertWrongDirectory.showAndWait();

    }



}
