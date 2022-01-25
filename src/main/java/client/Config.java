package client;

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

    public static Config getDefaultConfig() {
        return new Config(DEFAULT_CONFIG.MAX_MESSAGE_LENGTH, DEFAULT_CONFIG.PORT, DEFAULT_CONFIG.ADDRESS);
    }

    public static Config loadCfg(File f) {
        // TODO: implement load from file
        return null;
    }

    public static void WriteCfg(File f, Config cfg) {
        // TODO: implement write to file
    }

}
