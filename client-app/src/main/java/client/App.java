package client;

import java.io.File;
import java.io.IOException;

import client.config.Config;
import client.simplelogger.ConsoleLogger;
import client.simplelogger.FileLogger;
import client.simplelogger.FileLogger.LogType;
import client.simplelogger.SimpleLogger;
import client.simplelogger.SimpleLogger.LogLevel;

public class App {

  private Client client;

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

    new App(Config.createFromArgs(args, Config.DEFAULT_CONFIG));
    
  }

  private App(Config cfg) {

    client = new Client(cfg);

  }
}
