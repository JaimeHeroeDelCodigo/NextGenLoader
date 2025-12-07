package org.example.nextgenloader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Objects;
import java.util.Optional;

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
                numberOFiles = Objects.requireNonNull(selectedDirectory.listFiles()).length;
                loadConfiguration(actionEvent);
                //Integer numberOfFilesToBeCharged = promptNumberOfFiles();

                if(promptNumberOfFiles()==null) {


                }

            } else {
                numberOFiles = 0;
                Alert alertWrongDirectory = new Alert(Alert.AlertType.ERROR);
                alertWrongDirectory.setTitle("Wrong directory");
                alertWrongDirectory.setHeaderText("Wrong Directory selected");
                alertWrongDirectory.setContentText("The files are not csv or the directory does not exist " +
                        "ot it is empty");
                alertWrongDirectory.showAndWait();
            }
        }
    }



    protected Integer promptNumberOfFiles() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Number of files");
        dialog.setHeaderText("Please enter the number of files expected to be loaded:");
        dialog.setContentText("Number of files:");


        String  result = dialog.showAndWait().orElse("");



        return result.isEmpty() ? null: Integer.valueOf(result) ;

    }


    protected void loadConfiguration(ActionEvent actionEvent) {
        Button sourceButton =  (Button) actionEvent.getSource();
        Stage sourceStage = (Stage) sourceButton.getScene().getWindow();
        sourceStage.close();
    }

}