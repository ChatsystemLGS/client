module client.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;
    requires fontawesomefx;

    opens client to javafx.fxml;
    exports client;

    opens client.gui to javafx.fxml;
    exports client.gui;
    exports client.interfaces;
    opens client.interfaces to javafx.fxml;
}