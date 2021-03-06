package client.db;

import java.sql.Timestamp;

public class Message extends TransmittableObject {

    private final Attr<Integer> id = new Attr<>();
    private final Attr<Integer> channel = new Attr<>();
    private final Attr<Integer> author = new Attr<>();
    private final Attr<Timestamp> timestamp = new Attr<>();
    private final Attr<byte[]> data = new Attr<>();
    private final Attr<DataType> dataType = new Attr<>();

    public Message() {
        registerAttributes(id, channel, author, timestamp, data, dataType);
    }

    public Message withId(int id) {
        this.id.set(id);
        return this;
    }

    public Message withChannel(int id) {
        this.channel.set(id);
        return this;
    }

    public Message withAuthor(int id) {
        this.author.set(id);
        return this;
    }

    public Message withTimestamp(Timestamp timestamp) {
        this.timestamp.set(timestamp);
        return this;
    }

    public Message withData(byte[] data) {
        this.data.set(data);
        return this;
    }

    public Message withDataType(DataType dataType) {
        this.dataType.set(dataType);
        return this;
    }

    public int getId() {
        return id.getValue();
    }

    public int getChannel() {
        return channel.getValue();
    }

    public int getAuthor() {
        return author.getValue();
    }

    public Timestamp getTimestamp() {
        return timestamp.getValue();
    }

    public byte[] getData() {
        return data.getValue();
    }

    public DataType getDataType() {
        return dataType.getValue();
    }

    public enum DataType {

        TEXT, FILE_TXT, FILE_PNG, FILE_GIF, FILE_PDF

    }

}
