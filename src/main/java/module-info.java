module client.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires fontawesomefx;

    opens client to javafx.fxml;
    exports client;

    opens client.gui to javafx.fxml;
    exports client.gui;
}