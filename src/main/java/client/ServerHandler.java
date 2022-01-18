package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Scanner;

import client.db.Channel;
import client.db.Message;
import client.db.User;
import client.simplelogger.SimpleLogger;
import client.simplelogger.SimpleLogger.LogLevel;

@SuppressWarnings("all")
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
            // LOGIN bWlya28ubGVvbi53ZWloQGxncy1odS5ldQ== bVdlMWhfMTIzNA==

            if (session.getState() != Session.State.DISCONNECTED) {
                checkProtocolVersion(readLine());
            }

            while (session.getState() != Session.State.DISCONNECTED) { // implement keep alive? 
                // writeLine(String.format("LOGIN %1$s %2$s", email, password));
                // handle(readLine());
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

    public String toBase64(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    // Commands

	private void register(String emailAddress, String password, String nickname) {
		sendCommand(String.format("REGISTER %s %s %s", toBase64(emailAddress), toBase64(password), toBase64(nickname)));
    }

	private void login(String emailAddress, String password) {
        sendCommand(String.format("LOGIN %s %s", toBase64(emailAddress), toBase64(password)));
    }

    private Channel[] getPublicGroups() {
		Channel[] publicGroups = null;
		return publicGroups;
    }

    private void joinGroup(int channelID) {

    }

    private Channel[] getChannels() {
		Channel[] channels = null;
		return channels;
    }

    private User[] getChannelMembers(int channelID) {
		User[] users = null;
		return users;
    }

    private User getUser(int userID, String emailAddress) {
		User user = null;
		return user;
    }

    private void addFriend(int userID) {

    }

    private User[] getFriends() {
		User[] users = null;
		return users;
    }

    private void sendMessage(int channelID, Byte[] data, Enum dataType) {

    }

    private Channel createDM(int userID) {
		Channel channel = null;
		return channel;
    }

    private Message[] recieveMessages(int channelID, Timestamp tFrom, Timestamp tUntil) {
		Message[] messages = null;
		return messages;
    }

    private void quit() {
        session.disconnect();
    }
}
