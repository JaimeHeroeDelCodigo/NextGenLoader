package org.example.nextgenloader;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadingController {

    public static Logger log = LoggerFactory.getLogger(LoadingController.class);

    @FXML
    protected void nextFile() {
        System.out.println("Siguiente archivo");
    }

    @FXML
    protected void lastFile() {
        System.out.println("Último archivo");
    }
}
