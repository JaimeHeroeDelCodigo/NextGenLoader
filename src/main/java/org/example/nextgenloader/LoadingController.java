package org.example.nextgenloader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;
import org.example.nextgenloader.visual.DisplayableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.example.nextgenloader.alerts.Alerts.finalizeMessage;
import static org.example.nextgenloader.files.FileManagement.*;

public class LoadingController {

    @FXML
    private ListView<PropertySheet.Item> fileListView;

    private ListView<DisplayableItem> testList;

    private Integer listIndex = 0;

    private ObservableList<PropertySheet.Item> fileItems;

    @FXML
    private Label pathLabel;

    @FXML
    private Button postponeProcessButton;

    private Clipboard clipboard = Clipboard.getSystemClipboard();

    private final ClipboardContent clipboardContent = new ClipboardContent();




    public static Logger log = LoggerFactory.getLogger(LoadingController.class);

    private Path workingDirectory;

    public LoadingController(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @FXML
    public void initialize() throws IOException {
        File controlFile = new File(workingDirectory + "/control.txt");

        List<String> fileNames = getFilesNamesFromControlFile(controlFile);

        boolean rollOutFileFlag = rollOutFileCreation(workingDirectory,fileNames);

        if (fileNames!=null) {
            ObservableList<PropertySheet.Item> items =  FXCollections.observableArrayList();

            for (String fileName: fileNames) {
                DisplayableItem displayableItem = new DisplayableItem(fileName);
                displayableItem.setDescription(searchPathOfFile(workingDirectory,fileName));
                items.add(displayableItem);
            }
            fileListView.setItems(items);
        }
        fileItems = fileListView.getItems();
    }

    public LoadingController() {
        this(Paths.get("C:/"));
    }

    @FXML
    protected void nextFile() {

        if(listIndex< fileItems.size()) {

            listIndex++;
            pathLabel.setText("");
            pathLabel.setText(fileItems.get(listIndex).getDescription());
            fileListView.getSelectionModel().select(listIndex);
            fileListView.scrollTo(listIndex);

            clipboard.clear();
            String fileName = fileItems.get(listIndex).getName();
            clipboardContent.putString(fileName);
            clipboard.setContent(clipboardContent);

        } else {
            finalizeMessage();
        }
    }

    @FXML
    protected void previousFile() {

        if(listIndex>0) {
            listIndex--;
            pathLabel.setText("");
            pathLabel.setText(fileItems.get(listIndex).getDescription());
            fileListView.getSelectionModel().select(listIndex);
            fileListView.scrollTo(listIndex);

            String fileName = fileItems.get(listIndex).getName();

            clipboardContent.putString(fileName);
            clipboard.setContent(clipboardContent);
        }
    }

    @FXML
    protected void postponeLoadingProcess() throws Exception {

        Stage sourceStage = (Stage) postponeProcessButton.getScene().getWindow();
        sourceStage.close();
        NextGenLoaderApplication app = new NextGenLoaderApplication();
        Stage stage = new Stage();
        app.start(stage);
    }

    @FXML
    protected void copyPath() {
        clipboardContent.clear();
        clipboardContent.putString(pathLabel.getText());
        clipboard.setContent(clipboardContent);
    }
}
