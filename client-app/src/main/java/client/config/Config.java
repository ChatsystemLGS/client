package client.config;

public class Config {

    public final int MAX_MESSAGE_LENGTH;
    public final String ADDRESS;
    public final int PORT;

    public static final Config DEFAULT_CONFIG = new Config(Integer.MAX_VALUE, "localhost", 1465);

    public Config(int maxMessageLength, String address, int port) {

        MAX_MESSAGE_LENGTH = maxMessageLength;
        this.ADDRESS = address;
        this.PORT = port;
    }
}