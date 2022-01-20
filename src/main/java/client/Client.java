package client;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import client.config.Config;

public class Client {

    @SuppressWarnings("all")
    public final Config CFG;

    Client(Config cfg) {
        CFG = cfg;

        try {
            Socket server = new Socket(cfg.ADDRESS, cfg.PORT);
            ServerHandler.createClient(server, this); // TODO: alternative
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
