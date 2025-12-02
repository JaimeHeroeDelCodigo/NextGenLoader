package org.example.nextgenloader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void openFileExplorerClick() {

        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Select a directory");

        File selectedDirectory = directoryChooser.showDialog(null);

        System.out.println("############################################################");

        System.out.println(selectedDirectory.getAbsolutePath());








        //welcomeText.setText("New loading started");
        

    }
}