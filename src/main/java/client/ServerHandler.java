package client;

import client.simplelogger.SimpleLogger;
import client.simplelogger.SimpleLogger.LogLevel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

@SuppressWarnings("all")
public class ServerHandler implements AutoCloseable {

    static Socket server;
    static Scanner s;
    static PrintWriter pw;

    static Session session;

    private ServerHandler(Socket server, Client client) throws IOException {
        this.server = server;

        s = new Scanner(server.getInputStream());
        pw = new PrintWriter(server.getOutputStream(), true);

        session = new Session(client);

        SimpleLogger.logf(LogLevel.DEBUG, "%s", session.getState());
    }

    public static String execute(Session.Command cmd, Object... parameters) throws ProtocolException {

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

        if (!response[0].contentEquals("OK"))
            throw ProtocolException.getException(rawResponse);

        if (response.length > 1)
            return String.join(" ", Arrays.copyOfRange(response, 1, response.length));

        return "";
    }

    static String toBase64String(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] fromBase64String(String data) {
        return Base64.getDecoder().decode((data).getBytes());
    }

    private String base64toString(String data) {
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

    private static String readLine() {
        String line = s.nextLine();
        SimpleLogger.logf(LogLevel.DEBUG, "server [%s]:%s > %s", server.getInetAddress(), server.getPort(), line);
        return line;
    }

    private static void writeLine(String line) {
        SimpleLogger.logf(LogLevel.DEBUG, "server [%s]:%s < %s", server.getInetAddress(), server.getPort(), line);
        pw.println(line);
    }

    @Override
	public void close() throws Exception {
		CommandController.quit();
		server.close();
	}
}
