package org.example.nextgenloader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import static java.nio.file.Files.createDirectory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

import static org.example.nextgenloader.alerts.Alerts.errorAlertGenerator;
import static org.example.nextgenloader.alerts.Alerts.yesNoPromptAfterLoading;
import static org.example.nextgenloader.files.FileManagement.*;
import static org.example.nextgenloader.utils.Numeric.isACorrectNumber;

public class HomeController {

    public static Logger log = LoggerFactory.getLogger(HomeController.class);

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
                String numberOfFilesToBeCharged = promptNumberOfFiles();
                if(numberOfFilesToBeCharged!=null) {
                    if (!isACorrectNumber(numberOfFilesToBeCharged)) {
                        errorAlertGenerator("Wrong input", "Empty number input",
                                "The number was not entered is empty or is not a valid input.");
                    } else if (!Objects.equals(Integer.valueOf(numberOfFilesToBeCharged), numberOFiles)) {
                        errorAlertGenerator("Loading error", "Non matching files",
                                "The number of files in the directory differs.");
                    } else {
                        loadConfiguration(actionEvent, selectedDirectory);
                    }
                }
            } else {
                numberOFiles = 0;
                errorAlertGenerator("Wrong directory","Wrong Directory selected",
                        "The files are not csv or the directory does not exist or it is empty");

            }
        }
    }

    protected String promptNumberOfFiles() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Number of files");
        dialog.setHeaderText("Please enter the number of files expected to be loaded:");
        dialog.setContentText("Number of files:");
        return dialog.showAndWait().orElse(null);
    }

    protected void loadConfiguration(ActionEvent actionEvent, File directory)  {
        Button sourceButton =  (Button) actionEvent.getSource();
        Stage sourceStage = (Stage) sourceButton.getScene().getWindow();
        sourceStage.close();

        try {
            File [] csv_list = directory.listFiles();

            String directoryOutput = directory.getPath().concat("/OUTPUT" );
            Path pathDirectoryOutput = Paths.get(directoryOutput);
            createDirectory(pathDirectoryOutput);
            createControlFile(directory);


            for (File file:csv_list) {
                String fileDir = file.getPath();

                Path fileDirPath = Paths.get(fileDir);

                String tenderNumber= file.getName().split("_")[0];
                String tenderNumberDir = directoryOutput + "/" + tenderNumber;

                String tenderNumberINGENICODir = tenderNumberDir + "/" + file.getName();
                Path tenderNumberINGENICODirPath = Paths.get(tenderNumberINGENICODir);

                Path tenderNumberDirPath = Paths.get(tenderNumberDir);
                String backUpDir = tenderNumberDir + "/" + "BACKUP";
                String backUpDirFile = backUpDir + "/" + file.getName();
                createDirectory(tenderNumberDirPath );
                createDirectory(Paths.get(backUpDir));

                Files.copy(fileDirPath,Paths.get(backUpDirFile),StandardCopyOption.REPLACE_EXISTING);

                Files.copy(fileDirPath, tenderNumberINGENICODirPath.resolveSibling("INGENICO - " + file.getName()));

                fileProcessing(new File(tenderNumberDir + "/" +   "INGENICO - " + file.getName()));

                File control = new File(directoryOutput + "/control.txt");

                fileRegister(control,tenderNumber);
            }

            Optional<ButtonType> confirmation = yesNoPromptAfterLoading("Success", "Configuration Loading completed", "¿Do you want to " +
                    "continue with the loading right now?");

            if (confirmation.isPresent() &&  ButtonType.OK.equals(confirmation.get())){
                NextGenLoadingScreenApplication app = new  NextGenLoadingScreenApplication();
                Stage stage = new Stage();
                app.start(stage);
            } else {
                NextGenLoaderApplication app = new NextGenLoaderApplication();
                Stage stage = new Stage();
                app.start(stage);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}