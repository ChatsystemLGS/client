package client.protocol;

public interface Protocol {
    void register(String email, String password)
            throws EmailAlreadyRegisteredException, PasswordRequirementNotMetException;

}
