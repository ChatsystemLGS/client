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

        SimpleLogger.logf(LogLevel.DEBUG, "%s", session.getState());
    }

    @Override
    public void run() {
        try {
            // TODO: implement sending commands to the server
            // LOGIN bWlya28ubGVvbi53ZWloQGxncy1odS5ldQ== bVdlMWhfMTIzNA==

            if (session.getState() != Session.State.DISCONNECTED) {
                checkProtocolVersion(readLine());
            }

            while (session.getState() != Session.State.DISCONNECTED) { // implement keep alive? 
                // writeLine(String.format("LOGIN %1$s %2$s", email, password));
                handle(readLine());
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

    public void sendCommand(String command) {
        try {

            if (session.getState() != Session.State.DISCONNECTED) {
                writeLine(command);
            }

        } catch (NoSuchElementException e) {
            session.disconnect();
        } finally {
            try {
                server.close();
                SimpleLogger.logf(LogLevel.DEBUG, "%s", session.getState());
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
        // new ServerHandler(server, client);
	}

    private void checkProtocolVersion(String response) {
        if (response.equalsIgnoreCase("OK " + session.PROTOCOL_VERSION)) {
            System.out.println("Matching protocol version");
        } else if (response.split(" ")[0].equalsIgnoreCase("OK")) {
            System.out.println("Deprecated Protocol Version! Update to Version: " + response.split(" ")[1]);
        }
    }

    public static void handle(String response) {
        
    }
}
