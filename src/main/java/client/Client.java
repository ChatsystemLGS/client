package client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public final Config CFG;

    Client(Config cfg) {
        CFG = cfg;

        try {
            Socket server = new Socket(cfg.ADDRESS, cfg.PORT);
            ServerHandler.createClient(server, this);
        } catch (IOException | ProtocolException e) {
            e.printStackTrace();
        }
    }


}
