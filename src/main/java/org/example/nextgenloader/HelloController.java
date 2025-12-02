package org.example.nextgenloader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void openFileExplorerClick() {




        welcomeText.setText("New loading started");
        

    }
}