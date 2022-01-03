package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler implements Runnable {

    Socket socket;
    Scanner s;
    PrintWriter pw;

    Session session;

    private ServerHandler(Socket client, Server server) throws IOException {
        this.socket = client;

        s = new Scanner(client.getInputStream());
        pw = new PrintWriter(client.getOutputStream(), true);

        session = new Session(server);
    }

    @Override
    public void run() {

    }
}
