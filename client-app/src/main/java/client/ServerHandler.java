package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import server.simplelogger.SimpleLogger;
import server.simplelogger.SimpleLogger.LogLevel;

public class ServerHandler implements Runnable {

    Socket server;
    Scanner s;
    PrintWriter pw;

    Session session;

    private ServerHandler(Socket server, Client client) throws IOException {
        this.server = server;

        s = new Scanner(server.getInputStream());
        pw = new PrintWriter(server.getOutputStream(), true);

        // session = new Session(client);
    }

    @Override
    public void run() {
        try {
            // TODO: implement sending commands to the server
            writeLine(session.greet());
            while (session.getState() != Session.State.DISCONNECTED) {
                readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
                System.out.println("disconnected from server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readLine() {
		String line = s.nextLine();
		SimpleLogger.logf(LogLevel.DEBUG, "server [%s]:%s > %s", line);
		return line;
	}

	private void writeLine(String line) {
		SimpleLogger.logf(LogLevel.DEBUG, "server [%s]:%s < %s", line);
		pw.println(line);
	}

    public static void createClient(Socket server, Client client) throws IOException {
        new ServerHandler(server, client);
    }
}
