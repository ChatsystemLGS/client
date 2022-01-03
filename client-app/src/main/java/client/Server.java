package client;

import client.config.Config;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {

    public final Config CFG;

    Server(Config cfg) throws IOException {
        CFG = cfg;

        try {
            Socket server = new Socket(cfg.ADDRESS, cfg.PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void communicate(){

    }
}
