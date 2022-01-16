package client;

import client.ProtocolException.Status;
import client.db.TransmittableObject;
import client.db.User;


public class Session {

	static final String PROTOCOL_VERSION = "0.0.0";

    private final Client client;

	private State state;
	private User user;

	public Session(Client client) {
		this.client = client;
		state = State.CONNECTED;
	}
    
	public static void execute(String command) {
		System.out.println("execute");
	}

	private String response(Status status, Object obj, TransmittableObject object, TransmittableObject[] objectList) {

		String s = status.toString();

		if (obj != null)
			s += " " + obj.toString();

		if (object != null)
			s += " " + object.transmittableString();

		if (objectList != null)
			s += " " + TransmittableObject.toString(objectList);

		return s;
	}

	private String response(Status status, Object retVal1, TransmittableObject[] retVal2) {
		return response(status, retVal1, null, retVal2);
	}

	private String response(TransmittableObject retVal) {
		return response(Status.OK, null, retVal, null);
	}

	private String response(Status status, Object retVal) {
		return response(status, retVal, null, null);
	}

	private String response(Status status) {
		return response(status, null, null, null);
	}

	private String response(TransmittableObject[] retVal) {
		return response(Status.OK, null, null, retVal);
	}

	private String response() {
		return response(Status.OK);
	}

	public void disconnect() {
		state = State.DISCONNECTED;
	}

	public State getState() {
		return state;
	}

	public String greet() {
		return response(Status.OK, PROTOCOL_VERSION);
	}

	enum State {

		CONNECTED(null), AUTHENTICATED(State.CONNECTED), DISCONNECTED(State.CONNECTED);

		private final State parentState;

		State(State parentState) {
			this.parentState = parentState;
		}

		// returns parent State or null if it is the default State
		public State getParentState() {
			return parentState;
		}

		public boolean hasParent(State state) {

			if (state == null)
				throw new IllegalArgumentException();

			if (parentState == null)
				return false;

			if (parentState == state)
				return true;
			else
				return parentState.hasParent(state);
		}

	}

	enum Command {

		REGISTER(State.CONNECTED, 3), 
		LOGIN(State.CONNECTED, 2), 
		GETPUBLICGROUPS(State.AUTHENTICATED, 0),
		JOINGROUP(State.AUTHENTICATED, 1), 
		GETCHANNELS(State.AUTHENTICATED, 0),
		GETCHANNELMEMBERS(State.AUTHENTICATED, 1), 
		GETUSER(State.AUTHENTICATED, 1), 
		ADDFRIEND(State.AUTHENTICATED, 1),
		GETFRIENDS(State.AUTHENTICATED, 0), 
		SENDMESSAGE(State.AUTHENTICATED, 3),
		CREATEDM(State.AUTHENTICATED, 1),
		RECEIVEMESSAGES(State.AUTHENTICATED, 3), 
		QUIT(State.CONNECTED, 0);

		private final State requiredState;
		private final int numArgs;

		Command(State requiredState, int numArgs) {
			this.requiredState = requiredState;
			this.numArgs = numArgs;
		}

		public State getRequiredState() {
			return requiredState;
		}

		public int getNumArgs() {
			return numArgs;
		}

	}

}
