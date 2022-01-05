package client;

import java.io.IOException;

import client.config.Config;

public class App {

    private Client client;

    public static void main(String[] args) throws IOException {
		new App(Config.createFromArgs(args, Config.DEFAULT_CONFIG));
    }

    private App(Config cfg) throws IOException {

		client = new Client(cfg);

	}
}
