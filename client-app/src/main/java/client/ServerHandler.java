package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import client.simplelogger.SimpleLogger;
import client.simplelogger.SimpleLogger.LogLevel;

public class ServerHandler implements Runnable {

    Socket server;
    Scanner s;
    PrintWriter pw;

    Session session;

    private ServerHandler(Socket server, Client client) throws IOException {
        this.server = server;

        s = new Scanner(server.getInputStream());
        pw = new PrintWriter(server.getOutputStream(), true);

        session = new Session(client);

        SimpleLogger.logf(LogLevel.DEBUG, "server: %s", session.getState());
    }

    @Override
    public void run() {
        try {
            // TODO: implement sending commands to the server

            while (session.getState() != Session.State.DISCONNECTED) {

                // if (readLine().equalsIgnoreCase("INVALID_PARAMETER 1")) {
                //     session.disconnect();
                // } else {
                //     writeLine("LOGIN mirko.leon.weih@lgs-hu.eu mWe1h_1234");
                // }
            }
        } catch (NoSuchElementException e) {
            session.disconnect();
        } finally {
            try {
                server.close();
                SimpleLogger.logf(LogLevel.DEBUG, "client: %s", session.getState());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readLine() {
		String line = s.nextLine();
		SimpleLogger.logf(LogLevel.DEBUG, "server: > %s", line);
		return line;
	}

	private void writeLine(String line) {
		SimpleLogger.logf(LogLevel.DEBUG, "server: < %s", line);
		pw.println(line);
	}

    public static void createClient(Socket server, Client client) throws IOException {
		new Thread(new ServerHandler(server, client)).start();
	}
}
