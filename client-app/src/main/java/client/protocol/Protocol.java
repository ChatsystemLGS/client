package client.protocol;

public interface Protocol {

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

		REGISTER(State.CONNECTED, 2),
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
    
    enum Status {

		OK, NOT_ENOUGH_PARAMETERS,
		TOO_MANY_PARAMETERS,
		INVALID_PARAMETER,
		COMMAND_NOT_FOUND,
		INTERNAL_SERVER_ERROR,
		AUTHENTICATION_REQUIRED,
		EMAIL_ALREADY_REGISTERED,
		PASSWORD_REQ_NOT_MET,
		EMAIL_NOT_REGISTERED,
		PASSWORD_INVALID,
		NOT_MEMBER_OF_CHANNEL,
		MESSAGE_TOO_LONG,
		TOO_MANY_MESSAGES,
		CHANNEL_NOT_FOUND,
		USER_NOT_FOUND,
		DM_ALREADY_EXISTS;

		@Override
		public String toString() {
			return this.name();
		}

	}

    enum DataType {

		TEXT,
		FILE_TXT,
		FILE_PNG,
		FILE_GIF,
		FILE_PDF;

	}
    
}
