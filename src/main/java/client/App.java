package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

import client.config.Config;
import client.simplelogger.ConsoleLogger;
import client.simplelogger.FileLogger;
import client.simplelogger.SimpleLogger;
import client.simplelogger.FileLogger.LogType;
import client.simplelogger.SimpleLogger.LogLevel;

public class App extends Application {

    private Client client;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        primaryStage.setTitle("Chat");
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
            FileLogger fileLogger = new FileLogger(new File("./latest.log"), LogType.APPEND, LogLevel.DEBUG);
            SimpleLogger.addLogListener(fileLogger);

            ConsoleLogger consoleLogger = new ConsoleLogger(LogLevel.DEBUG);
            SimpleLogger.addLogListener(consoleLogger);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		new Client(Config.getDefaultConfig());

        // launch();
    }

    // contructor is not allowed here
    // private App(Config cfg) {
	// 	client = new Client(cfg);
	// }
}
