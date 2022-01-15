package client.app.config;

import java.io.File;

public class Config {

	public final int MAX_MESSAGE_LENGTH;
	public final int PORT;
	public final String ADDRESS;

	public static final Config DEFAULT_CONFIG = new Config(Integer.MAX_VALUE, 1465, "localhost");

	public Config(int maxMessageLength, int port, String address) {

		MAX_MESSAGE_LENGTH = maxMessageLength;
		this.PORT = port;
		this.ADDRESS = address;

	}

	// public static Config createFromArgs(String[] args, Config defaultValues) {

	// 	ArgMap parameters = new ArgMap(args);

	// 	int port;
	// 	String address;

	// 	address = parameters.getString("address", defaultValues.ADDRESS);

	// 	try {
	// 		port = parameters.getInteger("port", defaultValues.PORT);
	// 	} catch (NumberFormatException e) {
	// 		throw new IllegalArgumentException();
	// 	}

	// 	return new Config(DEFAULT_CONFIG.MAX_MESSAGE_LENGTH, port, address);
	// }

	public static Config getDefaultConfig() {
		return new Config(DEFAULT_CONFIG.MAX_MESSAGE_LENGTH, DEFAULT_CONFIG.PORT, DEFAULT_CONFIG.ADDRESS);
	}

	public static Config loadCfg(File f) {
		// TODO
		return null;
	}

	public static void WriteCfg(File f, Config cfg) {
		// TODO
	}

}
