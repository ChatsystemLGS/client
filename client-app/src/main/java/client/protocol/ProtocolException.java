package client.protocol;

public abstract class ProtocolException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -2162828148268985546L;
    private final Status status;

    ProtocolException(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
