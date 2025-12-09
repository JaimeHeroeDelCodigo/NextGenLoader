module org.example.nextgenloader {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires org.slf4j;
    requires ch.qos.logback.classic;

    opens org.example.nextgenloader to javafx.fxml;
    exports org.example.nextgenloader;
}