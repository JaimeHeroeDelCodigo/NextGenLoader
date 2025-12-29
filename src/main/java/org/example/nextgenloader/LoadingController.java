package org.example.nextgenloader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.controlsfx.control.PropertySheet.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadingController {

    @FXML
    private ListView<Item> fileListView;

    public static Logger log = LoggerFactory.getLogger(LoadingController.class);

    private Path workingDirectory;

    private ObservableList<Item> items =  FXCollections.observableArrayList();

    public LoadingController(Path workingDirectory) {
        this.workingDirectory = workingDirectory;

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



}
