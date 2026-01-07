package org.example.nextgenloader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import org.controlsfx.control.PropertySheet;
import org.example.nextgenloader.visual.DisplayableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.example.nextgenloader.alerts.Alerts.finalizeMessage;
import static org.example.nextgenloader.files.FileManagement.getFilesNamesFromControlFile;
import static org.example.nextgenloader.files.FileManagement.searchPathOfFile;

public class LoadingController {

    @FXML
    private ListView<PropertySheet.Item> fileListView;


    private ListView<DisplayableItem> testList;

    private Integer listIndex = 0;

    private ObservableList<PropertySheet.Item> fileItems;


    @FXML
    private Label pathLabel;

    public static Logger log = LoggerFactory.getLogger(LoadingController.class);

    private Path workingDirectory;

    private List<File> files;

    public LoadingController(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @FXML
    public void initialize() {
        File controlFile = new File(workingDirectory + "/control.txt");

        List<String> fileNames = getFilesNamesFromControlFile(controlFile);

        if (fileNames!=null) {
            ObservableList<PropertySheet.Item> items =  FXCollections.observableArrayList();

            for (String fileName: fileNames) {
                DisplayableItem displayableItem = new DisplayableItem(fileName);
                displayableItem.setDescription(searchPathOfFile(workingDirectory,fileName));
                items.add(displayableItem);
            }

              //fileNames
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
            pathLabel.setText("");
            pathLabel.setText(fileItems.get(listIndex).getDescription());

            fileListView.getSelectionModel().select(listIndex);
            fileListView.scrollTo(listIndex);
            listIndex++;


        } else {
            finalizeMessage();
        }





    }

    @FXML
    protected void lastFile() {

        if(listIndex>0) {
            listIndex--;
            pathLabel.setText("");
            pathLabel.setText(fileItems.get(listIndex).getDescription());
            fileListView.getSelectionModel().select(listIndex);
            fileListView.scrollTo(listIndex);
        }



    }

    @FXML
    protected void postponeLoadingProcess() {
        System.out.println("Postpone Loading process");
    }

    @FXML
    protected void copyPath() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(pathLabel.getText());
        clipboard.setContent(content);
    }
}
