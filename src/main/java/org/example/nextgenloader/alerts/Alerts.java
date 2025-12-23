package org.example.nextgenloader.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import static javafx.application.Application.launch;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;


public class Alerts {

    public static void errorAlertGenerator(String title,String header, String text) {
        Alert alertWrongDirectory = new Alert(Alert.AlertType.ERROR);
        alertWrongDirectory.setTitle(title);
        alertWrongDirectory.setHeaderText(header);
        alertWrongDirectory.setContentText(text);
        alertWrongDirectory.showAndWait();
    }

    public static Optional<ButtonType> yesNoPromptAfterLoading(String title,String header,String content) {

        Alert yesNoAlert = new Alert(CONFIRMATION);

        yesNoAlert.setTitle(title);
        yesNoAlert.setHeaderText(header);
        yesNoAlert.setContentText(content);

        return yesNoAlert.showAndWait();
    }
}
