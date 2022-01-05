package client;

public class Session {

    private final Client client;

	private State state;
	private User user;

	public Session(Client client) {
		this.client = client;
		state = State.CONNECTED;
	}

    public String greet() {
		return response(Status.OK, PROTOCOL_VERSION);
	}
    
}
