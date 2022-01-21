package client;

import client.db.User;

@SuppressWarnings("all")
public class Session {

	public final static String PROTOCOL_VERSION = "0.0.0";

    private final Client client;

	private State state;
	private User user;

	public Session(Client client) {
		this.client = client;
		state = State.CONNECTED;
	}

	public void disconnect() {
		state = State.DISCONNECTED;
	}

	public State getState() {
		return state;
	}

	enum State {

		CONNECTED(null),
		AUTHENTICATED(State.CONNECTED),
		DISCONNECTED(State.CONNECTED);

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
