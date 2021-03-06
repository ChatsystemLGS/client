package client;

import client.db.Message;

import java.io.Serial;
import java.sql.Date;

public abstract class ProtocolException extends Exception {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -2162828148268985546L;
    private final Status status;

    ProtocolException(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public static ProtocolException getException(String response) {
        return new UnknownException(response);
    }

    public static class UnknownException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -432928437762596614L;

        public UnknownException(String response) {
            super(Status.UNKNOWN_EXCEPTION);
        }
    }

    public static class ProtocolVersionMismatchException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -8555814451693659465L;

        public ProtocolVersionMismatchException(String serverVersion, String clientVersion) {
            super(Status.DEPRECATED_PROTOCOL_VERSION);
        }
    }

    public static class ParseException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = 8485084790158847325L;

        public ParseException() {
            super(Status.UNABLE_TO_PARSE);
        }
    }

    public static class InvalidParameterException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -4208867606970220161L;
        private final int index;

        public InvalidParameterException(int index) {
            super(Status.INVALID_PARAMETER);
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

    }

    public static class EmailAlreadyRegisteredException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = 4237922086005335690L;

        public EmailAlreadyRegisteredException() {
            super(Status.EMAIL_ALREADY_REGISTERED);
        }

    }

    public static class PasswordRequirementNotMetException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -1035791352733735378L;

        public PasswordRequirementNotMetException() {
            super(Status.PASSWORD_REQ_NOT_MET);
        }

    }

    public static class EmailNotRegisteredException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -6532826769572086272L;

        public EmailNotRegisteredException() {
            super(Status.EMAIL_NOT_REGISTERED);
        }

    }

    public static class PasswordInvalidException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -1832448351451110039L;

        public PasswordInvalidException() {
            super(Status.PASSWORD_INVALID);
        }

    }

    public static class NotMemberOfChannelException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = 2170636056686421482L;

        public NotMemberOfChannelException() {
            super(Status.NOT_MEMBER_OF_CHANNEL);
        }

    }

    public static class MessageTooLongException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -5241748522110696394L;
        private final int MAX_MESSAGE_SIZE;

        public MessageTooLongException(int MAX_MESSAGE_SIZE) {
            super(Status.MESSAGE_TOO_LONG);
            this.MAX_MESSAGE_SIZE = MAX_MESSAGE_SIZE;
        }

        public int getMaxMessageSize() {
            return MAX_MESSAGE_SIZE;
        }

    }


    public static class TooManyMessagesException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = 4931284760707863961L;
        private final Date lastMessageTime;
        private final Message[] messages;

        public TooManyMessagesException(Date lastMessageTime, @SuppressWarnings("ClassEscapesDefinedScope") Message[] messages) {
            super(Status.TOO_MANY_MESSAGES);
            this.lastMessageTime = lastMessageTime;
            this.messages = messages;
        }

        public Date getLastMessageTime() {
            return lastMessageTime;
        }

        @SuppressWarnings("ClassEscapesDefinedScope")
        public Message[] getMessages() {
            return messages;
        }

    }

    public static class ChannelNotFoundException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = 6705554276488394002L;

        public ChannelNotFoundException() {
            super(Status.CHANNEL_NOT_FOUND);
        }

    }

    public static class UserNotFoundException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = 6940922235095667798L;

        public UserNotFoundException() {
            super(Status.USER_NOT_FOUND);
        }

    }

    public static class DmAlreadyExistsException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = 2923409626696600479L;
        private final int channelId;

        public DmAlreadyExistsException(int channelId) {
            super(Status.DM_ALREADY_EXISTS);
            this.channelId = channelId;
        }

        public int getChannelId() {
            return channelId;
        }

    }

    public static class InternalServerErrorException extends ProtocolException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -9199244919909050026L;

        public InternalServerErrorException() {
            super(Status.INTERNAL_SERVER_ERROR);
        }

    }

    public enum Status {

        OK, NOT_ENOUGH_PARAMETERS, TOO_MANY_PARAMETERS, INVALID_PARAMETER, COMMAND_NOT_FOUND, INTERNAL_SERVER_ERROR,
        AUTHENTICATION_REQUIRED, EMAIL_ALREADY_REGISTERED, PASSWORD_REQ_NOT_MET, EMAIL_NOT_REGISTERED, PASSWORD_INVALID,
        NOT_MEMBER_OF_CHANNEL, MESSAGE_TOO_LONG, TOO_MANY_MESSAGES, CHANNEL_NOT_FOUND, USER_NOT_FOUND,
        DM_ALREADY_EXISTS, UNABLE_TO_PARSE, DEPRECATED_PROTOCOL_VERSION, UNKNOWN_EXCEPTION;

        @Override
        public String toString() {
            return this.name();
        }

    }
}