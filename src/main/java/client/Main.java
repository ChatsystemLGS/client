package client;

import client.simplelogger.ConsoleLogger;
import client.simplelogger.FileLogger;
import client.simplelogger.SimpleLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/Main.fxml")));
        primaryStage.setTitle("Chat Application");

        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        // setup logging
        try {
            FileLogger fileLogger = new FileLogger(new File("./latest.log"), FileLogger.LogType.APPEND, SimpleLogger.LogLevel.DEBUG);
            SimpleLogger.addLogListener(fileLogger);

            ConsoleLogger consoleLogger = new ConsoleLogger(SimpleLogger.LogLevel.DEBUG);
            SimpleLogger.addLogListener(consoleLogger);
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }

        new Client(Config.getDefaultConfig());
        launch(args);
    }
}
