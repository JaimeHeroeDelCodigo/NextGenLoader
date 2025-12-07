package org.example.nextgenloader.alerts;

import javafx.scene.control.Alert;

public class Alerts {

    public static void errorAlertGenerator(String title,String header, String text) {
        Alert alertWrongDirectory = new Alert(Alert.AlertType.ERROR);
        alertWrongDirectory.setTitle(title);
        alertWrongDirectory.setHeaderText(header);
        alertWrongDirectory.setContentText(text);
        alertWrongDirectory.showAndWait();
    }



}
