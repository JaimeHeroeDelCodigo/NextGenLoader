package org.example.nextgenloader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.PropertySheet.Item;
import org.example.nextgenloader.visual.DisplayableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadingController {

    @FXML
    private ListView<String> fileListView;

    public static Logger log = LoggerFactory.getLogger(LoadingController.class);

    private Path workingDirectory;



    public LoadingController(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public void initialize() {



        ObservableList<String> items =  FXCollections.observableArrayList("Item 1","Item 2","Item 3","Item 4");

        fileListView.setItems(items);
    }

    public LoadingController() {
        this(Paths.get("C:/"));
    }





    @FXML
    protected void nextFile() {
        System.out.println("Siguiente archivo");
    }

    @FXML
    protected void lastFile() {
        System.out.println("Último archivo");
    }

    @FXML
    protected void postponeLoafingProcess() {
        System.out.println("Postpone Loading process");
    }

    @FXML
    protected void copyPath() {
        System.out.println("Copy path");
    }



}
