package org.example.nextgenloader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

import static org.example.nextgenloader.management.FileManagement.validDirectory;

public class HelloController {

    public static Logger log = LoggerFactory.getLogger(HelloController.class);

    @FXML
    private Label welcomeText;

    private Integer numberOFiles;

    @FXML
    protected void openFileExplorerClick(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");
        File selectedDirectory = directoryChooser.showDialog(null);

        if(selectedDirectory != null) {
            if(validDirectory(selectedDirectory)) {
                loadConfiguration(actionEvent);
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

    protected void promptNumberOfFiles() {
        Label promptLabel = new Label ("Enter the input number");
        TextField inputFiled = new TextField();
        Button accessButton = new Button("Enter");
        Label warningLabel = new Label();

        VBox vBox = new VBox(10,promptLabel,inputFiled,accessButton,warningLabel);
    }

    protected void loadConfiguration(ActionEvent actionEvent) {
        Button sourceButton =  (Button)actionEvent.getSource();
        Stage sourceStage = (Stage) sourceButton.getScene().getWindow();
        sourceStage.close();
    }

}