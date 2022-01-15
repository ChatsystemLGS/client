package client.app;
// package client.app;

// import java.io.File;
// import java.io.IOException;

// import client.app.config.Config;
// import client.app.simplelogger.ConsoleLogger;
// import client.app.simplelogger.FileLogger;
// import client.app.simplelogger.SimpleLogger;
// import client.app.simplelogger.FileLogger.LogType;
// import client.app.simplelogger.SimpleLogger.LogLevel;

// public class App {

// 	private Client client;

// 	public static void main(String[] args) {

// 		// setup logging
// 			try {
// 				FileLogger fileLogger = new FileLogger(new File("./latest.log"), LogType.APPEND, LogLevel.DEBUG);
// 				SimpleLogger.addLogListener(fileLogger);

// 				ConsoleLogger consoleLogger = new ConsoleLogger(LogLevel.DEBUG);
// 				SimpleLogger.addLogListener(consoleLogger);
// 			} catch (IllegalArgumentException e) {
// 				e.printStackTrace();
// 			} catch (IOException e) {
// 				e.printStackTrace();
// 			}

// 		new App(Config.createFromArgs(args, Config.DEFAULT_CONFIG));
		
// 	}

// 	private App(Config cfg) {
// 		client = new Client(cfg);
// 	}
// }
