package client;

import client.simplelogger.SimpleLogger;
import client.simplelogger.SimpleLogger.LogLevel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Scanner;

@SuppressWarnings("all")
public class ServerHandler implements Runnable {

    public static ServerHandler sh;
    Socket server;
    Session session;
    Scanner s;
    PrintWriter pw;

    ServerHandler(Socket server, Client client) throws IOException, ProtocolException {
        this.server = server;

        s = new Scanner(server.getInputStream());
        pw = new PrintWriter(server.getOutputStream(), true);

        session = new Session(client);

        // SimpleLogger.logf(LogLevel.DEBUG, "%s", session.getState());

        String response = readLine();
        String[] greeting = response.split(" ");

        if (greeting.length != 2)
            throw new ProtocolException.UnknownException(response);

        String status = greeting[0];
        String version = greeting[1];

        if (!status.contentEquals("OK"))
            throw ProtocolException.getException(response);

        if (!version.contentEquals(Session.PROTOCOL_VERSION))
            throw new ProtocolException.ProtocolVersionMismatchException(version, Session.PROTOCOL_VERSION);

        SimpleLogger.logf(LogLevel.INFO, "connected to server [%s]:%s (%s)", server.getInetAddress(), server.getPort(),
                version);
    }

    @Override
    public void run() {
        try {
            while (session.getState() != Session.State.DISCONNECTED) {
                // keeps connection alive
                // TODO: maybe better way to keep alive?? @lixo
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

    public String readLine() {
        String line = s.nextLine();
        SimpleLogger.logf(LogLevel.DEBUG, "server: > %s", line);
        return line;
    }

    public void writeLine(String line) {
        SimpleLogger.logf(LogLevel.DEBUG, "server: < %s", line);
        pw.println(line);
    }

    public static void createClient(Socket server, Client client) throws IOException, ProtocolException {
        sh = new ServerHandler(server, client);
        new Thread(sh).start();
        // new ServerHandler(server, client);
    }

    public String execute(Session.Command cmd, Object... parameters) throws ProtocolException {

        StringBuilder sb = new StringBuilder(cmd.toString());

        for (Object param : parameters) {
            sb.append(" ");
            if (param instanceof String)
                sb.append(toBase64String(((String) param).getBytes()));
            else if (param instanceof Timestamp)
                sb.append(((Timestamp) param).getTime());
            else if (param instanceof byte[])
                sb.append(toBase64String((byte[]) param));
            else
                sb.append(param);
        }

        writeLine(sb.toString());

        String rawResponse = readLine();
        String[] response = rawResponse.split(" ");

        if (response.length == 0)
            throw ProtocolException.getException(rawResponse);

        return rawResponse;
    }

    private String toBase64String(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] fromBase64String(String data) {
        return Base64.getDecoder().decode((data).getBytes());
    }

    public String base64toString(String data) {
        if (data.contentEquals("-"))
            return "";
        if (data.contentEquals("null"))
            return null;
        try {
            return new String(fromBase64String(data), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
