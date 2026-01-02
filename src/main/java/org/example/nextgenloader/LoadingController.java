package org.example.nextgenloader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.example.nextgenloader.files.FileManagement.getFilesNamesFromControlFile;

public class LoadingController {

    @FXML
    private ListView<String> fileListView;

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

        System.out.println(fileNames);

        if (fileNames!=null) {
            ObservableList<String> items =  FXCollections.observableArrayList(fileNames);
            fileListView.setItems(items);



            //for(String fileName:fileNames) {
                //workingDirectory


            //}


        }
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
