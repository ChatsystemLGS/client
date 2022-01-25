package client.db;

import java.util.ArrayList;
import java.util.Arrays;

public class Channel extends TransmittableObject {

    private final int id;
    private final Type type;
    private final String name;

    private final ArrayList<Message> messages = new ArrayList<>();

    public Channel(int id, Type type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Message getLatestMessage() {
        if (messages.size() == 0)
            return null;
        return messages.get(0);
    }

    public void setMessages(Message[] messages) {
        this.messages.clear();
        this.messages.addAll(Arrays.asList(messages));
    }

    public Message[] getMessages() {
        return messages.toArray(new Message[0]);
    }

    public enum Type {
        DM, PRIVATE_GROUP, PUBLIC_GROUP
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

}
