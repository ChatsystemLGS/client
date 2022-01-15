package client.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import client.app.config.Config;
import client.app.simplelogger.ConsoleLogger;
import client.app.simplelogger.FileLogger;
import client.app.simplelogger.SimpleLogger;
import client.app.simplelogger.FileLogger.LogType;
import client.app.simplelogger.SimpleLogger.LogLevel;

public class ChatApplication extends Application {

    private Client client;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        // scene.getStylesheets().add(getClass().getResource("style.css").toString());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
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

        launch();
    }

    // contructor is not allowed here
    // private ChatApplication(Config cfg) {
	// 	client = new Client(cfg);
	// }
}
