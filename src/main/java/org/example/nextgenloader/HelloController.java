package org.example.nextgenloader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

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
                System.out.println("Directorio invalido");
            }

        }




    }





}