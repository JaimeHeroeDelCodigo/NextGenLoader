package org.example.nextgenloader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;

public class NextGenLoadingScreenApplication extends Application {


    public static Logger log =  LoggerFactory.getLogger(NextGenLoaderApplication.class);

    private Path workingDirectory;


    public NextGenLoadingScreenApplication() {}


    public NextGenLoadingScreenApplication(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }


    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fmxlLoader = new FXMLLoader(NextGenLoadingScreenApplication.class.getResource("loading-view.fxml"));

        LoadingController controller = new LoadingController(workingDirectory);
        fmxlLoader.setController(controller);


        //AnchorPane root = fmxlLoader.load();



        Scene scene = new Scene(fmxlLoader.load());

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();


    }



}
