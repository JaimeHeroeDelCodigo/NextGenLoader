package org.example.nextgenloader;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.example.nextgenloader.constants.TextConstants.*;

public class NextGenLoaderApplication extends Application {

    public static Logger log =  LoggerFactory.getLogger(NextGenLoaderApplication.class);

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(NextGenLoaderApplication.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760 , 470);
        stage.setTitle("NextGenLoader");
        stage.setScene(scene);
        stage.setResizable(false);
        log.info(START_APPLICATION_LOG);
        log.info(START_LEGEND);
        stage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        Application.launch(NextGenLoaderApplication.class);
    }
}