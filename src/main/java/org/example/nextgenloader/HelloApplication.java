package org.example.nextgenloader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));



        Scene scene = new Scene(fxmlLoader.load(), 470, 440);
        stage.setTitle("NextGenLoader");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws InterruptedException {
        Application.launch(HelloApplication.class);


    }
}