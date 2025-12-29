package org.example.nextgenloader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

import static org.example.nextgenloader.constants.TextConstants.START_APPLICATION_LOG;
import static org.example.nextgenloader.constants.TextConstants.START_LEGEND;

public class NextGenLoadingScreenApplication extends Application {


    public static Logger log =  LoggerFactory.getLogger(NextGenLoaderApplication.class);

    private Path workingDirectory;


    public NextGenLoadingScreenApplication() {}


    public NextGenLoadingScreenApplication(Path workingDirectory) {
        this();
        this.workingDirectory = workingDirectory;
    }


    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fmxlLoader = new FXMLLoader(NextGenLoadingScreenApplication.class.getResource("loading-view.fxml"));
        Scene scene = new Scene(fmxlLoader.load(),485,300);
        //Scene scene = new Scene(fmxlLoader.load());
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }



}
