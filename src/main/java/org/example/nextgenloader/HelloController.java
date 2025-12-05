package org.example.nextgenloader;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import java.io.File;

import static org.example.nextgenloader.management.FileManagement.validDirectory;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void openFileExplorerClick() {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");
        File selectedDirectory = directoryChooser.showDialog(null);

        if(selectedDirectory != null) {
            if(validDirectory(selectedDirectory)) {
                System.out.println("Directorio valido");
            } else {
                Alert alertWrongDirectory = new Alert(Alert.AlertType.ERROR);
                alertWrongDirectory.setTitle("Wrong directory");
                alertWrongDirectory.setHeaderText("Wrong Directory selected");
                alertWrongDirectory.setContentText("The files are not csv or the directory does not exist " +
                        "ot it is empty");
                alertWrongDirectory.showAndWait();
            }
        }
    }
}