package org.example.nextgenloader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Objects;

import static org.example.nextgenloader.alerts.Alerts.errorAlertGenerator;
import static org.example.nextgenloader.files.FileManagement.validDirectory;

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

                Integer numberOfFilesToBeCharged = promptNumberOfFiles();

                if(numberOfFilesToBeCharged==null) {
                    errorAlertGenerator("Wrong input","Empty number input",
                            "The number was not entered is empty or is not a valid input.");
                } else if (!Objects.equals(numberOfFilesToBeCharged, numberOFiles)) {
                    errorAlertGenerator("Loading error","Non matching files",
                            "The number of files in the directory differs.");
                } else {
                    loadConfiguration(actionEvent);
                }

            } else {
                numberOFiles = 0;
                errorAlertGenerator("Wrong directory","Wrong Directory selected",
                        "The files are not csv or the directory does not exist or it is empty");

            }
        }
    }

    protected Integer promptNumberOfFiles() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Number of files");
        dialog.setHeaderText("Please enter the number of files expected to be loaded:");
        dialog.setContentText("Number of files:");

        String  result = dialog.showAndWait().orElse("");

        return result.isEmpty() ? null: Integer.valueOf(result);
    }

    protected void loadConfiguration(ActionEvent actionEvent) {
        Button sourceButton =  (Button) actionEvent.getSource();
        Stage sourceStage = (Stage) sourceButton.getScene().getWindow();
        sourceStage.close();
    }

}